// List of base metals with their spectral reflectance values (normalized 0-1)
// Based on real optical properties and interband transitions
const metals = {
    gold: { r: 0.99, g: 0.96, b: 0.37, brittleness: 0.1, metalness: 0.98, roughness: 0.18, emissive: 0.00, desc: "Absorbs Blue (Relativistic electrons)" },
    silver: { r: 0.98, g: 0.98, b: 0.98, brittleness: 0.1, metalness: 0.99, roughness: 0.08, emissive: 0.00, desc: "High reflectance across spectrum" },
    copper: { r: 0.72, g: 0.44, b: 0.20, brittleness: 0.1, metalness: 0.95, roughness: 0.20, emissive: 0.00, desc: "Warm reddish-brown reflectance" },
    aluminum: { r: 0.90, g: 0.92, b: 0.95, brittleness: 0.9, metalness: 0.88, roughness: 0.25, emissive: 0.00, desc: "Violet/UV absorption" },
    gallium: { r: 0.75, g: 0.78, b: 0.85, brittleness: 0.9, metalness: 0.82, roughness: 0.35, emissive: 0.04, desc: "Cool grey, forms intermetallics" },
    titanium: { r: 0.50, g: 0.50, b: 0.55, brittleness: 0.2, metalness: 0.72, roughness: 0.55, emissive: 0.00, desc: "Matte, absorbent" },
    indium: { r: 0.80, g: 0.82, b: 0.88, brittleness: 0.8, metalness: 0.80, roughness: 0.40, emissive: 0.03, desc: "Similar to gallium for blue gold" }
};

function clamp01(value) {
    return Math.max(0, Math.min(1, value));
}

function calculateAppearance(composition) {
    const total = Object.values(composition).reduce((sum, val) => sum + val, 0);
    if (total === 0) return { metalness: 0.75, roughness: 0.5, emissive: 0 };

    let metalness = 0;
    let roughness = 0;
    let emissive = 0;

    for (let metal in composition) {
        const frac = composition[metal] / total;
        const spec = metals[metal];
        if (spec) {
            metalness += (spec.metalness ?? 0.8) * frac;
            roughness += (spec.roughness ?? 0.4) * frac;
            emissive += (spec.emissive ?? 0) * frac;
        }
    }

    return {
        metalness: clamp01(metalness),
        roughness: clamp01(roughness),
        emissive: clamp01(emissive)
    };
}

// Effects that apply based on composition, using continuous physics-inspired weights
const alloyEffects = [
    {
        name: 'electrum_green_shift',
        displayType: 'electrum',
        weight: (f) => Math.min(clamp01(f.silver / 0.5), clamp01(f.gold / 0.95)),
        modifiers: { r: 0.92, g: 1.05, b: 1.0 },
        brittlenessBonus: 0.0,
        desc: 'Silver reduces red reflectance in gold, creating an electrum-like greenish tint.'
    },
    {
        name: 'rose_gold_red_boost',
        displayType: 'rose_gold',
        weight: (f) => {
            const total = f.gold + f.copper;
            if (total === 0) return 0;
            const copperRatio = f.copper / total;
            const ratioMatch = Math.exp(-Math.pow((copperRatio - 0.25) / 0.12, 2));
            return clamp01(ratioMatch * clamp01(total / 1.0));
        },
        targetColor: [0.92, 0.65, 0.50],
        modifiers: { r: 1.08, g: 0.92, b: 0.95 },
        brittlenessBonus: 0.05,
        desc: 'Copper adds warm rosy tones to gold as the alloy approaches rose-gold composition.'
    },
    {
        name: 'gallium_blue_shift',
        displayType: 'blue_gold',
        weight: (f) => {
            const ratio = f.gold + f.gallium === 0 ? 0 : f.gold / (f.gold + f.gallium);
            const target = 1 / 3; // AuGa2 ratio by fraction
            const match = Math.exp(-Math.pow((ratio - target) / 0.15, 2));
            return clamp01(match * clamp01(f.gallium / 0.4));
        },
        modifiers: { r: 0.88, g: 0.92, b: 1.15 },
        brittlenessBonus: 0.5,
        desc: 'Gold-gallium alloys gradually shift blue as the composition moves toward intermetallic behavior.'
    },
    {
        name: 'aluminum_purple_shift',
        displayType: 'purple_gold',
        weight: (f) => {
            const ratio = f.gold + f.aluminum === 0 ? 0 : f.gold / (f.gold + f.aluminum);
            const target = 0.79; // approximate Au/total for AuAl2
            const match = Math.exp(-Math.pow((ratio - target) / 0.12, 2));
            return clamp01(match * clamp01(f.aluminum / 0.22));
        },
        modifiers: { r: 0.94, g: 0.80, b: 1.08 },
        brittlenessBonus: 0.55,
        desc: 'Gold-aluminum alloys develop a mild purple tone as intermetallic formation begins.'
    },
    {
        name: 'indium_blue_shift',
        displayType: 'blue_gold',
        weight: (f) => {
            const ratio = f.gold + f.indium === 0 ? 0 : f.gold / (f.gold + f.indium);
            const target = 1 / 3;
            const match = Math.exp(-Math.pow((ratio - target) / 0.15, 2));
            return clamp01(match * clamp01(f.indium / 0.4));
        },
        modifiers: { r: 0.90, g: 0.92, b: 1.12 },
        brittlenessBonus: 0.5,
        desc: 'Gold-indium composition gently moves toward blue as the intermetallic ratio is approached.'
    },
    {
        name: 'titanium_matte_absorb',
        displayType: 'titanium_matte',
        weight: (f) => clamp01(f.titanium / 0.30),
        modifiers: { r: 0.82, g: 0.82, b: 0.82 },
        brittlenessBonus: 0.1,
        desc: 'Titanium increasingly darkens and dulls the alloy, simulating its absorbent effect.'
    }
];

// Function to calculate alloy color considering bonding differences
function calculateAlloy(composition) {
    // Normalize composition to percentages
    const total = Object.values(composition).reduce((sum, val) => sum + val, 0);
    if (total === 0) return { color: [0, 0, 0], brittleness: 0, type: 'empty', desc: 'No metals', effects: [] };

    const normalized = {};
    for (let metal in composition) {
        normalized[metal] = composition[metal] / total * 100; // As percentage
    }

    // Start with base spectral mixing
    let r = 0, g = 0, b = 0, brittleness = 0;
    let appliedEffects = [];

    for (let metal in normalized) {
        const amt = normalized[metal] / 100; // Fraction
        const spec = metals[metal];
        if (spec) {
            r += spec.r * amt;
            g += spec.g * amt;
            b += spec.b * amt;
            brittleness += spec.brittleness * amt;
        }
    }

    let color = [r, g, b];

    const fraction = {};
    for (let metal in normalized) {
        fraction[metal] = normalized[metal] / 100;
    }

    function clamp01(value) {
        return Math.max(0, Math.min(1, value));
    }

    function blendColor(base, modified, weight) {
        return [
            base[0] * (1 - weight) + modified[0] * weight,
            base[1] * (1 - weight) + modified[1] * weight,
            base[2] * (1 - weight) + modified[2] * weight
        ];
    }

    function effectWeight(effect, frac) {
        if (typeof effect.weight !== 'function') return 0;
        return clamp01(effect.weight(frac));
    }

    function applyEffect(color, effect, weight) {
        if (!weight || (!effect.modifiers && !effect.targetColor)) return color;
        if (effect.targetColor) {
            return blendColor(color, effect.targetColor, weight);
        }
        const modified = [
            color[0] * (effect.modifiers.r !== undefined ? effect.modifiers.r : 1),
            color[1] * (effect.modifiers.g !== undefined ? effect.modifiers.g : 1),
            color[2] * (effect.modifiers.b !== undefined ? effect.modifiers.b : 1)
        ];
        return blendColor(color, modified, weight);
    }

    const effectWeights = [];

    // Apply effects in order, continuously
    for (let effect of alloyEffects) {
        const weight = effectWeight(effect, fraction);
        if (weight > 0) {
            color = applyEffect(color, effect, weight);
            brittleness += effect.brittlenessBonus * weight;
            appliedEffects.push(effect.name);
            effectWeights.push({ effect, weight });
        }
    }

    // Determine type based on dominant physical effect
    let type = 'alloy';
    let desc = 'Standard alloy mixture';
    const strongEffect = effectWeights.reduce((best, current) => current.weight > (best?.weight || 0) ? current : best, null);
    if (strongEffect && strongEffect.weight > 0.35 && strongEffect.effect.displayType) {
        type = strongEffect.effect.displayType.toUpperCase();
        desc = strongEffect.effect.desc;
    } else if (effectWeights.some(item => item.weight > 0.1)) {
        type = 'special_alloy';
        desc = 'Alloy with mild bonding effects: ' + effectWeights.map(item => item.effect.name).join(', ');
    }

    color = color.map(clamp01);
    const appearance = calculateAppearance(normalized);

    return {
        color: color,
        brittleness: Math.min(brittleness, 1.0), // Cap at 1
        type: type,
        desc: desc,
        effects: appliedEffects,
        appearance: appearance
    };
}

// Function to mix colors (legacy, for compatibility)
function mixColors(selectedMetals) {
    const composition = {};
    selectedMetals.forEach(metal => {
        composition[metal.name.toLowerCase()] = 1; // Equal parts
    });
    const result = calculateAlloy(composition);
    return result.color.map(c => Math.round(c * 255));
}

// Function to convert RGB to hex
function rgbToHex(r, g, b) {
    const clamp255 = (value) => Math.max(0, Math.min(255, Math.round(value)));
    r = clamp255(r);
    g = clamp255(g);
    b = clamp255(b);
    return "#" + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
}

// Function to get alloy color
function getAlloyColor(selectedNames) {
    const composition = {};
    selectedNames.forEach(name => {
        composition[name.toLowerCase()] = 1;
    });
    const result = calculateAlloy(composition);
    const rgb = result.color.map(c => Math.round(c * 255));
    return rgbToHex(...rgb);
}

// Export for use in HTML
if (typeof module !== 'undefined' && module.exports) {
    module.exports = { metals, alloyEffects, calculateAlloy, mixColors, rgbToHex, getAlloyColor };
}
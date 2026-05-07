/*
 * Minecraft ingot generator for alloy outputs.
 *
 * This file creates a simple 16×16 ingot sprite using the alloy color
 * returned by the alloy generator. The generated image is intended to
 * resemble Minecraft ingot pixels with highlights and shading.
 * Note: this file is a work in progress
 */

function clamp01(value) {
    return Math.max(0, Math.min(1, value));
}

function hexToRgb(hex) {
    if (typeof hex !== 'string') return null;
    const cleaned = hex.replace(/^#/, '').trim();
    if (cleaned.length === 3) {
        return cleaned.split('').map(c => parseInt(c + c, 16));
    }
    if (cleaned.length === 6) {
        return [0, 2, 4].map(i => parseInt(cleaned.slice(i, i + 2), 16));
    }
    return null;
}

function normalizeColor(input) {
    if (Array.isArray(input) && input.length === 3) {
        const [r, g, b] = input;
        if (r <= 1 && g <= 1 && b <= 1) {
            return [clamp01(r), clamp01(g), clamp01(b)];
        }
        return [clamp01(r / 255), clamp01(g / 255), clamp01(b / 255)];
    }

    if (typeof input === 'string') {
        const rgb = hexToRgb(input);
        if (rgb) {
            return rgb.map(channel => clamp01(channel / 255));
        }
    }

    if (input && typeof input === 'object') {
        if (input.color && Array.isArray(input.color) && input.color.length === 3) {
            return normalizeColor(input.color);
        }
        if (input.r !== undefined && input.g !== undefined && input.b !== undefined) {
            return normalizeColor([input.r, input.g, input.b]);
        }
    }

    return [1, 1, 1];
}

const INGOT_TEMPLATE = [
    '                ',
    '                ',
    '          ss    ',
    '       ooo12o   ',
    '    ooo.....zo  ',
    ' +&&%.......@=! ',
    'o?..........^^~x',
    'ocw3.4555w67890x',
    '                ',
    '                ',
    '                ',
    '                ',
    '                ',
    '                ',
    '                ',
    '                '
];

const GOLD_REFERENCE_COLOR = normalizeColor([243, 193,  72]);

function rgbMulFromGoldReference(addOffset) {
    return GOLD_REFERENCE_COLOR.map((channel, index) => {
        if (channel <= 0) return 1;
        return clamp01((channel + addOffset[index]) / channel);
    });
}

const INGOT_COLOR_ADJUSTMENTS = {
    ' ': null,
    's': { mul: rgbMulFromGoldReference([-0.29, -0.57, -0.33]) },
    'o': { mul: rgbMulFromGoldReference([-0.28, -0.57, -0.33]) },
    '1': { mul: rgbMulFromGoldReference([-0.06, -0.21, -0.13]) },
    '2': { mul: rgbMulFromGoldReference([-0.04, -0.17, -0.12]) },
    'z': { mul: rgbMulFromGoldReference([-0.01, -0.02, -0.01]) },
    '+': { mul: rgbMulFromGoldReference([-0.28, -0.54, -0.29]) }, // #B66B14 (Golden Brown)
    '&': { mul: rgbMulFromGoldReference([-0.28, -0.54, -0.32]) }, // #B66A0D (Deep Copper)
    '%': { mul: rgbMulFromGoldReference([-0.01, -0.11, -0.08]) }, // #FAD84A (Mellow Gold)
    '@': { color: [1.00, 1.00, 1.00] }, // white highlight
    '=': { mul: rgbMulFromGoldReference([-0.01, -0.11, -0.05]) }, // #FAD951 (Soft Goldenrod)
    '!': { mul: rgbMulFromGoldReference([-0.51, -0.79, -0.35]) }, // #7B2B04 (Dark Burnt Sienna)
    '?': { color: [1.00, 1.00, 1.00] }, // white highlight
    '^': { color: [1.00, 1.00, 1.00] }, // pure white
    '~': { mul: rgbMulFromGoldReference([-0.08, -0.13, -0.06]) }, // #E8D450 (Muted Mustard)
    'x': { mul: rgbMulFromGoldReference([-0.53, -0.82, -0.37]) }, // #762300 (Deep Mahogany)
    'c': { add: [-0.05, -0.19,  0.12] },                          // #EFC53F (Ochre Gold)
    'w': { color: [1.00, 1.00, 1.00] }, // white highlight
    '3': { color: [1.00, 1.00, 1.00] }, // white highlight
    '4': { mul: rgbMulFromGoldReference([ 0.00, -0.01,  0.01]) }, // #FDF561 (Base Variation)
    '5': { mul: rgbMulFromGoldReference([ 0.00,  0.01,  0.07]) }, // #FDF670 (Soft Lemon)
    '6': { color: [1.00, 1.00, 1.00] }, // white highlight
    '7': { color: [1.00, 1.00, 1.00] }, // white highlight
    '8': { mul: rgbMulFromGoldReference([-0.02, -0.05, -0.04]) }, // #F9E855 (Flat Gold)
    '9': { mul: rgbMulFromGoldReference([-0.13, -0.38, -0.30]) }, // #DB9513 (Deep Amber)
    '0': { mul: rgbMulFromGoldReference([-0.09, -0.24, -0.13]) }, // #E6B83E (Antique Gold)
    '.': { mul: [1.00, 1.00, 1.00] },
    '-': { mul: [1.10, 1.10, 1.10] },
    '*': { mul: [1.18, 1.18, 1.18] },
    '#': { mul: [0.84, 0.84, 0.84] }
};

function applyColorAdjustment(rgb, adjustment) {
    if (!adjustment) {
        return rgb;
    }

    if (typeof adjustment === 'number') {
        return rgb.map(channel => clamp01(channel * adjustment));
    }

    if (adjustment && typeof adjustment === 'object' && Array.isArray(adjustment.color) && adjustment.color.length === 3) {
        return normalizeColor(adjustment.color);
    }

    let adjusted = rgb.slice();

    if (Array.isArray(adjustment) && adjustment.length === 3) {
        return rgb.map((channel, index) => clamp01(channel + adjustment[index]));
    }

    if (typeof adjustment === 'object') {
        if (adjustment.mul !== undefined) {
            if (Array.isArray(adjustment.mul) && adjustment.mul.length === 3) {
                adjusted = adjusted.map((channel, index) => clamp01(channel * adjustment.mul[index]));
            } else {
                adjusted = adjusted.map(channel => clamp01(channel * adjustment.mul));
            }
        }

        if (adjustment.add !== undefined) {
            if (Array.isArray(adjustment.add) && adjustment.add.length === 3) {
                adjusted = adjusted.map((channel, index) => clamp01(channel + adjustment.add[index]));
            } else {
                adjusted = adjusted.map(channel => clamp01(channel + adjustment.add));
            }
        }

        if (adjustment.sub !== undefined || adjustment.subtract !== undefined) {
            const subtraction = adjustment.sub ?? adjustment.subtract;
            if (Array.isArray(subtraction) && subtraction.length === 3) {
                adjusted = adjusted.map((channel, index) => clamp01(channel - subtraction[index]));
            } else {
                adjusted = adjusted.map(channel => clamp01(channel - subtraction));
            }
        }

        if (adjustment.r !== undefined || adjustment.g !== undefined || adjustment.b !== undefined) {
            adjusted = [
                clamp01(adjusted[0] + (adjustment.r ?? 0)),
                clamp01(adjusted[1] + (adjustment.g ?? 0)),
                clamp01(adjusted[2] + (adjustment.b ?? 0))
            ];
        }
    }

    return adjusted;
}

function createIngotPixelData(baseColor, size = 16) {
    const rgb = normalizeColor(baseColor);
    const data = new Uint8ClampedArray(size * size * 4);

    for (let y = 0; y < size; y += 1) {
        const row = INGOT_TEMPLATE[y] || ''.padEnd(size, ' ');
        for (let x = 0; x < size; x += 1) {
            const char = row[x] || ' ';
            const adjustment = INGOT_COLOR_ADJUSTMENTS[char] ?? null;
            const offset = (y * size + x) * 4;
            if (adjustment !== null) {
                const [r, g, b] = applyColorAdjustment(rgb, adjustment);
                data[offset] = Math.round(r * 255);
                data[offset + 1] = Math.round(g * 255);
                data[offset + 2] = Math.round(b * 255);
                data[offset + 3] = 255;
            } else {
                data[offset] = 0;
                data[offset + 1] = 0;
                data[offset + 2] = 0;
                data[offset + 3] = 0;
            }
        }
    }

    return data;
}

function createIngotImageData(baseColor, size = 16) {
    const pixelData = createIngotPixelData(baseColor, size);
    if (typeof ImageData !== 'undefined') {
        return new ImageData(pixelData, size, size);
    }
    return { width: size, height: size, data: pixelData };
}

function createIngotCanvas(baseColor, size = 16) {
    if (typeof document === 'undefined') {
        throw new Error('createIngotCanvas requires a browser environment.');
    }
    const canvas = document.createElement('canvas');
    canvas.width = size;
    canvas.height = size;
    const ctx = canvas.getContext('2d');
    const imageData = createIngotImageData(baseColor, size);
    ctx.putImageData(imageData, 0, 0);
    return canvas;
}

function createIngotDataURL(baseColor, size = 16) {
    const canvas = createIngotCanvas(baseColor, size);
    return canvas.toDataURL('image/png');
}

function generateAlloyIngot(composition, options = {}) {
    const color = (typeof calculateAlloy === 'function')
        ? normalizeColor(calculateAlloy(composition).color)
        : normalizeColor(options.color || [1, 1, 1]);
    return createIngotDataURL(color, options.size || 16);
}

function renderAlloyIngotToElement(element, composition, options = {}) {
    const url = generateAlloyIngot(composition, options);
    if (typeof element === 'string') {
        element = document.getElementById(element);
    }
    if (!element) {
        throw new Error('Target element not found for ingot render.');
    }

    const img = document.createElement('img');
    img.width = options.size || 16;
    img.height = options.size || 16;
    img.src = url;
    img.alt = 'Alloy ingot preview';

    element.innerHTML = '';
    element.appendChild(img);
    return img;
}

if (typeof window !== 'undefined') {
    window.createIngotDataURL = createIngotDataURL;
    window.createIngotImageData = createIngotImageData;
    window.generateAlloyIngot = generateAlloyIngot;
    window.renderAlloyIngotToElement = renderAlloyIngotToElement;
}

if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        createIngotPixelData,
        createIngotImageData,
        createIngotDataURL,
        generateAlloyIngot,
        renderAlloyIngotToElement
    };
}

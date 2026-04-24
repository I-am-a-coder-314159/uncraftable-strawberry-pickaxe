// Furnace Temperature System for Strawberry Minecraft
// Custom component to handle temperature based on fuel

import { world, BlockPermutation, system, ActionFormData, ItemStack } from "@minecraft/server";

// Define temperature values for different fuels (in Celsius)
const fuelTemperatures = {
  "minecraft:coal": 800,
  "minecraft:charcoal": 800,
  "minecraft:coal_block": 1000,
  "minecraft:stick": 200,
  "minecraft:wood": 400,
  "minecraft:planks": 400,
  "minecraft:oak_planks": 400,
  "minecraft:spruce_planks": 400,
  "minecraft:birch_planks": 400,
  "minecraft:jungle_planks": 400,
  "minecraft:acacia_planks": 400,
  "minecraft:dark_oak_planks": 400,
  "minecraft:oak_log": 500,
  "minecraft:spruce_log": 500,
  "minecraft:birch_log": 500,
  "minecraft:jungle_log": 500,
  "minecraft:acacia_log": 500,
  "minecraft:dark_oak_log": 500,
  "minecraft:stripped_oak_log": 500,
  "minecraft:stripped_spruce_log": 500,
  "minecraft:stripped_birch_log": 500,
  "minecraft:stripped_jungle_log": 500,
  "minecraft:stripped_acacia_log": 500,
  "minecraft:stripped_dark_oak_log": 500,
  "minecraft:oak_wood": 500,
  "minecraft:spruce_wood": 500,
  "minecraft:birch_wood": 500,
  "minecraft:jungle_wood": 500,
  "minecraft:acacia_wood": 500,
  "minecraft:dark_oak_wood": 500,
  "minecraft:stripped_oak_wood": 500,
  "minecraft:stripped_spruce_wood": 500,
  "minecraft:stripped_birch_wood": 500,
  "minecraft:stripped_jungle_wood": 500,
  "minecraft:stripped_acacia_wood": 500,
  "minecraft:stripped_dark_oak_wood": 500,
  "minecraft:lava_bucket": 1600,
  "minecraft:blaze_rod": 1000
};

// Define custom recipes for the temperature furnace
const customRecipes = [
  {
    input: "minecraft:iron_ore",
    modifiers: [],
    output: "minecraft:iron_ingot",
    minTemp: 800,
    maxTemp: 1200,
    cookTime: 100
  },
  {
    input: "minecraft:gold_ore",
    modifiers: [],
    output: "minecraft:gold_ingot",
    minTemp: 1000,
    maxTemp: 1500,
    cookTime: 120
  },
  {
    input: "minecraft:cobblestone",
    modifiers: ["minecraft:redstone"],
    output: "minecraft:stone",
    minTemp: 600,
    maxTemp: 1000,
    cookTime: 80
  },
  {
    input: "minecraft:sand",
    modifiers: [],
    output: "minecraft:glass",
    minTemp: 1200,
    maxTemp: 2000,
    cookTime: 150
  },
  {
    input: "minecraft:clay_ball",
    modifiers: [],
    output: "minecraft:brick",
    minTemp: 800,
    maxTemp: 1200,
    cookTime: 100
  }
];

// Track smelting progress for each furnace
const smeltingProgress = new Map();

// Custom component function for temperature
function furnaceTemperatureComponent(block) {
  try {
    // Get the block's inventory
    const inventory = block.getComponent("minecraft:inventory");
    if (!inventory) return;

    const container = inventory.container;
    if (!container) return;

    // Check fuel slot (slot 1 in furnace)
    const fuelItem = container.getItem(1);
    let temperature = 0;

    if (fuelItem) {
      const itemId = fuelItem.typeId;
      temperature = fuelTemperatures[itemId] || 0;
    }

    // Get current temperature property
    const currentTemp = block.permutation.getProperty("strawberrymc:temperature")?.value || 0;

    // Only update if temperature changed
    if (currentTemp !== temperature) {
      // Create new permutation with updated temperature
      const newPermutation = block.permutation.withProperty("strawberrymc:temperature", temperature);
      block.setPermutation(newPermutation);
    }
  } catch (error) {
    console.warn("Furnace temperature component error:", error);
  }
}

// Custom component function for interaction
function furnaceInteractComponent(block, player) {
  try {
    // Open the custom furnace UI
    openFurnaceUI(player, block);
  } catch (error) {
    console.warn("Furnace interact component error:", error);
  }
}

// Function to open the custom furnace UI
function openFurnaceUI(player, block) {
  try {
    const temperature = block.permutation.getProperty("strawberrymc:temperature")?.value || 0;

    // Create the UI form
    const form = new ActionFormData()
      .title("Temperature Furnace")
      .body(`Current Temperature: ${temperature}°C\n\nInput Slots (0-2): Normal smelting\nModifier Slots (3-4): Special effects\nOutput Slot (5): Results`);

    // Add buttons for interaction
    form.button("Close");

    // Show the form
    form.show(player).then((response) => {
      if (response.canceled) {
        // Form was closed
      }
    });
  } catch (error) {
    console.warn("Open furnace UI error:", error);
  }
}

// Function to process smelting recipes
function processSmelting(block) {
  try {
    const inventory = block.getComponent("minecraft:inventory");
    if (!inventory) return;

    const container = inventory.container;
    if (!container) return;

    const temperature = block.permutation.getProperty("strawberrymc:temperature")?.value || 0;

    // Check input slots (0, 2, 3)
    const inputSlots = [0, 2, 3];
    for (const slot of inputSlots) {
      const inputItem = container.getItem(slot);
      if (!inputItem) continue;

      // Check modifier slots (4, 5)
      const modifier0 = container.getItem(4);
      const modifier1 = container.getItem(5);
      const modifiers = [];
      if (modifier0) modifiers.push(modifier0.typeId);
      if (modifier1) modifiers.push(modifier1.typeId);

      // Find matching recipe
      const recipe = customRecipes.find(r =>
        r.input === inputItem.typeId &&
        r.minTemp <= temperature &&
        r.maxTemp >= temperature &&
        modifiers.length === r.modifiers.length &&
        r.modifiers.every(mod => modifiers.includes(mod))
      );

      if (recipe) {
        const blockId = `${block.location.x}_${block.location.y}_${block.location.z}`;
        let progress = smeltingProgress.get(blockId) || 0;

        progress += 1; // Increment progress each tick

        if (progress >= recipe.cookTime) {
          // Smelting complete
          const outputItem = container.getItem(6); // Output slot

          if (!outputItem || (outputItem.typeId === recipe.output && outputItem.amount < outputItem.maxAmount)) {
            // Can add output
            const newOutput = outputItem ?
              { typeId: recipe.output, amount: outputItem.amount + 1 } :
              { typeId: recipe.output, amount: 1 };

            container.setItem(6, new ItemStack(recipe.output, newOutput.amount));

            // Remove input
            if (inputItem.amount > 1) {
              container.setItem(slot, new ItemStack(inputItem.typeId, inputItem.amount - 1));
            } else {
              container.setItem(slot, undefined);
            }

            // Remove modifiers if consumed
            recipe.modifiers.forEach((mod, index) => {
              const modSlot = 4 + index;
              const modItem = container.getItem(modSlot);
              if (modItem && modItem.amount > 1) {
                container.setItem(modSlot, new ItemStack(modItem.typeId, modItem.amount - 1));
              } else {
                container.setItem(modSlot, undefined);
              }
            });
          }

          progress = 0; // Reset progress
        }

        smeltingProgress.set(blockId, progress);
      }
    }
  } catch (error) {
    console.warn("Process smelting error:", error);
  }
}

// Custom component function for smelting logic
function furnaceSmeltingComponent(block) {
  processSmelting(block);
}

// Register the custom components
world.beforeEvents.worldInitialize.subscribe((event) => {
  event.blockComponentRegistry.registerCustomComponent("strawberrymc:furnace_temperature", {
    onTick: furnaceTemperatureComponent
  });

  event.blockComponentRegistry.registerCustomComponent("strawberrymc:furnace_interact", {
    onPlayerInteract: furnaceInteractComponent
  });

  event.blockComponentRegistry.registerCustomComponent("strawberrymc:furnace_smelting", {
    onTick: furnaceSmeltingComponent
  });
});
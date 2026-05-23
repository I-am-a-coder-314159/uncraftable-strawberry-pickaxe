/* credits to namuwiki for the image
package com.nobigcorps.strawberrymc.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceScreen.class)
public abstract class FurnaceScreenMixin {

    // 1. Define the path to your custom visual background texture sheet
    @Unique
    private static final ResourceLocation CUSTOM_FURNACE_GUI =
            ResourceLocation.fromNamespaceAndPath("strawberrymc", "textures/gui/custom_furnace.png");

    // 2. Inject your custom texture right at the end of the background rendering cycle
    @Inject(method = "renderBg", at = @At("TAIL"))
    private void drawCustomFurnaceBackground(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY, CallbackInfo ci) {
        // Cast the current mixin target to access the standard screen size coordinates
        AbstractFurnaceScreen<?> screen = (AbstractFurnaceScreen<?>) (Object) this;

        int x = (screen.width - 176) / 2;  // Centers your custom window horizontally
        int y = (screen.height - 166) / 2; // Centers your custom window vertically

        // 3. OVERWRITE THE VANILLA GRAPHIC with your custom PNG image
        // This blits your custom strawberry furnace design over the original grey box
        guiGraphics.blit(CUSTOM_FURNACE_GUI, x, y, 0, 0, 176, 166);
    }
}
*/
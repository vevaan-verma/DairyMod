package net.cheesestudios.dairymod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DairyCondenserScreen extends AbstractContainerScreen<DairyCondenserMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DairyMod.MOD_ID, "textures/gui/dairy_condenser_gui.png");

    public DairyCondenserScreen(DairyCondenserMenu pMenu, Inventory pPlayerInventory, Component pTitle) {

        super(pMenu, pPlayerInventory, pTitle);

    }

    @Override
    protected void init() {

        super.init();

        // removes inventory & title labels
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        // for centering gui
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);

    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {

        /*
        GUIDE:
            - pX: x coordinate
            - pY: yCoordinate
            - pUOffset: horizontal offset to arrow
            - pVOffset: vertical offset to arrow
            - pUWidth: arrow width
            - pVHeight: arrow height; menu.getScaledProgress() draws part of arrow proportional to progress
         */

        // COORDINATES & OFFSETS DEFINED BY TOP LEFT CORNER
        if (menu.isCrafting())
            guiGraphics.blit(TEXTURE, x + 81, y + 34, 176, 0, 15, menu.getScaledProgress());

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);

    }
}

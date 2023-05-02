package dev.acrosx.gui;

import net.minecraft.client.gui.GuiButton;

public class PresetGuiButton extends GuiButton {
    public PresetGuiButton(int id, int x, int y) {
        super(id, x, y, 100, 20, "Presets");
    }

    public void drawButton(net.minecraft.client.Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
    }
}

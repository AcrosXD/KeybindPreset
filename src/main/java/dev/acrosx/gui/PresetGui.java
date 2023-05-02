package dev.acrosx.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;

public class PresetGui extends GuiScreen {
    Configuration config = dev.acrosx.KeybindPresetMod.config;

    public void initGui() {
        super.initGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawWorldBackground(1);

        int i = 0;
        for (String name : config.getCategoryNames()) {
            {
                this.buttonList.add(new GuiButton(i+806, this.width / 2 - 100, this.height / 2 - 100 + i * 25, name));
                i++;
            }
        }


        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

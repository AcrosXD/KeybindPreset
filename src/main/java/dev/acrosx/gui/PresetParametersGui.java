package dev.acrosx.gui;

import dev.acrosx.KeybindPresetMod;
import dev.acrosx.interfaces.IPresetParametersGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class PresetParametersGui extends GuiScreen implements IPresetParametersGui {

    private final Configuration config = KeybindPresetMod.config;
    private final GuiButton button0 = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 100, "NONE");
    //to finish
    private String presetName;

    public PresetParametersGui(String presetName) {
        super();
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(button0);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if(button.id == 0){
            button0.displayString = "";
            int newKey = Keyboard.getEventKey();
            config.get(presetName, "presetActivation", newKey).getInt();
        }

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }
}

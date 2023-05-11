package dev.acrosx.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.io.IOException;

public class AddPresetGui extends GuiScreen {

    private GuiTextField textField;

    public void initGui() {
        super.initGui();

        this.textField = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 130, 200, 20);
        //yes that a lot of parameters
        this.textField.setMaxStringLength(64);
        this.textField.setFocused(true);
        this.textField.setEnabled(true);
        this.textField.setCanLoseFocus(true);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawWorldBackground(1);
        this.textField.drawTextBox();

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 - 80, "Create Preset"));

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        super.keyTyped(typedChar, keyCode);
        this.textField.textboxKeyTyped(typedChar, keyCode);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            int createPreset = dev.acrosx.functions.CreatePreset.createPreset(this.textField.getText());
            if (createPreset == 1) {
                mc.fontRendererObj.drawString("Preset already exists!", this.width / 2 - 100, this.height / 2 - 100, 0xff0000);
            }
        }
    }
}

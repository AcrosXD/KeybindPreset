package dev.acrosx.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class AddPresetGui extends GuiScreen {

    private GuiTextField text;

    public void initGui() {
        super.initGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawWorldBackground(1);
        this.text = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 100, 200, 20);
        this.text.setFocused(true);
        this.text.setEnabled(true);
        this.text.drawTextBox();

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 - 150, "Create Preset"));

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            int createPreset = dev.acrosx.functions.CreatePreset.createPreset(this.text.getText());
            if (createPreset == 1) {
                //display a message saying that the preset allready exists
                mc.fontRendererObj.drawString("Preset already exists!", this.width / 2 - 100, this.height / 2 - 100, 0xff0000);
            }
        }
    }
}

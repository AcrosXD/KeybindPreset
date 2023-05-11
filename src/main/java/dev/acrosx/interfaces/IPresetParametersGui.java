package dev.acrosx.interfaces;

import net.minecraft.client.gui.GuiButton;

import java.io.IOException;

public interface IPresetParametersGui{
        void initGui();
        void drawScreen(int mouseX, int mouseY, float partialTicks);
        void actionPerformed(GuiButton button);
        void keyTyped(char typedChar, int keyCode) throws IOException;
    }

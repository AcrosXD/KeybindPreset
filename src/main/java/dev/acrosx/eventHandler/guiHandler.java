package dev.acrosx.eventHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiControls;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class guiHandler {
    @SubscribeEvent
    public void onOpenGui(GuiOpenEvent event) {
        if(event.gui != null && event.gui.getClass() == GuiControls.class)
        {
            event.gui = new dev.acrosx.gui.CustomGuiControls(null, Minecraft.getMinecraft().gameSettings);
        }
    }
}

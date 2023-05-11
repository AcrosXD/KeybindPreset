package dev.acrosx.eventHandler;

import dev.acrosx.functions.SetPreset;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyHandler {
    @SubscribeEvent
    public void onKeyPress(KeyInputEvent event) {
    for(KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings){
            if(keyBinding.getKeyCategory().equals("KeybindPreset")){
                if(keyBinding.isPressed()){
                    SetPreset.setPreset(keyBinding.getKeyDescription());
                }
            }
        }
    }
}

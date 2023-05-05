package dev.acrosx.functions;

import dev.acrosx.KeybindPresetMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.config.Configuration;

public class CreatePreset {
    public static int createPreset(String name) {
        Configuration config = KeybindPresetMod.config;
        if (config.hasCategory(name)) {
            //Code error: 1, the preset allready exist 
            return 1;
        }
        config.getCategory(name);
        for (KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings) {
            config.get(name, keyBinding.getKeyDescription(), keyBinding.getKeyCode());
        }
        config.save();

        return 0;
    }
}

package dev.acrosx.functions;

import dev.acrosx.KeybindPresetMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;

public class SetPreset {
    public static void setPreset(String name) {
        Configuration config = KeybindPresetMod.config;
        if (!config.hasCategory(name)) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new net.minecraft.util.ChatComponentText(EnumChatFormatting.YELLOW + "Preset does not exist!"));
            return;
        }

        for (String key : config.getCategory(name).getValues().keySet()) {
            //convert string to keybinding
            for (KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings) {
                if (keyBinding.getKeyDescription().equals(key)) {
                    Minecraft.getMinecraft().gameSettings.setOptionKeyBinding(keyBinding, config.get(name, key, 0).getInt());
                }
            }
        }
        Minecraft.getMinecraft().gameSettings.saveOptions();
        Minecraft.getMinecraft().gameSettings.loadOptions();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new net.minecraft.util.ChatComponentText(EnumChatFormatting.GRAY + "Setting preset '" + EnumChatFormatting.DARK_AQUA + name + EnumChatFormatting.GRAY + "' with current keybindings set."));
    }

}

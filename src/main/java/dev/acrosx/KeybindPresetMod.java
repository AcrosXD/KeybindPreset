package dev.acrosx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;

@Mod(modid = KeybindPresetMod.MODID, version = KeybindPresetMod.VERSION)
public class KeybindPresetMod {
    public static final String MODID = "keybindpreset";
    public static final String VERSION = "1.0";

    public static File configFile = new File(Minecraft.getMinecraft().mcDataDir, "keybindpreset.toml");
    public static Configuration config = new Configuration(configFile);
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new dev.acrosx.commands.CreatePresetCommand());
        ClientCommandHandler.instance.registerCommand(new dev.acrosx.commands.GetCurrentPresetConfig());
        ClientCommandHandler.instance.registerCommand(new dev.acrosx.commands.SetPresetCommand());

        MinecraftForge.EVENT_BUS.register(new dev.acrosx.eventHandler.guiHandler());
        MinecraftForge.EVENT_BUS.register(new dev.acrosx.eventHandler.KeyHandler());

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        config.load();

        for (String name : config.getCategoryNames()) {
            int keyBind = config.get(name, "presetActivation", 0).getInt();
            KeyBinding keyBinding = new KeyBinding(name, keyBind, "KeybindPreset");
            Minecraft.getMinecraft().gameSettings.keyBindings = (KeyBinding[]) java.util.Arrays.copyOf(Minecraft.getMinecraft().gameSettings.keyBindings, Minecraft.getMinecraft().gameSettings.keyBindings.length + 1);
            Minecraft.getMinecraft().gameSettings.keyBindings[Minecraft.getMinecraft().gameSettings.keyBindings.length - 1] = keyBinding;
        }

    }



    @Mod.EventHandler
    public void postInit(FMLInitializationEvent event) {
        if (config.hasChanged()) {
            config.save();
        }
    }
}

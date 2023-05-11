# KeybindPreset
A forge mod to set presets for keybindings on Minecraft.

## Todo
- Improve/Finish GUI part
- Add sensitivity save
- Add utility to the Preset gui
- Patch bugs

## Known bugs
At this time there is one major bug known, you cannot set the keybind for a new preset until you resart the game. This is happening because the game cannot register new keybinds for the running instance. 

We can resolve this by adding our own keybind system for the mod or find a way to force the game to register new keybinds. The way it's built allow us to push the keybinds in the keybind array like that:

```java
    //config.getCategoryNames() stands for the name of each preset in the config
    for (String name : config.getCategoryNames()) {
            int keyBind = config.get(name, "presetActivation", 0).getInt();
            KeyBinding keyBinding = new KeyBinding(name, keyBind, "KeybindPreset");
            Minecraft.getMinecraft().gameSettings.keyBindings = java.util.Arrays.copyOf(Minecraft.getMinecraft().gameSettings.keyBindings, Minecraft.getMinecraft().gameSettings.keyBindings.length + 1);
            Minecraft.getMinecraft().gameSettings.keyBindings[Minecraft.getMinecraft().gameSettings.keyBindings.length - 1] = keyBinding;
        }
```

For creating our own keybinds system this is not possible without modify the config system, the current way create confict with the keybinds allready registred in the configuration.

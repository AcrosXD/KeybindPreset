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
    for(){
        //i'am in class, i forgot the code 💀
    }
```

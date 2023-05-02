package dev.acrosx.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandBase;

public class GetCurrentPresetConfig extends CommandBase {

    @Override
    public String getCommandName() {
        return "getcurrentpresetconfig";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(net.minecraft.command.ICommandSender sender) {
        return "/getcurrentpresetconfig";
    }

    @Override
    public void processCommand(net.minecraft.command.ICommandSender sender, String[] args) {
        sender.addChatMessage(new net.minecraft.util.ChatComponentText("Getting current preset config..."));
        for (KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings) {
            sender.addChatMessage(new net.minecraft.util.ChatComponentText(keyBinding.getKeyDescription() + " = " + keyBinding.getKeyCode() + ", "));
        }
    }
}

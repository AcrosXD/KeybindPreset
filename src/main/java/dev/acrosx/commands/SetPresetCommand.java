package dev.acrosx.commands;

import net.minecraft.command.CommandBase;

public class SetPresetCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "setpreset";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(net.minecraft.command.ICommandSender sender) {
        return "/setpreset <name>";
    }

    @Override
    public void processCommand(net.minecraft.command.ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.addChatMessage(new net.minecraft.util.ChatComponentText("Usage: /setpreset <name>"));
            return;
        }
        String name = args[0];
        dev.acrosx.functions.SetPreset.setPreset(name);
    }
}

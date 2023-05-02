package dev.acrosx.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.util.EnumChatFormatting;

public class CreatePresetCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "createpreset";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(net.minecraft.command.ICommandSender sender) {
        return "/createpreset <name>";
    }

    @Override
    public void processCommand(net.minecraft.command.ICommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.addChatMessage(new net.minecraft.util.ChatComponentText("Usage: /createpreset <name>"));
            return;
        }
        String name = args[0];
        int createPreset = dev.acrosx.functions.CreatePreset.createPreset(name);
        if (createPreset == 1) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new net.minecraft.util.ChatComponentText(EnumChatFormatting.YELLOW + "Preset already exists!"));
            return;
        }
        sender.addChatMessage(new net.minecraft.util.ChatComponentText(EnumChatFormatting.GRAY + "Creating preset '" + EnumChatFormatting.DARK_GREEN + name + EnumChatFormatting.GRAY + "' with current keybindings set."));
    }
}

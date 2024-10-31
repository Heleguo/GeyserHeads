package me.lz5509.geyserheads.utils;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Utils {
    public static void sendUsage(@NotNull CommandSender sender) {
        sender.sendMessage("Usage:");
        sender.sendMessage("  /geyserheads load <slimefun|head-database|hand> [HeadDatabase-ID]");
        sender.sendMessage("Example: /geyserheads load slimefun");
        sender.sendMessage("<> = required, [] = optional");
    }
}

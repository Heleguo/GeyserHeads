package me.lz5509.geyserslimefunhead.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import me.lz5509.geyserslimefunhead.GSFH;
import me.lz5509.geyserslimefunhead.libs.NBTEditor;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args[0].toLowerCase().equals("load")){
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    player.sendMessage(GSFH.getInstance().getName() + "You don't have op!");
                    return true;
                }
                player.sendMessage(GSFH.getInstance().getName() + " Loading Heads.");
            }else {
                GSFH.getInstance().getLogger().info("Loading Heads.");
            }
            Config config = new Config(new File("plugins/Geyser-Spigot/custom-skulls.yml"));
            List<String> l = config.getStringList("skin-hashes");
            for (SlimefunItem i : Slimefun.getRegistry().getAllSlimefunItems()){
                if (i.getItem().getType() == Material.PLAYER_HEAD){
                    GSFH.getInstance().getLogger().info(i.getAddon().getName() + " - " + i.getItemName());
                    String texturecode = NBTEditor.getTexture(i.getItem());
                    String[] ts = texturecode.split("/");
                    if (l.contains(ts[ts.length-1])) continue;
                    l.add(ts[ts.length-1]);
                }
            }
            Collections.sort(l);
            Collections.reverse(l);
            config.setValue("skin-hashes", l);
            config.save();
            GSFH.getInstance().getLogger().info("Loaded " + l.size() + " items.");
            GSFH.getInstance().getLogger().info("Loaded heads, please reload to load heads from Geyser-Spigot");

        }
        return true;
    }
}
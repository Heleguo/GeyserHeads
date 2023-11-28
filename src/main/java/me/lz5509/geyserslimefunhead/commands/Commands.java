package me.lz5509.geyserslimefunhead.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.lz5509.geyserslimefunhead.GSFH;
import me.lz5509.geyserslimefunhead.libs.NBTEditor;

import java.io.File;
import java.util.ArrayList;
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
            List<ItemStack> itemlist = new ArrayList<ItemStack>();
            Slimefun.getRegistry().getAllSlimefunItems().forEach(i -> {if (i.getItem().getType() == Material.PLAYER_HEAD) itemlist.add(i.getItem());});
            Slimefun.getLocalization().getLanguages().forEach(i -> {itemlist.add(i.getItem());});
            itemlist.add(Slimefun.getLocalization().getDefaultLanguage().getItem());
            itemlist.add(new CustomItemStack(SlimefunUtils.getCustomHead("e952d2b3f351a6b0487cc59db31bf5f2641133e5ba0006b18576e996a0293e52")));
            for (HeadTexture ht : HeadTexture.values()) if(!itemlist.contains(ht.getAsItemStack())) itemlist.add(ht.getAsItemStack());
            for (ItemStack i : itemlist){
                if (!i.getItemMeta().getDisplayName().isEmpty()) GSFH.getInstance().getLogger().info(i.getItemMeta().getDisplayName());
                else GSFH.getInstance().getLogger().info("Language Heads or Other Slimefun Heads...");
                String texturecode = NBTEditor.getTexture(i);
                if (texturecode==null) continue;
                String[] ts = texturecode.split("/");
                if (!l.contains(ts[ts.length-1])) l.add(ts[ts.length-1]);
            }
            String[] i = {"player-names", "player-uuids", "player-profiles"};
            for(String t : i) if(config.getStringList(t).isEmpty()) config.setValue(t, new ArrayList<>());
            config.setValue("skin-hashes", l);
            config.save();
            GSFH.getInstance().getLogger().info("Loaded " + l.size() + " items.");
            GSFH.getInstance().getLogger().info("Loaded heads, please reload to load heads from Geyser-Spigot");
        }
        return true;
    }
}
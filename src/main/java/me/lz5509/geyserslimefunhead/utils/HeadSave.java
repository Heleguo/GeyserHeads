package me.lz5509.geyserslimefunhead.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import me.lz5509.geyserslimefunhead.GSFH;

public class HeadSave {
    private GSFH gsfh = GSFH.getInstance();
    private FileConfiguration config;

    public enum HeadType {
        PLAYER_PROFILES("player-profiles"),
        SKIN_HASHES("skin-hashes");

        private final String name;       
        private HeadType(String s) {
            name = s;
        }
        public boolean equalsName(String otherName) {
            return name.equals(otherName);
        }
        public String toString() {
            return this.name;
        }
    }

    public void save(HeadType type, List<String> items){
        Plugin GeyserSpigot = Bukkit.getPluginManager().getPlugin("Geyser-Spigot");
        if (GeyserSpigot != null) config = GeyserSpigot.getConfig();
        else {
            config = gsfh.getConfig();
            gsfh.getLogger().warning("Not Found Geyser-Spigot, save data to plugins/Geyser-Heads/config.yml");
        }
        config.set(type.name(), items);
    }
}

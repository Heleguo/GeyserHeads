package me.lz5509.geyserheads.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import me.lz5509.geyserheads.GeyserHeads;

public class Heads {
    private GeyserHeads geyserheads = GeyserHeads.getInstance();
    private YamlConfiguration config;
    private File geysercustomskulls;
    private int added_heads = 0;

    public Heads() {
        Plugin GeyserSpigot = Bukkit.getPluginManager().getPlugin("Geyser-Spigot");
        if (GeyserSpigot != null) {
            geysercustomskulls = new File(GeyserSpigot.getDataFolder(), "custom-skulls.yml");
        }
        else {
            geysercustomskulls = new File(geyserheads.getDataFolder(), "config.yml");
            geyserheads.getLogger().warning("Not Found Geyser-Spigot, save data to plugins/Geyser-Heads/config.yml");
        }
        config = YamlConfiguration.loadConfiguration(geysercustomskulls);
    }

    public enum HeadType {
        PLAYER_USERNAMES("player-usernames"),
        PLAYER_UUIDS("player-uuids"),
        PLAYER_PROFILES("player-profiles"),
        SKIN_HASHES("skin-hashes");

        private final String name;       
        private HeadType(@NotNull String s) {
            name = s;
        }
        public boolean equalsName(@NotNull String otherName) {
            return name.equals(otherName);
        }
        public String toString() {
            return this.name;
        }
    }
    public List<String> get_heads(@NotNull HeadType type) {
        return config.getStringList(type.toString());
    }

    public void add(@NotNull HeadType type, @NotNull ItemStack item) {
        if (!(item.getItemMeta() instanceof SkullMeta)) return;
        String texturecode = NBTEditor.getTexture(item);
        if (texturecode == null) return;
        String[] ts = texturecode.split("/");
        texturecode = ts[ts.length - 1];

        List<String> items = this.get_heads(type);
        if (items.contains(texturecode)) return;
        items.add(texturecode);
        config.set(type.toString(), items);
        added_heads++;
    }

    public void add(@NotNull HeadType type, @NotNull String texturecode) {
        List<String> items = this.get_heads(type);
        if (items.contains(texturecode)) return;
        items.add(texturecode);
        config.set(type.toString(), items);
        added_heads++;
    }

    public void save(@NotNull CommandSender sender) {
        try {
            for (HeadType ht : HeadType.values()) if (config.getStringList(ht.toString()).isEmpty()) config.set(ht.toString(), new ArrayList<>());
            config.save(geysercustomskulls);
            sender.sendMessage("Saved " + added_heads + " Heads");
        } catch (IOException e) {
            sender.sendMessage("Failed to save heads");
        }
    }
}

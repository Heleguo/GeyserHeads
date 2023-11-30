package me.lz5509.geyserslimefunhead;

import org.bukkit.plugin.java.JavaPlugin;

import me.lz5509.geyserslimefunhead.commands.Commands;

public class GSFH extends JavaPlugin {
    private static GSFH instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getLogger().info("********************");
        this.getLogger().info("      GSFH - v" + this.getDescription().getVersion());
        this.getLogger().info("********************");
        this.getCommand("gsfh").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {}

    public static GSFH getInstance(){
        return instance;
    }
}

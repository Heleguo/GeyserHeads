package me.lz5509.geyserslimefunhead;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.lz5509.geyserslimefunhead.commands.Commands;

public class GSFH extends JavaPlugin implements SlimefunAddon {
    public static GSFH instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getLogger().info("********************");
        this.getLogger().info("*     GSFH - v" + this.getPluginVersion() + "      *");
        this.getLogger().info("********************");
        this.getCommand("gsfh").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {}

    @Override
    public String getBugTrackerURL() {
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static GSFH getInstance(){
        return instance;
    }
}

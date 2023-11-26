package me.lz5509.geyserslimefunhead;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.lz5509.geyserslimefunhead.commands.Commands;

public class GSFH extends JavaPlugin implements SlimefunAddon {
    private static GSFH instance;

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
        return "https://github.com/hahaa13/Geyser-Slimefun-Heads/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static GSFH getInstance(){
        return instance;
    }
}

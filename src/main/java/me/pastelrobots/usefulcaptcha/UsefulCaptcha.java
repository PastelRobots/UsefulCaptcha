package me.pastelrobots.usefulcaptcha;

import me.pastelrobots.usefulcaptcha.listeners.MenuListener;
import me.pastelrobots.usefulcaptcha.listeners.PlayerListener;
import me.pastelrobots.usefulcaptcha.utils.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class UsefulCaptcha extends JavaPlugin {
    public static UsefulCaptcha plugin;

    private ConfigFile mainConfig;

    private int kickTime;

    public static UsefulCaptcha getInstance() {
        return plugin;
    }

    public ConfigFile getMainConfig() {
        return this.mainConfig;
    }

    public int getKickTime() {
        return this.kickTime;
    }

    public void setKickTime(int kickTime) {
        this.kickTime = kickTime;
    }

    public void onEnable() {
        plugin = this;
        this.mainConfig = new ConfigFile(this, "config");
        this.kickTime = ConfigFile.getInt("kick.timeout");
        if (getConfig().getBoolean("console.enabled-msg")) {
            Bukkit.getLogger().info(ChatColor.GOLD + "=============================================");
            Bukkit.getLogger().info(ChatColor.GREEN + "UsefulCaptcha has been turned on!");
            Bukkit.getLogger().info(ChatColor.GREEN + "If you need help or support join the" + ChatColor.BLUE + " discord.");
            Bukkit.getLogger().info(ChatColor.BLUE + "discord.gg/VtgcZRnmMR");
            Bukkit.getLogger().info(ChatColor.GOLD + "=============================================");
        }
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    public void onDisable() {
        if (getConfig().getBoolean("console.enabled-msg")) {
            Bukkit.getLogger().info(ChatColor.RED + "=============================================");
            Bukkit.getLogger().info(ChatColor.BLUE + "UsefulCaptcha has been turned off!");
            Bukkit.getLogger().info(ChatColor.BLUE + "If you need help or support join the" + ChatColor.BLUE + " discord.");
            Bukkit.getLogger().info(ChatColor.DARK_BLUE + "discord.gg/VtgcZRnmMR");
            Bukkit.getLogger().info(ChatColor.BLUE + "Bye-bye!");
            Bukkit.getLogger().info(ChatColor.RED + "=============================================");
        }
        plugin = null;
    }
}

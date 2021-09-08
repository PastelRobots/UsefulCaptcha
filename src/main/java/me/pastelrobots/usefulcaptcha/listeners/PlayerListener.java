package me.pastelrobots.usefulcaptcha.listeners;

import me.pastelrobots.usefulcaptcha.UsefulCaptcha;
import me.pastelrobots.usefulcaptcha.gui.CaptchaMenu;
import me.pastelrobots.usefulcaptcha.utils.ConfigFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerListener implements Listener {
    public static List<UUID> passedCaptcha = new ArrayList<>();

    public BukkitRunnable laterTask;

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        (new BukkitRunnable() {
            public void run() {
                (new CaptchaMenu()).open(p);
            }
        }).runTaskLater(UsefulCaptcha.getInstance(), 5L);
        (new BukkitRunnable() {
            public void run() {
                PlayerListener.this.laterTask = this;
                if (!PlayerListener.passedCaptcha.contains(p.getUniqueId()))
                    p.kickPlayer(ConfigFile.getString("kick.timeout-message"));
            }
        }).runTaskLater(UsefulCaptcha.getInstance(), UsefulCaptcha.getInstance().getKickTime() * 20L);
        (new BukkitRunnable() {
            public void run() {
                if (PlayerListener.passedCaptcha.contains(p.getUniqueId())) {
                    p.sendMessage(ConfigFile.getString("success"));
                    cancel();
                    if (PlayerListener.this.laterTask != null)
                        PlayerListener.this.laterTask.cancel();
                }
                if (!p.isOnline())
                    cancel();
            }
        }).runTaskTimer(UsefulCaptcha.getInstance(), 5L, 5L);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        if (passedCaptcha.contains(event.getPlayer().getUniqueId())) {
            passedCaptcha.remove(event.getPlayer().getUniqueId());
            if (this.laterTask != null)
                this.laterTask.cancel();
        }
    }
}

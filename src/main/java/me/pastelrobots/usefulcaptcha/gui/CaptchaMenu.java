package me.pastelrobots.usefulcaptcha.gui;

import me.pastelrobots.usefulcaptcha.listeners.PlayerListener;
import me.pastelrobots.usefulcaptcha.utils.ConfigFile;
import me.pastelrobots.usefulcaptcha.utils.InventoryUtil;
import me.pastelrobots.usefulcaptcha.utils.ItemBuilder;
import me.pastelrobots.usefulcaptcha.utils.RandomInteger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CaptchaMenu implements Menu {
    Random rand = new Random();

    int materialsArrayLength = (Material.values()).length;

    Material randomMaterial = Material.values()[this.rand.nextInt(this.materialsArrayLength)];

    private final Inventory inventory = Bukkit.createInventory(this, 27, ConfigFile.getString("success-title"));

    public void open(Player player) {
        update();
        player.openInventory(this.inventory);
    }

    public void update() {
        this.inventory.clear();
        this.inventory.setItem(RandomInteger.randomInteger(0, 26), (new ItemBuilder(this.randomMaterial)).setDurability(13).setName("&aClick the " + this.randomMaterial.toString().toLowerCase().replace('_', ' ') + "!").get());
        InventoryUtil.fillInventory(this.inventory);
    }

    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player)e.getPlayer();
        Inventory closeInventory = e.getInventory();
        Inventory topInventory = e.getView().getTopInventory();
        if (!topInventory.equals(this.inventory))
            return;
        if (topInventory.equals(closeInventory) &&
                !PlayerListener.passedCaptcha.contains(player.getUniqueId()))
            player.kickPlayer(ConfigFile.getString("kick.wrong-answer"));
    }

    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        Inventory clickedInventory = e.getClickedInventory();
        Inventory topInventory = e.getView().getTopInventory();
        if (!topInventory.equals(this.inventory))
            return;
        if (topInventory.equals(clickedInventory)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
                return;
            if (!e.getCurrentItem().hasItemMeta())
                return;
            if (e.getCurrentItem().equals((new ItemBuilder(this.randomMaterial)).setDurability(13).setName("&aClick the " + this.randomMaterial.toString().toLowerCase().replace('_', ' ') + "!").get())) {
                PlayerListener.passedCaptcha.add(p.getUniqueId());
                p.closeInventory();
            } else {
                p.kickPlayer(ConfigFile.getString("kick.wrong-answer"));
            }
        } else if ((!topInventory.equals(clickedInventory) && e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) || e.getAction() == InventoryAction.COLLECT_TO_CURSOR) {
            e.setCancelled(true);
        }
    }

    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}

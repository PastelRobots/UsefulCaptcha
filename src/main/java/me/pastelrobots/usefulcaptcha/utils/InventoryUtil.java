package me.pastelrobots.usefulcaptcha.utils;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.Objects;

public final class InventoryUtil {
    public static void fillInventory(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null || Objects.requireNonNull(inventory.getItem(i)).getType().equals(Material.AIR))
                inventory.setItem(i, (new ItemBuilder(Material.RED_STAINED_GLASS_PANE)).setDurability(7).setName("&cNot here!").get());
        }
    }

    public static boolean clickedTopInventory(InventoryDragEvent event) {
        InventoryView view = event.getView();
        Inventory topInventory = view.getTopInventory();
        boolean result = false;
        int size = topInventory.getSize();
        for (Integer entry : event.getNewItems().keySet()) {
            if (entry >= size)
                continue;
            result = true;
        }
        return result;
    }
}

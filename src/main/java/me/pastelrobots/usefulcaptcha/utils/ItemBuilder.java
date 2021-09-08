package me.pastelrobots.usefulcaptcha.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
  private final ItemStack itemStack;

  public ItemBuilder(Material material) {
    this.itemStack = new ItemStack(material);
  }

  public ItemBuilder(ItemStack itemStack) {
    this.itemStack = itemStack;
  }

  public static ItemBuilder copyOf(ItemBuilder builder) {
    return new ItemBuilder(builder.get());
  }

  public static ItemBuilder copyOf(ItemStack item) {
    return new ItemBuilder(item);
  }

  public static void rename(ItemStack stack, String name) {
    ItemMeta meta = stack.getItemMeta();
    assert meta != null;
    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
    stack.setItemMeta(meta);
  }

  public static ItemStack createItem(Material material, String name) {
    ItemStack item = new ItemStack(material);
    ItemMeta meta = item.getItemMeta();
    assert meta != null;
    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
    item.setItemMeta(meta);
    return item;
  }

  public static ItemStack createItem(Material material, String name, int amount) {
    ItemStack item = new ItemStack(material, amount);
    ItemMeta meta = item.getItemMeta();
    assert meta != null;
    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
    item.setItemMeta(meta);
    return item;
  }

  public static ItemStack createItem(Material material, String name, int amount, short damage) {
    ItemStack item = new ItemStack(material, amount, damage);
    ItemMeta meta = item.getItemMeta();
    assert meta != null;
    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
    item.setItemMeta(meta);
    return item;
  }

  public ItemBuilder setAmount(int amount) {
    this.itemStack.setAmount(Math.min(amount, 64));
    return this;
  }

  public ItemBuilder setName(String name) {
    ItemMeta meta = this.itemStack.getItemMeta();
    assert meta != null;
    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
    this.itemStack.setItemMeta(meta);
    return this;
  }

  public ItemBuilder addLoreLine(String name) {
    ItemMeta meta = this.itemStack.getItemMeta();
    assert meta != null;
    List<String> lore = meta.getLore();
    if (lore == null)
      lore = new ArrayList<>();
    lore.add(ChatColor.translateAlternateColorCodes('&', name));
    meta.setLore(lore);
    this.itemStack.setItemMeta(meta);
    return this;
  }

  public ItemBuilder setLore(List<String> lore) {
    List<String> toSet = new ArrayList<>();
    ItemMeta meta = this.itemStack.getItemMeta();
    lore.forEach(string -> toSet.add(ChatColor.translateAlternateColorCodes('&', string)));
    assert meta != null;
    meta.setLore(toSet);
    this.itemStack.setItemMeta(meta);
    return this;
  }

  public ItemBuilder setDurability(int durability) {
    this.itemStack.setDurability((short)durability);
    return this;
  }

  public ItemBuilder setData(int data) {
    this.itemStack.setData(new MaterialData(this.itemStack.getType(), (byte)data));
    return this;
  }

  public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
    this.itemStack.addUnsafeEnchantment(enchantment, level);
    return this;
  }

  public ItemBuilder addEnchantment(Enchantment enchantment) {
    this.itemStack.addUnsafeEnchantment(enchantment, 1);
    return this;
  }

  public ItemBuilder setType(Material material) {
    this.itemStack.setType(material);
    return this;
  }

  public ItemBuilder clearLore() {
    ItemMeta meta = this.itemStack.getItemMeta();
    assert meta != null;
    meta.setLore(new ArrayList<>());
    this.itemStack.setItemMeta(meta);
    return this;
  }

  public ItemBuilder clearEnchantments() {
    this.itemStack.getEnchantments().keySet().forEach(this.itemStack::removeEnchantment);
    return this;
  }

  public ItemBuilder setColor(Color color) {
    if (this.itemStack.getType() != Material.LEATHER_BOOTS && this.itemStack.getType() != Material.LEATHER_CHESTPLATE && this.itemStack.getType() != Material.LEATHER_HELMET && this.itemStack.getType() != Material.LEATHER_LEGGINGS)
      throw new IllegalArgumentException("color() only applicable for leather armor.");
    LeatherArmorMeta meta = (LeatherArmorMeta)this.itemStack.getItemMeta();
    assert meta != null;
    meta.setColor(color);
    this.itemStack.setItemMeta(meta);
    return this;
  }

  public ItemBuilder setOwner(String owner) {
    SkullMeta meta = (SkullMeta)this.itemStack.getItemMeta();
    assert meta != null;
    meta.setOwner(owner);
    this.itemStack.setItemMeta(meta);
    return this;
  }

  public ItemStack get() {
    return this.itemStack;
  }
}

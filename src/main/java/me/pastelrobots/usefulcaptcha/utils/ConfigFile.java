package me.pastelrobots.usefulcaptcha.utils;

import me.pastelrobots.usefulcaptcha.UsefulCaptcha;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ConfigFile {

    public ConfigFile(UsefulCaptcha main, String config) {
    }

    public static int getInt(String intpath) {
        return UsefulCaptcha.plugin.getConfig().getInt(intpath);
    }

    public static boolean getBoolean(String booleanpath) {
        return UsefulCaptcha.plugin.getConfig().getBoolean(booleanpath);
    }

    public static double getDouble(String doublepath) {
        return UsefulCaptcha.plugin.getConfig().getDouble(doublepath);
    }

    public static String getString(String stringpath) {
        return UsefulCaptcha.plugin.getConfig().getString(stringpath);
    }

    public static Boolean getObject(String objectpath, Class<Boolean> clazz) {
        return UsefulCaptcha.plugin.getConfig().getObject(objectpath, clazz);
    }

    public static List<Boolean> getBooleanList(String booleanpath) {
        return UsefulCaptcha.plugin.getConfig().getBooleanList(booleanpath);
    }

    public static List<String> getStringList(String stringpath) {
        return UsefulCaptcha.plugin.getConfig().getStringList(stringpath);
    }

    public static List<Byte> getByteList(String bytepath) {
        return UsefulCaptcha.plugin.getConfig().getByteList(bytepath);
    }

    public static List<Character> getCharacterList(String characterpath) {
        return UsefulCaptcha.plugin.getConfig().getCharacterList(characterpath);
    }

    public static Color getColour(String colourpath) {
        return UsefulCaptcha.plugin.getConfig().getColor(colourpath);
    }

    public static List<Double> getDoubleList(String doublepath) {
        return UsefulCaptcha.plugin.getConfig().getDoubleList(doublepath);
    }

    public static List<Float> getFloatList(String floatpath) {
        return UsefulCaptcha.plugin.getConfig().getFloatList(floatpath);
    }

    public static List<Integer> getIntList(String intpath) {
        return UsefulCaptcha.plugin.getConfig().getIntegerList(intpath);
    }

    public static ItemStack getItemStack(String itemstackpath) {
        return UsefulCaptcha.plugin.getConfig().getItemStack(itemstackpath);
    }

    public static Vector getVector(String vecpath) {
        return UsefulCaptcha.plugin.getConfig().getVector(vecpath);
    }

    public static Set<String> getKeys(boolean deep) {
        return UsefulCaptcha.plugin.getConfig().getKeys(deep);
    }

    public static Map<String, Object> getValues(boolean deep) {
        return UsefulCaptcha.plugin.getConfig().getValues(deep);
    }

    public static List<Short> getShortList(String shortpath) {
        return UsefulCaptcha.plugin.getConfig().getShortList(shortpath);
    }

    public static OfflinePlayer getOfflinePlayer(String offlpath) {
        return UsefulCaptcha.plugin.getConfig().getOfflinePlayer(offlpath);
    }

    public static List<Map<?, ?>> getMapList(String mappath) {
        return UsefulCaptcha.plugin.getConfig().getMapList(mappath);
    }

    public static List<Long> getLongList(String longpath) {
        return UsefulCaptcha.plugin.getConfig().getLongList(longpath);
    }

    public static Long getLong(String longpath) {
        return UsefulCaptcha.plugin.getConfig().getLong(longpath);
    }

    public static Location getLocation(String locpath) {
        return UsefulCaptcha.plugin.getConfig().getLocation(locpath);
    }

    public static ConfigurationSection getConfigSection(String cspath) {
        return UsefulCaptcha.plugin.getConfig().getConfigurationSection(cspath);
    }

}

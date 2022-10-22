package com.badbones69.crazywarps.api;

import com.badbones69.crazywarps.CrazyWarps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrazyUtils {

    public String colorize(String toColorize) {
        return ChatColor.translateAlternateColorCodes('&', toColorize);
    }

    public String stripColor(String toStrip) {
        return ChatColor.stripColor(toStrip);
    }

    private Player isPlayerOnline(String name) {
        return Bukkit.getServer().getPlayer(name);
    }

    public void sendMessage(CommandSender commandSender, String string) {
        commandSender.sendMessage(colorize(getPrefix() + " " + string));
    }

    private void performCommand(Player player, String CMD) {
        player.performCommand(CMD);
    }

    private boolean isOnline(String name, CommandSender p) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        p.sendMessage(colorize("&cThat player is not online at this time."));
        return false;
    }

    public String getPrefix() {
        return colorize(CrazyWarps.getCrazyWarps().getConfig().getString("Settings.WarpPrefix"));
    }

    public Integer getVersion() {
        String ver = Bukkit.getServer().getClass().getPackage().getName();
        ver = ver.substring(ver.lastIndexOf('.') + 1);
        ver = ver.replaceAll("_", "").replaceAll("R", "").replaceAll("v", "");
        return Integer.parseInt(ver);
    }

    private boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            return false;
        }
        return true;
    }

    public ItemStack makeItem(Material material, int amount, int type, String name) {
        ItemStack itemStack = new ItemStack(material, amount, (short) type);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(name));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore) {
        ArrayList<String> l = new ArrayList<String>();
        ItemStack item = new ItemStack(material, amount, (short) type);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(colorize(name));
        for (String L : lore) l.add(colorize(L));
        m.setLore(l);
        item.setItemMeta(m);
        return item;
    }

    private ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore, Map<Enchantment, Integer> enchants) {
        ItemStack item = new ItemStack(material, amount, (short) type);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(name);
        m.setLore(lore);
        item.setItemMeta(m);
        item.addUnsafeEnchantments(enchants);
        return item;
    }

    private ItemStack addLore(ItemStack item, String i) {
        ArrayList<String> lore = new ArrayList<String>();
        ItemMeta m = item.getItemMeta();

        if (item.getItemMeta().hasLore()) {
            lore.addAll(item.getItemMeta().getLore());
        }

        lore.add(i);
        m.setLore(lore);
        item.setItemMeta(m);
        return item;
    }
}
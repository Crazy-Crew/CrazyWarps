package me.badbones69.categorywarps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Api {
	
	public static String color(String msg) {
		msg = msg.replaceAll("(&([a-f0-9]))", "\u00A7$2");
		msg = msg.replaceAll("&l", ChatColor.BOLD + "");
		msg = msg.replaceAll("&o", ChatColor.ITALIC + "");
		msg = msg.replaceAll("&k", ChatColor.MAGIC + "");
		msg = msg.replaceAll("&n", ChatColor.UNDERLINE + "");
		return msg;
	}
	public static String removeColor(String msg) {
		msg = ChatColor.stripColor(msg);
		return msg;
	}
	public static Integer getVersion() {
		String ver = Bukkit.getServer().getClass().getPackage().getName();
		ver = ver.substring(ver.lastIndexOf('.') + 1);
		ver = ver.replaceAll("_", "").replaceAll("R", "").replaceAll("v", "");
		return Integer.parseInt(ver);
	}
	public static ItemStack makeItem(Material material, int amount, int type, String name) {
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(color(name));
		item.setItemMeta(m);
		return item;
	}
	public static ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore) {
		ArrayList<String> l = new ArrayList<String>();
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(color(name));
		for(String L : lore) l.add(color(L));
		m.setLore(l);
		item.setItemMeta(m);
		return item;
	}
	public static ItemStack makeItem(Material material, int amount, int type, String name, List<String> lore, Map<Enchantment, Integer> enchants) {
		ItemStack item = new ItemStack(material, amount, (short) type);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(name);
		m.setLore(lore);
		item.setItemMeta(m);
		item.addUnsafeEnchantments(enchants);
		return item;
	}
	public static ItemStack addLore(ItemStack item, String i) {
		ArrayList<String> lore = new ArrayList<String>();
		ItemMeta m = item.getItemMeta();
		if(item.getItemMeta().hasLore()) {
			lore.addAll(item.getItemMeta().getLore());
		}
		lore.add(i);
		m.setLore(lore);
		item.setItemMeta(m);
		return item;
	}
	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		}catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	public static Player getPlayer(String name) {
		return Bukkit.getServer().getPlayer(name);
	}
	public static Location getLoc(Player player) {
		return player.getLocation();
	}
	public static void runCMD(Player player, String CMD) {
		player.performCommand(CMD);
	}
	public static boolean isOnline(String name, CommandSender p) {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(player.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		p.sendMessage(color("&cThat player is not online at this time."));
		return false;
	}
	public static boolean hasPermission(Player player, String perm) {
		if(!player.hasPermission("CategoryWarps." + perm)) {
			player.sendMessage(color("&cYou need permission to use this command."));
			return false;
		}
		return true;
	}
	public static boolean hasPermission(CommandSender sender, String perm) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(!player.hasPermission("CategoryWarps." + perm)) {
				player.sendMessage(color("&cYou need permission to use this command."));
				return false;
			}else {
				return true;
			}
		}else {
			return true;
		}
	}
}
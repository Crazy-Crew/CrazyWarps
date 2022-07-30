package me.badbones69.categorywarps;

import com.massivestats.MassiveStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {
	
	public static Main plugin;
	public static SettingsManager settings = SettingsManager.getInstance();
	ArrayList<Player> Teleport = new ArrayList<>();
	HashMap<String, Integer> Task = new HashMap<>();
	HashMap<String, Integer> Tel = new HashMap<>();
	HashMap<String, Integer> Check = new HashMap<>();
	
	@Override
	public void onEnable() {
		settings.setup(this);
		new MassiveStats(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args) {
		if(commandLable.equalsIgnoreCase("WarpHelp")) {
			if(!Api.hasPermission(sender, "WarpHelp")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to that command!");
				return true;
			}
			String Prefix = getConfig().getString("Settings.WarpPrefix").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("&l", ChatColor.BOLD + "");
			sender.sendMessage(Prefix + ChatColor.GOLD + "A list of all the Warp Commands!");
			sender.sendMessage(Api.color("&8- &3/WarpHelp &9Lists all Warp Commands."));
			sender.sendMessage(Api.color("&8- &3/Warp <Warp Name> [Player] &9Warps you to that warp."));
			sender.sendMessage(Api.color("&8- &3/WarpList &9Lists all Warps and there categories."));
			sender.sendMessage(Api.color("&8- &3/SetWarp <Category> <Warp Name> &9Create a new Warp."));
			sender.sendMessage(Api.color("&8- &3/DelWarp <Warp Name> &9Delete a warp."));
			sender.sendMessage(Api.color("&8- &3/WarpReload &9Reload the Config.yml and Data.yml."));
			sender.sendMessage(Api.color("&8- &3/WarpImport &9Will import all essentials warps."));
			sender.sendMessage(Api.color("&8- &3/MoveWarp <Warp> <Category> &9Move a warp to a category."));
			return true;
		}
		if(commandLable.equalsIgnoreCase("Warp")) {
			if(!Api.hasPermission(sender, "Warp")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to that command!");
				return true;
			}
			if(args.length == 0) {
				sender.sendMessage(ChatColor.BLUE + "Please do /Warp <Warp Name> [Player]");
				return true;
			}
			if(!(sender instanceof Player)) {
				sender.sendMessage(Api.color("&cYou must be player to use that command."));
				return true;
			}
			final Player player = (Player) sender;
			final String Prefix = getConfig().getString("Settings.WarpPrefix").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("&l", ChatColor.BOLD + "");
			int delay = getConfig().getInt("Settings.TeleportDelay");
			String warpname = args[0];
			Boolean triger = false;
			for(String category : settings.getData().getConfigurationSection("Categories").getKeys(false)) {
				for(String warpcheck : settings.getData().getConfigurationSection("Categories." + category).getKeys(false)) {
					if(warpcheck.equalsIgnoreCase(warpname)) {
						String world = settings.getData().getString("Categories." + category + "." + warpcheck + ".world");
						int X = settings.getData().getInt("Categories." + category + "." + warpcheck + ".X");
						int Y = settings.getData().getInt("Categories." + category + "." + warpcheck + ".Y");
						int Z = settings.getData().getInt("Categories." + category + "." + warpcheck + ".Z");
						float pitch = settings.getData().getInt("Categories." + category + "." + warpcheck + ".Pitch");
						float yaw = settings.getData().getInt("Categories." + category + "." + warpcheck + ".Yaw");
						World W = Bukkit.getServer().getWorld(world);
						final Location loc = new Location(W, X, Y, Z);
						loc.setPitch(pitch);
						loc.setYaw(yaw);
						String warpmessage = getConfig().getString("Settings.WarpMessage")
						.replaceAll("(&([a-f0-9]))", "\u00A7$2")
						.replaceAll("&l", ChatColor.BOLD + "")
						.replaceAll("%Warp%", warpcheck)
						.replaceAll("%Delay%", Integer.toString(delay));
						if(args.length == 2) {
							if(Api.hasPermission(player, "TPOthers")) {
								if(Api.isOnline(args[1], sender)) {
									Player target = Api.getPlayer(args[1]);
									TelepotingParticles.TeleportAway(target);
									target.teleport(loc.add(.5, 0, .5));
									return true;
								}
							}
						}
						if(!(player.hasPermission("CategoryWarps." + category)) && getConfig().getBoolean("Settings.PerCategoryPermissions")) {
							player.sendMessage(Prefix + ChatColor.RED + "You do not have permission to that Category!");
							return true;
						}
						if(!(player.hasPermission("CategoryWarps." + category + "." + warpcheck)) && getConfig().getBoolean("Settings.PerWarpPermissions")) {
							player.sendMessage(Prefix + ChatColor.RED + "You do not have permission to that Warp!");
							return true;
						}
						if(Teleport.contains(player)) {
							player.sendMessage(Prefix + ChatColor.GOLD + "You are already warping!");
							return true;
						}
						player.sendMessage(Prefix + warpmessage);
						Teleport.add(player);
						final String worldC = player.getWorld().getName();
						final int x = player.getLocation().getBlockX();
						final int y = player.getLocation().getBlockY();
						final int z = player.getLocation().getBlockZ();
						World w = Bukkit.getServer().getWorld(worldC);
						final Location locP = new Location(w, x, y, z);
						Check.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
							String world2 = player.getWorld().getName();
							int x2 = player.getLocation().getBlockX();
							int y2 = player.getLocation().getBlockY();
							int z2 = player.getLocation().getBlockZ();
							World w2 = Bukkit.getServer().getWorld(world2);
							final Location loc2 = new Location(w2, x2, y2, z2);
							if(!(locP.equals(loc2))) {
								player.sendMessage(Prefix + Api.color(settings.getConfig().getString("Settings.WarpCancelMessage")));
								Teleport.remove(player);
								if(Check.containsKey(player.getName())) {
									Bukkit.getScheduler().cancelTask(Check.get(player.getName()));
								}
								if(Task.containsKey(player.getName())) {
									Bukkit.getScheduler().cancelTask(Task.get(player.getName()));
								}
								if(Tel.containsKey(player.getName())) {
									Bukkit.getScheduler().cancelTask(Tel.get(player.getName()));
								}
							}
						}, 0, 3));
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Random")) {
							TelepotingParticles.Random(player);
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Sparkles")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.Sparkles(player), 0, 8));
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Smoke")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.Smoke(player), 0, 8));
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Spell")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.Spell(player), 0, 2));
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Enchant")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.Enchant(player), 0, 8));
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Village")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.Village(player), 0, 2));
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Magic")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.Magic(player), 0, 2));
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Specks")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.Specks(player), 0, 2));
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("Music")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.Music(player), 0, 2));
						}
						if(getConfig().getString("Settings.TeleportingParticle").equalsIgnoreCase("RainbowCloud")) {
							Task.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> TelepotingParticles.RainbowCloud(player), 0, 3));
						}
						Tel.put(player.getName(), Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
							TelepotingParticles.TeleportAway(player);
							player.teleport(loc.add(.5, 0, .5));
							Teleport.remove(player);
							if(Check.containsKey(player.getName())) {
								Bukkit.getScheduler().cancelTask(Check.get(player.getName()));
							}
							if(Task.containsKey(player.getName())) {
								Bukkit.getScheduler().cancelTask(Task.get(player.getName()));
							}
							if(Tel.containsKey(player.getName())) {
								Bukkit.getScheduler().cancelTask(Tel.get(player.getName()));
							}
						}, delay * 20));
						return true;
					}
				}
			}
			if(!triger) {
				player.sendMessage(Prefix + ChatColor.RED + "That Warp does not exist! Do /WarpList to see all Warps!");
				return true;
			}
		}
		if(commandLable.equalsIgnoreCase("WarpList") || commandLable.equalsIgnoreCase("Warps")) {
			if(!Api.hasPermission(sender, "WarpList")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to that command!");
				return true;
			}
			String warp = "";
			String Prefix = getConfig().getString("Settings.WarpPrefix").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("&l", ChatColor.BOLD + "");
			if(settings.getData().getConfigurationSection("Categories") == null) {
				sender.sendMessage(Prefix + ChatColor.RED + "There are no warps set!");
				settings.getData().set("Categories.clear", null);
				settings.saveData();
				return true;
			}
			if(settings.getData().getConfigurationSection("Categories").getKeys(false).isEmpty()) {
				sender.sendMessage(Prefix + ChatColor.RED + "There are no warps set!");
				return true;
			}
			String cat = "";
			String deny = getConfig().getString("Settings.DeniedCategoryFormat").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("&l", ChatColor.BOLD + "");
			for(String category : settings.getData().getConfigurationSection("Categories").getKeys(false)) {
				if(settings.getData().getConfigurationSection("Categories." + category).getKeys(false).isEmpty()) {
					settings.getData().set("Categories." + category, null);
					settings.saveData();
					continue;
				}
				cat += getConfig().getString("Settings.CategoryFormat")
				.replaceAll("(&([a-f0-9]))", "\u00A7$2")
				.replaceAll("&l", ChatColor.BOLD + "")
				.replaceAll("%Category%", category);
				
				for(String warpname : settings.getData().getConfigurationSection("Categories." + category).getKeys(false)) {
					if(!(hasPermission(sender, "CategoryWarps." + category + "." + warpname)) && getConfig().getBoolean("Settings.PerWarpPermissions")) {
						warp += getConfig().getString("Settings.NoPermWarpFormat")
						.replaceAll("(&([a-f0-9]))", "\u00A7$2")
						.replaceAll("&l", ChatColor.BOLD + "")
						.replaceAll("%Warp%", warpname);
					}
					if(hasPermission(sender, "CategoryWarps." + category + "." + warpname) || !getConfig().getBoolean("Settings.PerWarpPermissions")) {
						warp += getConfig().getString("Settings.HasPermWarpFormat")
						.replaceAll("(&([a-f0-9]))", "\u00A7$2")
						.replaceAll("&l", ChatColor.BOLD + "")
						.replaceAll("%Warp%", warpname);
					}
				}
				if(!(hasPermission(sender, "CategoryWarps." + category)) && getConfig().getBoolean("Settings.PerCategoryPermissions")) {
					cat = getConfig().getString("Settings.WarpListCategoryDeny")
					.replaceAll("(&([a-f0-9]))", "\u00A7$2")
					.replaceAll("&l", ChatColor.BOLD + "")
					.replaceAll("%Category%", cat)
					.replaceAll("%Deny%", deny);
				}
				if(hasPermission(sender, "CategoryWarps." + category) || !getConfig().getBoolean("Settings.PerCategoryPermissions")) {
					cat = getConfig().getString("Settings.WarpListFormat")
					.replaceAll("(&([a-f0-9]))", "\u00A7$2")
					.replaceAll("&l", ChatColor.BOLD + "")
					.replaceAll("%Category%", cat)
					.replaceAll("%Warp%", warp.substring(0, warp.length() - 2));
				}
				cat += "\n";
				warp = "";
			}
			if(settings.getData().getConfigurationSection("Categories") == null) {
				sender.sendMessage(Prefix + ChatColor.RED + "There are no warps set!");
				settings.getData().set("Categories.clear", null);
				settings.saveData();
				return true;
			}
			if(settings.getData().getConfigurationSection("Categories").getKeys(false).isEmpty()) {
				sender.sendMessage(Prefix + ChatColor.RED + "There are no warps set!");
				return true;
			}
			String top = getConfig().getString("Settings.ListWarp").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("&l", ChatColor.BOLD + "");
			sender.sendMessage(Prefix + top);
			sender.sendMessage(cat);
			return true;
		}
		if(commandLable.equalsIgnoreCase("SetWarp")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Api.color("&cYou must be player to use that command."));
				return true;
			}
			final Player player = (Player) sender;
			Location loc = player.getLocation();
			String world = loc.getWorld().getName();
			int X = loc.getBlockX();
			int Y = loc.getBlockY();
			int Z = loc.getBlockZ();
			float pitch = loc.getPitch();
			float yaw = loc.getYaw();
			String Prefix = getConfig().getString("Settings.WarpPrefix").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("&l", ChatColor.BOLD + "");
			if(!(player.hasPermission("CategoryWarps.SetWarp"))) {
				player.sendMessage(ChatColor.RED + "You do not have permission to that command!");
				return true;
			}
			if(args.length == 0 || args.length == 1) {
				player.sendMessage(ChatColor.BLUE + "Please do /SetWarp <Category> <Warp Name>");
				return true;
			}
			if(args.length >= 2) {
				String categories = args[0];
				String warpname = args[1];
				boolean triger = false;
				
				if(Main.settings.getData().getConfigurationSection("Categories") == null) {
					Main.settings.getData().set("Categories.clear", null);
					Main.settings.saveData();
				}
				
				for(String category : settings.getData().getConfigurationSection("Categories").getKeys(false)) {
					if(category.equalsIgnoreCase(categories)) {
						categories = category;
					}
					for(String warpcheck : settings.getData().getConfigurationSection("Categories." + category).getKeys(false)) {
						if(warpcheck.equalsIgnoreCase(warpname)) {
							triger = true;
						}
					}
				}
				if(triger) {
					player.sendMessage(ChatColor.RED + "The warp " + ChatColor.GOLD + warpname + ChatColor.RED + " all ready exist!");
					player.sendMessage(ChatColor.RED + "Please delete Warp " + ChatColor.GOLD + warpname + ChatColor.RED + " and then set the new warp!");
					return true;
				}
				settings.getData().set("Categories." + categories + "." + warpname + ".world", world);
				settings.getData().set("Categories." + categories + "." + warpname + ".X", X);
				settings.getData().set("Categories." + categories + "." + warpname + ".Y", Y);
				settings.getData().set("Categories." + categories + "." + warpname + ".Z", Z);
				settings.getData().set("Categories." + categories + "." + warpname + ".Pitch", pitch);
				settings.getData().set("Categories." + categories + "." + warpname + ".Yaw", yaw);
				settings.saveData();
				player.sendMessage(Prefix + ChatColor.GOLD + "You have just created " + ChatColor.RED + warpname + ChatColor.GOLD + " in the category " + ChatColor.RED + categories);
			}
			return true;
		}
		if(commandLable.equalsIgnoreCase("DelWarp")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Api.color("&cYou must be player to use that command."));
				return true;
			}
			final Player player = (Player) sender;
			if(!(player.hasPermission("CategoryWarps.DelWarp"))) {
				player.sendMessage(ChatColor.RED + "You do not have permission to that command!");
				return true;
			}
			if(args.length == 0) {
				player.sendMessage(ChatColor.BLUE + "Please do /DelWarp <Warp Name>");
				return true;
			}
			String warpname = args[0];
			String Prefix = getConfig().getString("Settings.WarpPrefix").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("&l", ChatColor.BOLD + "");
			for(String category : settings.getData().getConfigurationSection("Categories").getKeys(false)) {
				for(String warpcheck : settings.getData().getConfigurationSection("Categories." + category).getKeys(false)) {
					if(warpcheck.equalsIgnoreCase(warpname)) {
						settings.getData().set("Categories." + category + "." + warpcheck, null);
						settings.saveData();
						player.sendMessage(Prefix + ChatColor.GOLD + "You have deleted the " + ChatColor.RED + warpcheck + ChatColor.GOLD + " Warp!");
						return true;
					}
				}
			}
			player.sendMessage(Prefix + ChatColor.RED + "That Warp does not exist! Do /WarpList to see all Warps!");
			return true;
		}
		if(commandLable.equalsIgnoreCase("WarpReload")) {
			if(!(hasPermission(sender, "CategoryWarps.Reload"))) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to that command!");
				return true;
			}
			this.reloadConfig();
			settings.reloadConfig();
			String Prefix = getConfig().getString("Settings.WarpPrefix").replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("&l", ChatColor.BOLD + "");
			sender.sendMessage(Prefix + ChatColor.GOLD + "You have succesfully Reloaded Category Warp's Config and Data File!");
		}
		if(commandLable.equalsIgnoreCase("MoveWarp")) {
			if(!Api.hasPermission(sender, "MoveWarp")) return true;
			if(args.length >= 2) {
				String Prefix = getConfig().getString("Settings.WarpPrefix");
				FileConfiguration data = settings.getData();
				String warp = args[0];
				String category = args[1];
				String C = "";
				boolean T = false;
				for(String cat : data.getConfigurationSection("Categories").getKeys(false)) {
					if(cat.equalsIgnoreCase(category)) {
						category = cat;
					}
					for(String wa : data.getConfigurationSection("Categories." + cat).getKeys(false)) {
						if(wa.equalsIgnoreCase(warp)) {
							warp = wa;
							C = cat;
							T = true;
						}
					}
				}
				if(T) {
					if(!C.equals(category)) {
						data.set("Categories." + category + "." + warp, data.get("Categories." + C + "." + warp));
						data.set("Categories." + C + "." + warp, null);
						settings.saveData();
					}
					sender.sendMessage(Api.color(Prefix + "&7You have set " + warp + " to the category " + category + "."));
					return true;
				}else {
					sender.sendMessage(Api.color(Prefix + "&cThat Warp does not exist! Do /WarpList to see all Warps!"));
					return true;
				}
			}
		}
		if(commandLable.equalsIgnoreCase("WarpImport")) {
			if(!Api.hasPermission(sender, "Import")) return true;
			Plugin ess = Bukkit.getPluginManager().getPlugin("Essentials");
			String Prefix = getConfig().getString("Settings.WarpPrefix");
			FileConfiguration data = settings.getData();
			if(ess != null) {
				for(String warp : new File(ess.getDataFolder() + "/warps").list()) {
					FileConfiguration file = YamlConfiguration.loadConfiguration(new File(ess.getDataFolder() + "/warps/", warp));
					warp = warp.substring(0, warp.length() - 4);
					data.set("Categories.Essentials." + warp + ".world", file.get("world"));
					data.set("Categories.Essentials." + warp + ".X", file.get("x"));
					data.set("Categories.Essentials." + warp + ".Y", file.get("y"));
					data.set("Categories.Essentials." + warp + ".Z", file.get("z"));
					data.set("Categories.Essentials." + warp + ".Pitch", file.get("yaw"));
					data.set("Categories.Essentials." + warp + ".Yaw", file.get("pitch"));
					settings.saveData();
				}
				sender.sendMessage(Api.color(Prefix + "&7You have just imported all essentials warps into the Essentials Category."));
			}else {
				sender.sendMessage(Api.color(Prefix) + "&cYou need essentials installed to use this command.");
			}
		}
		return false;
	}
	
	private boolean hasPermission(CommandSender sender, String perm) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			return player.hasPermission(perm);
		}else {
			return true;
		}
	}
	
}
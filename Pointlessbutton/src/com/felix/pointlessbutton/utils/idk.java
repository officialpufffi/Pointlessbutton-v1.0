package com.felix.pointlessbutton.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.felix.pointlessbutton.main.Main;

public class idk {
	
	static FileConfiguration config = Main.getPlugin().getConfig();
	
	/*public static List<Material> buttons = Arrays.asList(Material.ACACIA_BUTTON, Material.BIRCH_BUTTON, Material.CRIMSON_BUTTON,
			Material.DARK_OAK_BUTTON, Material.JUNGLE_BUTTON, Material.OAK_BUTTON, Material.POLISHED_BLACKSTONE_BUTTON,
			Material.SPRUCE_BUTTON, Material.STONE_BUTTON, Material.WARPED_BUTTON);*/
	
	public static boolean isAdmin(Player p) {
		return (p.hasPermission("pointlessbutton.admin") || p.hasPermission("pointlessbutton.*"));
	}
	
	public static boolean canUse(Player p) {
		return (p.hasPermission("pointlessbutton.use") || p.hasPermission("pointlessbutton.admin") || p.hasPermission("pointlessbutton.*"));
	}
	
	public static void sendNoPerm(Player p) {
		p.sendMessage("§cYou're not allowed to do this.");
	}
	
	public static void pluginReload() {
		Main.data.reloadConfig();
		Bukkit.getPluginManager().getPlugin(Main.getPlugin().getName()).reloadConfig();
	}
	
	public static void sendWrongUsage(Player p) {
		p.sendMessage(Main.prefix + "§cUsage: §6/pointless setbutton 1/2/3/4");
		p.sendMessage(Main.prefix + "§cUsage: §6/pointless setholo");
	}
	
	public static void sendHelp(Player p) {
		p.sendMessage("§8------------------------------------");
		p.sendMessage(Main.prefix + "§cCommands: ");
		p.sendMessage(Main.prefix + "§6/pointless credits");
		p.sendMessage(Main.prefix + "§6/pointless setbutton 1/2/3/4");
		p.sendMessage(Main.prefix + "§6/pointless setholo");
		p.sendMessage(" ");
		p.sendMessage(Main.prefix + "§4Permissions: ");
		p.sendMessage(Main.prefix + "§6pointlessbutton.admin");
		p.sendMessage(Main.prefix + "§6pointlessbutton.use");
		p.sendMessage("§8------------------------------------");
	}
	
	public static void sendCredits(Player p) {
		p.sendMessage("§8------------------------------------");
		p.sendMessage(Main.prefix + "§3Plugin name§8: " + Main.pluginName);
		p.sendMessage(Main.prefix + "§2Plugin version§8: §a" + Main.version);
		p.sendMessage(Main.prefix + "§6Plugin author§8: §ePufffi");
		p.sendMessage(Main.prefix + "§bDiscord§8: §ewww.discord.com/invite/VtrAFyz");
		p.sendMessage(Main.prefix + "§5Twitch§8: §ewww.twitch.tv/official_pufffi");
		p.sendMessage(Main.prefix + "§cYouTube§8: §ewww.youtube.com/channel/UCYnYPHDIm4UYBDqaUBIRtXw");
		p.sendMessage(Main.prefix + "§9Twitter§8: §ehttps://twitter.com/official_pufffi");
		p.sendMessage(Main.prefix + "§6Instagram§8: §ewww.instagram.com/official_pufffi/");
		p.sendMessage(Main.prefix + "§4Help-Command§8: §c/pointless help");
		p.sendMessage("§8------------------------------------");
	}

	public static void setHolo(Player p) {
		config.set("Pointlessbutton.Button.Holo.World", p.getWorld().getName());
		config.set("Pointlessbutton.Button.Holo.X", p.getLocation().getX());
		config.set("Pointlessbutton.Button.Holo.Y", p.getLocation().getY());
		config.set("Pointlessbutton.Button.Holo.Z", p.getLocation().getZ());
		Main.getPlugin().saveConfig();
		createHolo(p);
	}
	
	public static void setButton(Player p, int button) {
		config.set("Pointlessbutton.Button" + button + ".World", p.getWorld().getName());
		config.set("Pointlessbutton.Button" + button + ".X", p.getLocation().getBlockX());
		config.set("Pointlessbutton.Button" + button + ".Y", p.getLocation().getBlockY());
		config.set("Pointlessbutton.Button" + button + ".Z", p.getLocation().getBlockZ());
		Main.getPlugin().saveConfig();
	}
	static void createHolo(Player p) {
		ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
		as.setGravity(false);
		as.setCanPickupItems(false);
		as.setCustomName("§7- " + getClicks() + " -");
		as.setCustomNameVisible(true);
		as.setVisible(false);
	}
	
	public static Location getButton(int button) {
		World w = Bukkit.getWorld(config.getString("Pointlessbutton.Button" + button + ".World"));
		double x = config.getDouble("Pointlessbutton.Button" + button + ".X");
		double y = config.getDouble("Pointlessbutton.Button" + button + ".Y");
		double z = config.getDouble("Pointlessbutton.Button" + button + ".Z");
		Location loc = new Location(w, x, y, z);
		return loc;
	}
	
	public static Location getHolo()
	{
		World w = Bukkit.getWorld(config.getString("Pointlessbutton.Button.Holo.World"));
		double x = config.getDouble("Pointlessbutton.Button.Holo.X");
		double y = config.getDouble("Pointlessbutton.Button.Holo.Y");
		double z = config.getDouble("Pointlessbutton.Button.Holo.Z");
		Location loc = new Location(w, x, y, z);
		return loc;
	}
	
	public static int getClicks() {
		int clicks = Main.data.getConfig().getInt("Server.Pointlessbutton.globalClicks");		
		return clicks;
	}
	
	public static void setGlobalClicks() {
		int clicks = Main.data.getConfig().getInt("Server.Pointlessbutton.globalClicks");	
		Main.data.getConfig().set("Server.Pointlessbutton.globalClicks", clicks+1);
		Main.data.saveConfig();
	}
	
	public static void setPlayerClicks(Player p) {
		int clicks = Main.data.getConfig().getInt("PlayerData." + p.getUniqueId() + ".pointlessbuttonClicks");
		Main.data.getConfig().set("PlayerData." + p.getUniqueId() + ".pointlessbuttonClicks", clicks+1);
		Main.data.saveConfig();
	}
	
	public static void update(Player p) {
		setGlobalClicks();
		setPlayerClicks(p);
		Main.data.saveConfig();
		refreshHolo();
		//getHolo();
	}
	
	public static void refreshHolo() {
		World w = getHolo().getWorld();
		for(ArmorStand as : w.getEntitiesByClass(ArmorStand.class))
		{
			Location asLoc = new Location(w, as.getLocation().getX(), as.getLocation().getY(), as.getLocation().getZ(), 0, 0);
			if(asLoc.equals(getHolo()))
			{
				if(as.getName().startsWith("§7- ") && as.getName().endsWith(" -"))
					as.setCustomName("§7- " + getClicks() + " -");
				else
					return;
			}
			else
				return;
		}
	}
}

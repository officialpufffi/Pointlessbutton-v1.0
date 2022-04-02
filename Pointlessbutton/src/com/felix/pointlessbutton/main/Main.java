package com.felix.pointlessbutton.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.felix.pointlessbutton.cmds.PointlessSetCmd;
import com.felix.pointlessbutton.files.DataManager;
import com.felix.pointlessbutton.listener.PointlessListener;
import com.felix.pointlessbutton.utils.PointlessTabCompleter;

public class Main extends JavaPlugin
{
	private static Main plugin;
	
	public static String prefix = "§7[§3Pointlessbutton§7] ";
	public static String pluginName = "§6Pointlessbutton ";
	public static String version = "v0.1-mc1.18.1";
	
	public static DataManager data;
	
	@Override
	public void onEnable()
	{
		Bukkit.getConsoleSender().sendMessage(prefix + "§aPointlessbutton enabled. §7(§b" + version + "§7)");
		
		plugin = this;
		data = new DataManager(this);
		
		getCommand("pointless").setExecutor(new PointlessSetCmd());
		getCommand("pointless").setTabCompleter(new PointlessTabCompleter());
		
		PluginManager plMa = Bukkit.getPluginManager();
		plMa.registerEvents(new PointlessListener(), this);
		
		//super.onEnable();
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getConsoleSender().sendMessage(prefix + "§cPointlessbutton disabled. §7(§b" + version + "§7)");
		//super.onDisable();
	}
	
	public static Main getPlugin()
	{
		return plugin;
	}
}

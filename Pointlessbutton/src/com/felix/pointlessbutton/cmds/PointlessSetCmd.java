package com.felix.pointlessbutton.cmds;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.felix.pointlessbutton.main.Main;
import com.felix.pointlessbutton.utils.idk;

public class PointlessSetCmd implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args)
	{
		Player p = (Player) sender;
		if(idk.isAdmin(p))
		{
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("setholo"))
				{
					idk.setHolo(p);
					p.sendMessage(Main.prefix + "§7Holo created.");
				}
				else if(args[0].equalsIgnoreCase("reload"))
				{
					idk.pluginReload();
					p.sendMessage(Main.prefix + "§7Plugin &asuccessfully &7reloaded!");
				}
				else if(args[0].equalsIgnoreCase("help"))
				{
					idk.sendHelp(p);
				}
				else if(args[0].equalsIgnoreCase("credits"))
				{
					idk.sendCredits(p);
				}
				else
					idk.sendWrongUsage(p);
			}
			else if(args.length == 2)
			{
				if(args[0].equalsIgnoreCase("setbutton"))
				{
					List<Material> buttons = Arrays.asList(Material.ACACIA_BUTTON, Material.BIRCH_BUTTON, Material.CRIMSON_BUTTON,
							Material.DARK_OAK_BUTTON, Material.JUNGLE_BUTTON, Material.OAK_BUTTON, Material.POLISHED_BLACKSTONE_BUTTON,
							Material.SPRUCE_BUTTON, Material.STONE_BUTTON, Material.WARPED_BUTTON);
					Material pLocBlock = p.getLocation().getBlock().getType();
					
					if(buttons.contains(pLocBlock))
					{
						if(args[1].equalsIgnoreCase("1")) {
							idk.setButton(p, 1);
							p.sendMessage(Main.prefix + "§7Set button 1");
						}
						else if(args[1].equalsIgnoreCase("2")) {
							idk.setButton(p, 2);
							p.sendMessage(Main.prefix + "§7Set button 2");
						}
						else if(args[1].equalsIgnoreCase("3")) {
							idk.setButton(p, 3);
							p.sendMessage(Main.prefix + "§7Set button 3");
						}
						else if(args[1].equalsIgnoreCase("4")) {
							idk.setButton(p, 4);
							p.sendMessage(Main.prefix + "§7Set button 4");
						}
						else
							idk.sendWrongUsage(p);
					}
					else
						p.sendMessage(Main.prefix + "§cYour position must be a button!");
				}
				else
					idk.sendWrongUsage(p);
			}
			else
				idk.sendWrongUsage(p);
		}
		else
			idk.sendNoPerm(p);
		return false;
	}
}

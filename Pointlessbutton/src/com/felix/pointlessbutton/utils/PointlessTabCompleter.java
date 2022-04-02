package com.felix.pointlessbutton.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class PointlessTabCompleter implements TabCompleter
{
	List<String> arguments = new ArrayList<String>();
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args)
	{
		if(arguments.isEmpty()) {
			arguments.add("credits"); arguments.add("help");
			arguments.add("setholo"); arguments.add("setbutton");
			arguments.add("reload");
		}
		
		List<String> result = new ArrayList<String>();
		if(args.length == 1) {
			for(String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase()))
					result.add(a);
			}
			return result;
		}
		return null;
	}
}

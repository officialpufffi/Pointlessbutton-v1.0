package com.felix.pointlessbutton.listener;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.felix.pointlessbutton.utils.idk;

public class PointlessListener implements Listener
{
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		if(b != null)
		{
			List<Material> buttons = Arrays.asList(Material.ACACIA_BUTTON, Material.BIRCH_BUTTON, Material.CRIMSON_BUTTON,
					Material.DARK_OAK_BUTTON, Material.JUNGLE_BUTTON, Material.OAK_BUTTON, Material.POLISHED_BLACKSTONE_BUTTON,
					Material.SPRUCE_BUTTON, Material.STONE_BUTTON, Material.WARPED_BUTTON);
			Material clicksBlock = b.getType();
			
			if(buttons.contains(clicksBlock))
			{
				if(idk.canUse(p))
				{
					Location bLoc = b.getLocation();
					if(bLoc.equals(idk.getButton(1)))
						idk.update(p);	
					else if(bLoc.equals(idk.getButton(2)))
						idk.update(p);
					else if(bLoc.equals(idk.getButton(3)))
						idk.update(p);
					else if(bLoc.equals(idk.getButton(4)))
						idk.update(p);
					else
						return;
				}
				else
					idk.sendNoPerm(p);
			}
			else
				return;
		}
	}
}

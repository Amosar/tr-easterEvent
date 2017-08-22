package com.trafalcraft.easter;

import java.util.List;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.trafalcraft.easter.util.ManageLocation;

public class PlayerListener implements Listener{

	@EventHandler
		public void onPLayerPickupEgg(PlayerPickupItemEvent e){
			if(e.getItem().getItemStack().getItemMeta().getDisplayName()!=null){
				if(e.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("§4Oeuf de paque")){
					e.setCancelled(true);
					if(!Main.getInstance().getConfig().getStringList("eggs."+ManageLocation.LocationToString(e.getItem().getLocation())).contains(e.getPlayer().getName())){
						System.out.println(ManageLocation.LocationToString(e.getItem().getLocation()));
						List<String> temp = Main.getInstance().getConfig().getStringList("eggs."+ManageLocation.LocationToString(e.getItem().getLocation()));
						temp.add(e.getPlayer().getName());
						Main.getInstance().getConfig().set("eggs."+ManageLocation.LocationToString(e.getItem().getLocation()),temp);
						Main.getInstance().saveConfig();
						e.getPlayer().sendMessage("succès");
					}
				}
			}
		}
	
	@EventHandler	
		public void onEggsDespawn(ItemDespawnEvent e){
			if(e.getEntity() instanceof Item){
				if(e.getEntity().getItemStack().getItemMeta().getDisplayName() == null){
					return;
				}
				if(e.getEntity().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("§4Oeuf de paque")){
					e.setCancelled(true);
				}
			}
		}
	
	@EventHandler
	public void onItemBouge(ItemMergeEvent e){
		if(e.getTarget().getItemStack().getItemMeta().getDisplayName()!=null){
			if(e.getTarget().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("§4Oeuf de paque")){
				e.setCancelled(true);
			}
		}
	}
		
}

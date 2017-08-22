package com.trafalcraft.easter;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.easter.util.ManageLocation;

public class Main extends JavaPlugin{
	private static Main instance;
	
	public void onEnable(){
		instance = this;
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		instance.getConfig().options().copyDefaults(true);
		instance.saveDefaultConfig();
		instance.reloadConfig();
		
		
	}
	
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[]args){
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("easter")){
				if(args[0].equalsIgnoreCase("add")){
					ArrayList<String> temp = new ArrayList<String>();
					temp.add("=========");
					temp.add("__users__");
					temp.add("=========");
					instance.getConfig().set("eggs."+ManageLocation.LocationToString(p.getLocation()), temp);
					instance.saveConfig();
					ItemStack item = new ItemStack(Material.EGG);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName("§4Oeuf de paque");
					meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
					item.setItemMeta(meta);
					Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), item);
					
					Location loc = p.getLocation();
					loc.setY(loc.getY()-1.2);
					Entity ent = Bukkit.getWorld(p.getWorld().getName()).spawnEntity(loc, EntityType.ARMOR_STAND);
					ent.setCustomName("§4Oeuf de paque");
					((ArmorStand) ent).setVisible(false);
					((ArmorStand) ent).setFallDistance(0);;
					ent.setCustomNameVisible(true);
				}
				if(args[0].equalsIgnoreCase("remove")){
					instance.getConfig().set("eggs."+ManageLocation.LocationToString(p.getLocation()), null);
				}
				if(args[0].equalsIgnoreCase("list")){
					for(String loc:instance.getConfig().getConfigurationSection("eggs").getKeys(false)){
						sender.sendMessage(loc);
					}
					for(Entity ent:p.getWorld().getEntities()){
						if(ent.getCustomName() != null){
							if(ent.getCustomName().equalsIgnoreCase("§4Oeuf de paque")){
								ent.remove();
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public static Main getInstance(){
		return instance;
	}
}

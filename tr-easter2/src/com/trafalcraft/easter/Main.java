package com.trafalcraft.easter;


import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	private static Main instance;
	
	  private static Economy econ = null;
	  private static boolean economy = true;
	  
	public void onEnable(){
		instance = this;
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		if (economy) {
			if (!setupEconomy()) {
				getLogger().severe(String.format("[%s] - No Economy (Vault) dependency found! Disabling Economy.", getDescription().getName()));
				economy = false;
			}
		}
		
	}
	
	public void onDisable(){
		
	}
	
	public static Main getInstance(){
		return instance;
	}
	
	private boolean setupEconomy() {
		//charge l'économie.
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
		//Chargement de l'économie terminé.
	}
	
	public static Economy getEcon(){
		return econ;
	}
}

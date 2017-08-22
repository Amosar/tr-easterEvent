package com.trafalcraft.easter;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Location;

import com.google.common.collect.Maps;
import com.trafalcraft.easter.util.ManageLocation;

public class PlayerManager {
	private class Joueur{
		String name;
		ArrayList<Location> eggsList;
		
		public Joueur(String pseudo){
			name = pseudo;
			for(String eggsLoc:Main.getInstance().getConfig().getConfigurationSection("eggs").getKeys(false)){
				Main.getInstance().getConfig().get("eggs."+eggsLoc);
			}
			
			//eggsList
		}
		
		public ArrayList<Location> getEggsList(){
			return eggsList;
		}
	}
	
	
	private final Map<String, Joueur> PlayerList = Maps.newHashMap();
	
	public void addPlayer(String name, int nbrEggs){
		PlayerList.put(name, new Joueur(name));
	}
	
	public Joueur getPlayer(String name){
		return PlayerList.get(name);
	}
	
	public void removePlayer(String name){
		for(Location loc:PlayerList.get(name).getEggsList()){
			Main.getInstance().getConfig().set(name+"eggs",ManageLocation.LocationToString(loc));	
		}
	}
}

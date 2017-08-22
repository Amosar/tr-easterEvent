package com.trafalcraft.easter;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.trafalcraft.easter.nbt.NbtReflection;

public class PlayerListener implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
		public void onPLayerPickupEgg(PlayerPickupItemEvent e){
 			if(e.getItem().getItemStack().getItemMeta().getDisplayName()!=null){
				if(e.getItem().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("§4Oeuf de paque")){
					e.setCancelled(true);
					e.getItem().remove();
					Random rd = new Random();
					int nombreGenerer = rd.nextInt(100);
					if(nombreGenerer == 1){
						ItemStack item = new ItemStack(Material.ELYTRA);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName("§5Elytra de Pâques");
						List<String> lore = new ArrayList<String>();
						lore.add("Bravo pour votre chance absolu");
						lore.add("Pendant l'évent de Pâques");
						meta.setLore(lore);
						item.setItemMeta(meta);
						Bukkit.getServer().broadcastMessage("§f[§5Pâques§f]>§5 Une Elytra a été trouvé par "+e.getPlayer().getName()+" pendant l'évent de Pâques. GG");
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), item);
					}else if(nombreGenerer >1 && nombreGenerer <= 21){
						e.getPlayer().setLevel(e.getPlayer().getLevel()+20);
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné 20lvls");
						//20lvl
					}else if(nombreGenerer > 21 && nombreGenerer <= 36){
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), new ItemStack(Material.DIAMOND_BLOCK));
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné un block de diamant ramasse le ;)");
						//1 block de diams
					}else if(nombreGenerer > 36 && nombreGenerer <= 46){
						 Main.getEcon().depositPlayer(e.getPlayer(), 700+nombreGenerer);
						 e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin transportait "+(700+nombreGenerer)+"coins sur lui :O");
						//750coins
					}else if(nombreGenerer > 46 && nombreGenerer <= 66){
						ItemStack item = new ItemStack(Material.MONSTER_EGG,1,(short) 101);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName("§5joyeuse paque");
						item.setItemMeta(meta);
						item = NbtReflection.setNewEntityTag(item, EntityType.RABBIT.getName());
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), item);
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné un magnifique oeuf de Pâques ramasse le ;)");
						//oeuf de lapin avec ecrit joueuses paque
					}else if(nombreGenerer > 66 && nombreGenerer <= 71){
						ItemStack item = new ItemStack(Material.SKULL_ITEM);
						item.setDurability((short) 5);
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), item);
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné une magnifique tête de dragon ramasse la ;)");
						//tete de dragon
					}else if(nombreGenerer > 71 && nombreGenerer <= 76){
						ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
						meta.addStoredEnchant(Enchantment.MENDING, 1, false);
						item.setItemMeta(meta);
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), item);
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné un magnifique livre enchant ramasse le ;)");
						//livre enchant avec mending
					}else if(nombreGenerer > 76 && nombreGenerer <= 77){
						ItemStack item = new ItemStack(Material.MONSTER_EGG,1,(short) 120);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName("§5joyeuse Pâques");
						item.setItemMeta(meta);
						item = NbtReflection.setNewEntityTag(item, EntityType.VILLAGER.getName());
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), item);
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné un magnifique oeuf de Pâques ramasse le ;)");
						//oeuf de villageois
					}else if(nombreGenerer > 77 && nombreGenerer <= 82){
						ItemStack item = new ItemStack(Material.GOLDEN_CARROT);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName("§5Carotte chocolaté de lindt");
						List<String> lore = new ArrayList<String>();
						lore.add("Régale toi avec cette magnifique carotte doré");
						lore.add("édition 2016");
						meta.setLore(lore);
						item.setItemMeta(meta);
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné une magnifique carotte doré ramasse la ;)");
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), item);
						//carrote d'or lint collection
					}else if(nombreGenerer > 82 && nombreGenerer <= 99){
						ItemStack item = new ItemStack(Material.CARROT_ITEM);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName("§5Tu t'est fait caroté");
						item.setItemMeta(meta);
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné une magnifique carotte ramasse la ;)");
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), item);
						//la carrote de la carrote
					}else if(nombreGenerer > 99 && nombreGenerer <= 100){
						ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName("§5Cheater de Pâques");
						item.setItemMeta(meta);
						item.setDurability((short) 1);
						e.getPlayer().sendMessage("§f[§5Pâques§f]>§5 Ce lapin t'a donné une magnifique pomme de notch ramasse la ;)");
						Bukkit.getWorld(e.getPlayer().getWorld().getName()).dropItemNaturally(e.getPlayer().getLocation(), item);
						//pomme de notch
					}
				}
			}
		}
	

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent e){
		Random rd = new Random();
		int nbrGenere = rd.nextInt(60);
		if(nbrGenere == 1){
			if(e.getChunk().getEntities().length > 1){
				e.getChunk().getEntities()[0].setCustomName("§9Lapin de paque");
		   		Firework f = (Firework) Bukkit.getWorld(e.getChunk().getEntities()[0].getWorld().getName()).spawn(e.getChunk().getEntities()[0].getLocation(), Firework.class);
				FireworkMeta fm = f.getFireworkMeta();
				
				fm.addEffects(FireworkEffect.builder()
						.flicker(true)
						.trail(true)
						.with(Type.BALL_LARGE)
						.withColor(Color.AQUA)
						.withColor(Color.RED)
						.withColor(Color.AQUA)
						.withColor(Color.RED)
						.build()
						);
				fm.setPower(1);
			}
		}
	}
	
	@EventHandler
	public void onMobSpawn(EntitySpawnEvent e){
		if(e.getEntity().getType() == EntityType.RABBIT){
			Random rd = new Random();
			if(rd.nextInt(30) == 1){
				e.getEntity().setCustomName("§9Lapin de paque");
           		Firework f = (Firework) Bukkit.getWorld(e.getEntity().getWorld().getName()).spawn(e.getEntity().getLocation(), Firework.class);
        		FireworkMeta fm = f.getFireworkMeta();
        		
        		fm.addEffects(FireworkEffect.builder()
        				.flicker(true)
        				.trail(true)
        				.with(Type.BALL_LARGE)
        				.withColor(Color.AQUA)
        				.withColor(Color.RED)
        				.withColor(Color.AQUA)
        				.withColor(Color.RED)
        				.build()
        				);
        		fm.setPower(1);
        		f.setFireworkMeta(fm);
			}
		}else{
			Random rd = new Random();
			int nbrGenere = rd.nextInt(100);
			if(nbrGenere == 1){
				System.out.println("spawn monstre");
				Entity ent = Bukkit.getServer().getWorld(e.getEntity().getWorld().getName()).spawnEntity(e.getEntity().getLocation(), EntityType.RABBIT);
				ent.setCustomName("§9Lapin de paque");
           		Firework f = (Firework) Bukkit.getWorld(e.getEntity().getWorld().getName()).spawn(e.getEntity().getLocation(), Firework.class);
        		FireworkMeta fm = f.getFireworkMeta();
        		
        		fm.addEffects(FireworkEffect.builder()
        				.flicker(true)
        				.trail(true)
        				.with(Type.BALL_LARGE)
        				.withColor(Color.AQUA)
        				.withColor(Color.RED)
        				.withColor(Color.AQUA)
        				.withColor(Color.RED)
        				.build()
        				);
        		fm.setPower(1);
        		f.setFireworkMeta(fm);
			}
		}
	}
	
	@EventHandler
	public void onPlayerKillRabbit(EntityDamageByEntityEvent e){
		if(e.getEntity().getType() == EntityType.RABBIT){
			if(e.getDamager().getType() == EntityType.PLAYER){
				if(((Rabbit) e.getEntity()).getHealth()-e.getDamage() <= 0){
					if(e.getEntity().getCustomName() != null){
						if(e.getEntity().getCustomName().equalsIgnoreCase("§9Lapin de paque")){
							//variable pourcentage
								ItemStack item = new ItemStack(Material.EGG);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName("§4Oeuf de paque");
								meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
								item.setItemMeta(meta);
								Bukkit.getWorld(e.getEntity().getWorld().getName()).dropItemNaturally(e.getEntity().getLocation(), item);
								e.getEntity().remove();
						}
					}
				}
			}
		}
	}
	
		
}

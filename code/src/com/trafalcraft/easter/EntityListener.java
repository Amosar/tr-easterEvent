package com.trafalcraft.easter;

import com.trafalcraft.easter.config.ConfigManager;
import com.trafalcraft.easter.config.Msg;
import com.trafalcraft.easter.util.WorldGuardLink;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class EntityListener implements Listener {
        private static boolean checkIgnoreWorldsAndRegions(Entity e) {
                for (String ignoreWorld : Main.getIgnoredWorlds()) {
                        if (e.getWorld().getName().equals(ignoreWorld)) {
                                return true;
                        }
                }
                return WorldGuardLink.checkAllowedRegion(e.getLocation());
        }

        @EventHandler
        public void onChunkLoad(ChunkLoadEvent e) {
                Random rd = new Random();
                int generateNumber = rd.nextInt(101 - ConfigManager.RABBIT_SPAWN_IN_CHUNK_LUCK.getInt());
                if (generateNumber == 1) {
                        if (e.getChunk().getEntities().length > 1) {
                                Entity ent = e.getChunk().getEntities()[0];
                                if (!checkIgnoreWorldsAndRegions(ent)) {

                                        Entity rabbit = Bukkit.getServer()
                                                .getWorld(ent.getWorld().getName())
                                                .spawnEntity(ent.getLocation(), EntityType.RABBIT);
                                        rabbit.setCustomName("§r§r" + Msg.EASTER_BUNNY_NAME);
                                        rabbit.setCustomNameVisible(true);
                                        Firework f = Bukkit.getWorld(rabbit.getWorld().getName()).spawn(
                                                rabbit.getLocation(), Firework.class);
                                        FireworkMeta fm = f.getFireworkMeta();

                                        fm.addEffects(FireworkEffect.builder()
                                                .flicker(true)
                                                .trail(true)
                                                .with(FireworkEffect.Type.BALL_LARGE)
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
        }

        @EventHandler
        public void onMobSpawn(EntitySpawnEvent e) {
                if (checkIgnoreWorldsAndRegions(e.getEntity())) {
                        return;
                }
                if (e.getEntity().getType() == EntityType.RABBIT) {
                        Random rd = new Random();
                        int random = rd.nextInt(101 - ConfigManager.RABBIT_SPAWN_LUCK.getInt());
                        if (random == 1) {
                                e.getEntity().setCustomName("§r§r" + Msg.EASTER_BUNNY_NAME);
                                e.getEntity().setCustomNameVisible(true);
                                Firework f = Bukkit.getWorld(e.getEntity().getWorld().getName())
                                        .spawn(e.getEntity().getLocation(), Firework.class);
                                FireworkMeta fm = f.getFireworkMeta();

                                fm.addEffects(FireworkEffect.builder()
                                        .flicker(true)
                                        .trail(true)
                                        .with(FireworkEffect.Type.BALL_LARGE)
                                        .withColor(Color.AQUA)
                                        .withColor(Color.RED)
                                        .withColor(Color.AQUA)
                                        .withColor(Color.RED)
                                        .build()
                                );
                                fm.setPower(1);
                                f.setFireworkMeta(fm);
                        }
                } else {
                        Random rd = new Random();
                        int generateNbr = rd.nextInt(101 - ConfigManager.MONSTER_SPAWN_LUCK.getInt());
                        if (generateNbr == 1) {
                                Entity ent = Bukkit.getServer().getWorld(e.getEntity().getWorld().getName())
                                        .spawnEntity(e.getEntity().getLocation(), EntityType.RABBIT);
                                ent.setCustomName("§r§r" + Msg.EASTER_BUNNY_NAME);
                                ent.setCustomNameVisible(true);
                                Firework f = Bukkit.getWorld(e.getEntity().getWorld().getName())
                                        .spawn(e.getEntity().getLocation(), Firework.class);
                                FireworkMeta fm = f.getFireworkMeta();

                                fm.addEffects(FireworkEffect.builder()
                                        .flicker(true)
                                        .trail(true)
                                        .with(FireworkEffect.Type.BALL_LARGE)
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
}

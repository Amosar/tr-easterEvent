package com.trafalcraft.easter.config;

import com.trafalcraft.easter.Main;

import java.util.Arrays;

public enum ConfigManager {

        RABBIT_SPAWN_IN_CHUNK_LUCK(40),
        RABBIT_SPAWN_LUCK(70),
        MONSTER_SPAWN_LUCK(1);

        private int value;

        ConfigManager(int intValue) {
                this.value = intValue;
        }

        public static void load() {
                RABBIT_SPAWN_IN_CHUNK_LUCK.replaceBy(
                        Main.getInstance().getConfig().getInt("Settings.RabbitSpawnInChunkLuck"));
                RABBIT_SPAWN_LUCK.replaceBy(
                        Main.getInstance().getConfig().getInt("Settings.RabbitSpawnLuck"));
                MONSTER_SPAWN_LUCK.replaceBy(
                        Main.getInstance().getConfig().getInt("Settings.MonsterSpawnLuck"));
                String sIgnoreWorld = Main.getInstance().getConfig().getString("Settings.IgnoreWorlds");
                Main.getIgnoredWorlds().addAll(Arrays.asList(sIgnoreWorld.split("/")));
                String sIgnoreRegion = Main.getInstance().getConfig().getString("Settings.IgnoreRegions");
                Main.getIgnoredRegions().addAll(Arrays.asList(sIgnoreRegion.split("/")));
        }

        public int getInt() {
                return value;
        }

        private void replaceBy(int value) {
                this.value = value;
        }

}

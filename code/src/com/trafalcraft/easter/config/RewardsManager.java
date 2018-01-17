package com.trafalcraft.easter.config;

import com.trafalcraft.easter.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Random;
import java.util.Set;

public class RewardsManager {

        private static String[] rewards;

        public static boolean load(boolean debug) {
                rewards = new String[100];
                FileConfiguration config = Main.getInstance().getConfig();
                Set<String> items = config.getConfigurationSection("items").getKeys(false);
                int index = 0;
                for (String item : items) {
                        int percent = config.getInt("items." + item + ".percent");
                        if (debug) {
                                Main.getInstance().getLogger().info("item = " + item);
                                Main.getInstance().getLogger().info("percent = " + percent);
                                Main.getInstance().getLogger().info("index = " + index);
                        }
                        for (int i = 0; i < percent; i++) {
                                if (index == 100) {
                                        return false;
                                }
                                rewards[index] = item;
                                index++;
                        }
                }
                if (debug) {
                        Main.getInstance().getLogger().info("totalIndex = " + index);
                }
                return index == 100;
        }

        public static String getRandomReward() {
                Random rd = new Random();
                int value = rd.nextInt(100);
                return rewards[value];
        }
}

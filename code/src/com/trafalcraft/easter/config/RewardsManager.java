package com.trafalcraft.easter.config;

import com.trafalcraft.easter.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Random;
import java.util.Set;

public class RewardsManager {

        private static String[] rewards;

        public static boolean load(boolean debug) {
                rewards = new String[10000];
                FileConfiguration config = Main.getInstance().getConfig();
                Set<String> items = config.getConfigurationSection("items").getKeys(false);
                int index = 0;
                for (String item : items) {
                        double tempPercent = config.getDouble("items." + item + ".percent");
                        int percent = (int) (tempPercent * 100);
                        if (debug) {
                                Main.getInstance().getLogger().info("item = " + item);
                                Main.getInstance().getLogger().info("percent = " + tempPercent);
                                Main.getInstance().getLogger().info("index = " + ((double) index) / 100);
                        }
                        for (int i = 0; i < percent; i++) {
                                if (index == 10000) {
                                        return false;
                                }
                                rewards[index] = item;
                                index++;
                        }
                }
                if (debug) {
                        Main.getInstance().getLogger().info("totalPercent = " + ((double) index) / 100);
                }
                return index == 10000;
        }

        public static String getRandomReward() {
                Random rd = new Random();
                int value = rd.nextInt(10000);
                return rewards[value];
        }
}

package com.trafalcraft.easter;

import com.trafalcraft.easter.config.Msg;
import com.trafalcraft.easter.config.RewardsManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

        @EventHandler
        public void onPlayerClickOnEasterRabbit(PlayerInteractEntityEvent e) {
                if (e.getRightClicked().getCustomName() != null) {
                        if (e.getRightClicked().getCustomName().startsWith("§r§r" + Msg.EASTER_BUNNY_NAME)) {
                                Player player = e.getPlayer();
                                e.getRightClicked().setCustomName(null);
                                e.getRightClicked().setCustomNameVisible(false);
                                FileConfiguration yc = Main.getInstance().getConfig();
                                String reward = RewardsManager.getRandomReward();

                                Object itemTest = yc.get("items." + reward + ".item");
                                ItemStack item;
                                if (itemTest != null && itemTest instanceof ItemStack) {
                                        item = (ItemStack) itemTest;
                                        player.getInventory().addItem(item);

                                }

                                String broadcastMsg = yc.getString("items." + reward + ".broadcastMsg");
                                if (broadcastMsg != null) {
                                        Bukkit.getServer().broadcastMessage(Msg.PREFIX + broadcastMsg);
                                }

                                int levels = yc.getInt("items." + reward + ".levels");
                                if (levels != 0) {
                                        player.giveExpLevels(levels);
                                }

                                String playerMsg = yc.getString("items." + reward + ".playerMsg");
                                if (playerMsg != null) {
                                        player.sendMessage(playerMsg);
                                }

                                int money = yc.getInt("items." + reward + ".money");
                                if (money != 0) {
                                        Main.getEcon()
                                                .depositPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()), money);
                                }

                        }
                }
        }
}

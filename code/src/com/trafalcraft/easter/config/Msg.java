package com.trafalcraft.easter.config;

import com.trafalcraft.easter.Main;
import org.bukkit.entity.Player;

public enum Msg {

        EASTER_BUNNY_NAME("§9Easter Bunny"),
        PREFIX("§f[§5Easter§f]>§5 "),
        NO_PERMISSIONS("§4ERROR §9§l> §r§bYou don't have permission to do that!"),
        COMMAND_USE("§9[§4tr-Easter§9]> §r§cCommand use: §6$command");

        private String value;

        Msg(String stringValue) {
                this.value = stringValue;
        }

        public static void sendHelp(Player p) {
                p.sendMessage("");
                p.sendMessage("§3§l-------------------Easter-------------------");
                p.sendMessage("§3/easter spawn §b- Spawn a new Easter rabbit at your position.");
                p.sendMessage("§3/easter addItem <itemName> - Add the item in your hand to the config file.");
                p.sendMessage("§3/seller reload - to reload the config file");
                p.sendMessage("§3------------------------------------------------");
                p.sendMessage("");
        }

        public static void load() {
                EASTER_BUNNY_NAME.replaceBy(
                        Main.getInstance().getConfig().getString("Msg.bunnyName")
                                .replace("&", "§"));
                PREFIX.replaceBy(
                        Main.getInstance().getConfig().getString("Msg.prefix")
                                .replace("&", "§"));
                NO_PERMISSIONS.replaceBy(
                        Main.getInstance().getConfig().getString("Msg.no_permission")
                                .replace("&", "§"));
                COMMAND_USE.replaceBy(
                        Main.getInstance().getConfig().getString("Msg.command_use")
                                .replace("&", "§"));
        }

        public String toString() {
                return value;
        }

        private void replaceBy(String value) {
                this.value = value;
        }
}

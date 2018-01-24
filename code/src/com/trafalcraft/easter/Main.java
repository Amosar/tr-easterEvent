package com.trafalcraft.easter;

import com.trafalcraft.easter.config.ConfigManager;
import com.trafalcraft.easter.config.Msg;
import com.trafalcraft.easter.config.RewardsManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.error.YAMLException;

import java.util.ArrayList;
import java.util.Collection;

public class Main extends JavaPlugin {
        private static final ArrayList<String> ignoredWorlds = new ArrayList<>();
        private static final ArrayList<String> ignoredRegions = new ArrayList<>();
        private static Main instance;
        private static Economy econ;
        private static boolean economy = true;

        public static Main getInstance() {
                return instance;
        }

        public static Collection<String> getIgnoredWorlds() {
                return ignoredWorlds;
        }

        public static Collection<String> getIgnoredRegions() {
                return ignoredRegions;
        }

        public static Economy getEcon() {
                return econ;
        }

        public void onEnable() {
                instance = this;
                Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
                Bukkit.getPluginManager().registerEvents(new EntityListener(), this);

                instance.saveDefaultConfig();
                instance.reloadConfig();

                try {
                        Msg.load();
                        ConfigManager.load();
                        if (!RewardsManager.load(false)) {
                                this.getLogger().severe("The percent of your item need to be equals to 100!");
                                this.getLogger().info("Debug: on");
                                RewardsManager.load(true);
                                Bukkit.getPluginManager().disablePlugin(this);
                                return;
                        }
                } catch (YAMLException e) {
                        this.getLogger().severe("An error as occurred in the config.yml please fix it!");
                        e.printStackTrace();
                }

                if (economy) {
                        if (!setupEconomy()) {
                                Bukkit.getLogger().warning(String
                                        .format("[%s] - No Economy (Vault) dependency found! Disabling Economy."
                                                , getDescription().getName()));
                                economy = false;
                        }
                }

        }

        public void onDisable() {

        }

        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
                if (sender instanceof Player) {
                        Player p = (Player) sender;
                        if (cmd.getName().equalsIgnoreCase("easter")) {
                                if (args.length > 0) {
                                        if (p.hasPermission("easter.admin") || p.isOp()) {
                                                if (args[0].equalsIgnoreCase("reload")) {
                                                        instance.reloadConfig();
                                                        Msg.load();
                                                        ConfigManager.load();
                                                }
                                                if (args[0].equalsIgnoreCase("spawn")) {
                                                        Entity entity = p.getWorld()
                                                                .spawnEntity(p.getLocation(), EntityType.RABBIT);
                                                        entity.setCustomName("§r§r" + Msg.EASTER_BUNNY_NAME);
                                                        entity.setCustomNameVisible(true);
                                                }
                                                if (args[0].equalsIgnoreCase("addItem")) {
                                                        if (args.length > 1) {
                                                                try {
                                                                        p.getInventory().getClass()
                                                                                .getMethod("getItemInMainHand");
                                                                        getInstance().getConfig()
                                                                                .set("items." + args[1] + ".item",
                                                                                        p.getInventory()
                                                                                                .getItemInMainHand());
                                                                } catch (NoSuchMethodException e) {
                                                                        //noinspection deprecation
                                                                        getInstance().getConfig()
                                                                                .set("items." + args[1] + ".item",
                                                                                        p.getInventory()
                                                                                                .getItemInHand());
                                                                }
                                                                if (getInstance().getConfig()
                                                                        .getInt("items." + args[1] + ".percent") == 0) {
                                                                        getInstance().getConfig()
                                                                                .set("items." + args[1] + ".percent",
                                                                                        0);
                                                                }
                                                                p.sendMessage(Msg.PREFIX + "Item added to "
                                                                        + args[1] + " reward");
                                                        } else {
                                                                p.sendMessage(Msg.COMMAND_USE.toString()
                                                                        .replace("$command",
                                                                                "/easter addItem <itemName>"));
                                                        }
                                                        getInstance().saveConfig();
                                                }
                                        } else {
                                                p.sendMessage(Msg.PREFIX.toString() + Msg.NO_PERMISSIONS);
                                        }
                                } else {
                                        Msg.sendHelp(p);
                                }
                        }
                } else {
                        sender.sendMessage(Msg.PREFIX + "The console can\"t send command");
                }
                return false;
        }

        private boolean setupEconomy() {
                //charge l'économie.
                if (getServer().getPluginManager().getPlugin("Vault") == null) {
                        return false;
                }
                RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager()
                        .getRegistration(Economy.class);
                if (rsp == null) {
                        return false;
                }
                econ = rsp.getProvider();
                return econ != null;
        }
}

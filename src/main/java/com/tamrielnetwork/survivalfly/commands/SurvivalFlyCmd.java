/*
 SurvivalFly is a Spigot Plugin that gives players the ability to fly.
 Copyright (C) 2022  Leopold Meinel

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see https://github.com/TamrielNetwork/RandomSpawnTp/blob/main/LICENSE.
 */
package com.tamrielnetwork.survivalfly.commands;

import com.google.common.collect.ImmutableMap;
import com.tamrielnetwork.survivalfly.SurvivalFly;
import com.tamrielnetwork.survivalfly.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SurvivalFlyCmd implements CommandExecutor {

    private final SurvivalFly main = JavaPlugin.getPlugin(SurvivalFly.class);


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check args length
        if (args.length == 0) {
            Utils.sendMessage(sender, "no-args");
            return true;
        }
        // Check arg 0
        switch (args[0]) {
            case "fly" -> toggleFly(sender, args);
            case "flyspeed" -> toggleFlySpeed(sender, args);
            default -> Utils.sendMessage(sender, "invalid-option");
        }

        return true;
    }

    private void toggleFly(CommandSender sender, String[] args) {
        // Check if command sender is a player
        if (!(sender instanceof Player)) {
            Utils.sendMessage(sender, "player-only");
            return;
        }
        // Check args length
        if (args.length > 2) {
            Utils.sendMessage(sender, "invalid-option");
            return;
        }
        //Check args length
        if (args.length == 1) {
            if (!sender.hasPermission("survivalfly.fly")) {
                Utils.sendMessage(sender, "no-perms");
                return;
            }
            if (((Player) sender).isFlying()) {
                ((Player) sender).setAllowFlight(false);
                ((Player) sender).setFlying(false);
                Utils.sendMessage(sender,"now-flying-disabled");
                return;
            }
            ((Player) sender).setAllowFlight(true);
            ((Player) sender).setFlying(true);
            Utils.sendMessage(sender,"now-flying");
            return;
        }
        // Check args length
        if (args.length == 2) {
            if (!sender.hasPermission("survivalfly.fly.others")) {
                Utils.sendMessage(sender, "no-perms");
                return;
            }
            Player player = Bukkit.getPlayer(args[1]);
            boolean isOnline = Objects.requireNonNull(player).isOnline();
            if(!isOnline) {
                Utils.sendMessage(sender, "not-online");
                return;
            }
            if (player.isFlying()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                Utils.sendMessage(sender, ImmutableMap.of("%player%", player.getName()),"player-now-flying-disabled");
                Utils.sendMessage(player, "now-flying-disabled");
                return;
            }
            player.setAllowFlight(true);
            player.setFlying(true);
            Utils.sendMessage(sender, ImmutableMap.of("%player%", player.getName()),"player-now-flying");
            Utils.sendMessage(player, "now-flying");
        }
    }

    private void toggleFlySpeed(CommandSender sender, String[] args) {
        // Check if command sender is a player
        if (!(sender instanceof Player)) {
            Utils.sendMessage(sender, "player-only");
            return;
        }
        // Check args length
        if (args.length > 3) {
            Utils.sendMessage(sender, "invalid-option");
            return;
        }
        //Check args length
        if (args.length == 2) {
            if (!sender.hasPermission("survivalfly.flyspeed")) {
                Utils.sendMessage(sender, "no-perms");
                return;
            }
            try {
                if (!(Float.parseFloat(args[1]) <= (float) main.getConfig().getInt("flyspeed.limit"))) {
                    Utils.sendMessage(sender, "beyond-limit");
                    return;
                }
                ((Player) sender).setFlySpeed((Float.parseFloat(args[1]))/10);
                Utils.sendMessage(sender, ImmutableMap.of("%flyspeed%", String.valueOf(Float.parseFloat(args[1]))),"flyspeed-changed");
                return;
            } catch(NumberFormatException numberFormatException){
                Utils.sendMessage(sender, "invalid-amount");
                return;
            }
        }
        // Check args length
        if (args.length == 3) {
            if (!sender.hasPermission("survivalfly.flyspeed.others")) {
                Utils.sendMessage(sender, "no-perms");
                return;
            }
            Player player = Bukkit.getPlayer(args[1]);
            boolean isOnline = Objects.requireNonNull(player).isOnline();
            if (!isOnline) {
                Utils.sendMessage(sender, "not-online");
                return;
            }
            try {
                if (!(Float.parseFloat(args[2]) <= (float) main.getConfig().getInt("flyspeed.limit"))) {
                    Utils.sendMessage(sender, "beyond-limit");
                    return;
                }
                player.setFlySpeed((Float.parseFloat(args[2]))/10);
                Utils.sendMessage(sender, ImmutableMap.of(
                                "%player%", player.getName(),
                                "%flyspeed%", String.valueOf(Float.parseFloat(args[2]))),
                        "player-flyspeed-changed");
                Utils.sendMessage(player, ImmutableMap.of("%flyspeed%", String.valueOf(Float.parseFloat(args[2]))), "flyspeed-changed");
            } catch(NumberFormatException numberFormatException) {
                Utils.sendMessage(sender, "invalid-amount");
            }
        }

    }
}
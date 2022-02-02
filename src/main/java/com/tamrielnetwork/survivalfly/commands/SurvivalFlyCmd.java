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
            ((Player) sender).setAllowFlight(true);
            ((Player) sender).setFlying(true);
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
            player.setAllowFlight(true);
            player.setFlying(true);
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
            if (!(Integer.parseInt(args[1]) <= main.getConfig().getInt("flyspeed.limit"))) {
                Utils.sendMessage(sender, "beyond-limit");
                return;
            }
            ((Player) sender).setFlySpeed((Float.parseFloat(args[1]))/10);
            return;
        }
        // Check args length
        if (args.length == 3) {
            if (!sender.hasPermission("survivalfly.flyspeed.others")) {
                Utils.sendMessage(sender, "no-perms");
                return;
            }
            Player player = Bukkit.getPlayer(args[1]);
            boolean isOnline = Objects.requireNonNull(player).isOnline();
            if(!isOnline) {
                Utils.sendMessage(sender, "not-online");
                return;
            }
            if (!(Integer.parseInt(args[2]) <= main.getConfig().getInt("flyspeed.limit"))) {
                Utils.sendMessage(sender, "beyond-limit");
                return;
            }
            player.setFlySpeed((Float.parseFloat(args[2]))/10);
        }

    }
}
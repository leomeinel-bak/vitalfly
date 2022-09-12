/*
 * File: VitalFlyCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalfly.commands;

import dev.meinel.leo.vitalfly.utils.Chat;
import dev.meinel.leo.vitalfly.utils.commands.Cmd;
import dev.meinel.leo.vitalfly.utils.commands.CmdSpec;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class VitalFlyCmd
        implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        if (Cmd.isArgsLengthGreaterThan(sender, args, 1)) {
            return false;
        }
        doFly(sender, args);
        return true;
    }

    private void doFly(@NotNull CommandSender sender, @NotNull String[] args) {
        if (Cmd.isInvalidSender(sender)) {
            return;
        }
        Player senderPlayer = (Player) sender;
        if (args.length == 0) {
            if (Cmd.isNotPermitted(sender, "vitalfly.fly")) {
                return;
            }
            if (senderPlayer.getAllowFlight()) {
                senderPlayer.setAllowFlight(false);
                senderPlayer.setFlying(false);
                Chat.sendMessage(senderPlayer, "now-flying-disabled");
                return;
            }
            senderPlayer.setAllowFlight(true);
            Chat.sendMessage(senderPlayer, "now-flying");
            return;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (CmdSpec.isInvalidCmd(sender, player, "vitalfly.fly.others")) {
                return;
            }
            assert player != null;
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                Chat.sendMessage(senderPlayer, Map.of("%player%", player.getName()), "player-now-flying-disabled");
                Chat.sendMessage(player, "now-flying-disabled");
                return;
            }
            player.setAllowFlight(true);
            Chat.sendMessage(senderPlayer, Map.of("%player%", player.getName()), "player-now-flying");
            Chat.sendMessage(player, "now-flying");
        }
    }
}
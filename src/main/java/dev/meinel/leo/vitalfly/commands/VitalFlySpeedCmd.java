/*
 * File: VitalFlySpeedCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalfly.commands;

import dev.meinel.leo.vitalfly.utils.commands.Cmd;
import dev.meinel.leo.vitalfly.utils.commands.CmdSpec;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VitalFlySpeedCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String label, @NotNull String[] args) {
        if (Cmd.isArgsLengthEqualTo(sender, args, 0)
                || Cmd.isArgsLengthGreaterThan(sender, args, 2)) {
            return false;
        }
        doFlySpeed(sender, args);
        return true;
    }

    private void doFlySpeed(@NotNull CommandSender sender, @NotNull String[] args) {
        if (Cmd.isInvalidSender(sender)) {
            return;
        }
        Player senderPlayer = (Player) sender;
        if (args.length == 1) {
            if (!Cmd.isPermitted(sender, "vitalfly.flyspeed")) {
                return;
            }
            CmdSpec.setFlySpeed(senderPlayer, args[0]);
        }
        if (args.length == 2) {
            Player player = Bukkit.getPlayer(args[0]);
            if (Cmd.isInvalidPlayer(sender, player)
                    || CmdSpec.isInvalidCmd(sender, player, "vitalfly.flyspeed.others")) {
                return;
            }
            CmdSpec.setFlySpeed(senderPlayer, args[1], player);
        }
    }
}

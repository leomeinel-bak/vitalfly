/*
 * VitalFly is a Spigot Plugin that gives players the ability to fly.
 * Copyright © 2022 Leopold Meinel & contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/LeoMeinel/VitalFly/blob/main/LICENSE
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

public class VitalFlySpeedCmd
		implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
	                         @NotNull String[] args) {
		if (Cmd.isArgsLengthEqualTo(sender, args, 0) || Cmd.isArgsLengthGreaterThan(sender, args, 2)) {
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
			if (Cmd.isNotPermitted(sender, "vitalfly.flyspeed")) {
				return;
			}
			CmdSpec.setFlySpeed(senderPlayer, args[0]);
		}
		if (args.length == 2) {
			Player player = Bukkit.getPlayer(args[0]);
			if (Cmd.isInvalidPlayer(sender, player) || CmdSpec.isInvalidCmd(sender, player,
			                                                                "vitalfly.flyspeed.others")) {
				return;
			}
			CmdSpec.setFlySpeed(senderPlayer, args[1], player);
		}
	}
}

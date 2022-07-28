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

package com.tamrielnetwork.vitalfly.commands;

import com.tamrielnetwork.vitalfly.utils.Chat;
import com.tamrielnetwork.vitalfly.utils.commands.Cmd;
import com.tamrielnetwork.vitalfly.utils.commands.CmdSpec;
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
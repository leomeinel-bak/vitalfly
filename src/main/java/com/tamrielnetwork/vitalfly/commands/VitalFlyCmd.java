/*
 * VitalFly is a Spigot Plugin that gives players the ability to fly.
 * Copyright © 2022 Leopold Meinel
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
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalFly/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalfly.commands;

import com.tamrielnetwork.vitalfly.utils.Chat;
import com.tamrielnetwork.vitalfly.utils.commands.Cmd;
import com.tamrielnetwork.vitalfly.utils.commands.CmdSpec;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VitalFlyCmd implements TabExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

		if (Cmd.isArgsLengthEqualTo(sender, args, 0) || Cmd.isArgsLengthGreaterThan(sender, args, 3)) {
			return true;
		}

		switch (args[0].toLowerCase()) {
			case "fly" -> doFly(sender, args);
			case "flyspeed" -> doFlySpeed(sender, args);
			default -> Chat.sendMessage(sender, "invalid-option");
		}

		return true;
	}

	private void doFly(@NotNull CommandSender sender, @NotNull String[] args) {

		if (Cmd.isArgsLengthGreaterThan(sender, args, 2)) {
			return;
		}
		if (Cmd.isInvalidSender(sender)) {
			return;
		}
		Player senderPlayer = (Player) sender;

		if (args.length == 1) {
			if (Cmd.isNotPermitted(sender, "vitalfly.fly")) {
				return;
			}
			if (senderPlayer.getAllowFlight()) {
				CmdSpec.disableFlight(senderPlayer);
				return;
			}
			CmdSpec.enableFlight(senderPlayer);
			return;
		}

		if (args.length == 2) {
			Player player = Bukkit.getPlayer(args[1]);

			if (CmdSpec.isInvalidCmd(sender, player, "vitalfly.fly.others")) {
				return;
			}

			assert player != null;
			if (player.getAllowFlight()) {
				CmdSpec.disableFlight(senderPlayer, player);
				return;
			}
			CmdSpec.enableFlight(senderPlayer, player);
		}
	}

	private void doFlySpeed(@NotNull CommandSender sender, @NotNull String[] args) {
		Player player = Bukkit.getPlayer(args[1]);

		if (Cmd.isInvalidSender(sender)) {
			return;
		}
		if (Cmd.isInvalidPlayer(sender, player)) {
			return;
		}
		Player senderPlayer = (Player) sender;

		if (args.length == 2) {

			if (Cmd.isNotPermitted(sender, "vitalfly.flyspeed")) {
				return;
			}
			CmdSpec.setFlySpeed(senderPlayer, args[1]);
		}

		if (args.length == 3) {
			if (CmdSpec.isInvalidCmd(sender, player, "vitalfly.flyspeed.others")) {
				return;
			}
			CmdSpec.setFlySpeed(senderPlayer, args[2], player);
		}

	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
		@Nullable List<String> tabComplete = new ArrayList<>();
		switch (args.length) {
			case 1 -> {
				if (sender.hasPermission("vitalfly.fly")) {
					tabComplete.add("fly");
				}
				if (sender.hasPermission("vitalfly.flyspeed")) {
					tabComplete.add("flyspeed");
				}
			}
			case 3 -> {
				if (sender.hasPermission("vitalfly.flyspeed") && args[0].equals("flyspeed")) {
					tabComplete.addAll(List.of(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
				}
			}
			default -> tabComplete = null;
		}
		return tabComplete;
	}
}
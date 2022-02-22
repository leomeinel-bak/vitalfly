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

package com.tamrielnetwork.vitalfly.utils.commands;

import com.google.common.collect.ImmutableMap;
import com.tamrielnetwork.vitalfly.VitalFly;
import com.tamrielnetwork.vitalfly.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class CmdSpec {

	private static final VitalFly main = JavaPlugin.getPlugin(VitalFly.class);

	public static void disableFlight(Player senderPlayer) {
		senderPlayer.setAllowFlight(false);
		senderPlayer.setFlying(false);
		Chat.sendMessage(senderPlayer, "now-flying-disabled");
	}

	public static void disableFlight(Player senderPlayer, Player player) {
		player.setAllowFlight(false);
		player.setFlying(false);
		Chat.sendMessage(senderPlayer, ImmutableMap.of("%player%", player.getName()), "player-now-flying-disabled");
		Chat.sendMessage(player, "now-flying-disabled");
	}

	public static void enableFlight(Player senderPlayer) {
		senderPlayer.setAllowFlight(true);
		Chat.sendMessage(senderPlayer, "now-flying");
	}

	public static void enableFlight(Player senderPlayer, Player player) {
		player.setAllowFlight(true);
		Chat.sendMessage(senderPlayer, ImmutableMap.of("%player%", player.getName()), "player-now-flying");
		Chat.sendMessage(player, "now-flying");
	}

	public static void setFlySpeed(@NotNull Player senderPlayer, @NotNull String arg) {
		try {
			if (isInvalidFlySpeed(senderPlayer, arg)) {
				return;
			}
			senderPlayer.setFlySpeed((Math.abs(Float.parseFloat(arg))) / 10);
			Chat.sendMessage(senderPlayer, ImmutableMap.of("%flyspeed%", String.valueOf(Math.abs(Float.parseFloat(arg)))), "flyspeed-changed");
		} catch (NumberFormatException numberFormatException) {
			Chat.sendMessage(senderPlayer, "invalid-amount");
		}
	}

	public static void setFlySpeed(@NotNull Player senderPlayer, @NotNull String arg, Player player) {
		try {
			if (isInvalidFlySpeed(senderPlayer, arg)) {
				return;
			}
			player.setFlySpeed((Math.abs(Float.parseFloat(arg))) / 10);
			String flySpeed = String.valueOf(Math.abs(Float.parseFloat(arg)));
			Chat.sendMessage(senderPlayer, ImmutableMap.of("%player%", player.getName(), "%flyspeed%", flySpeed), "player-flyspeed-changed");
			Chat.sendMessage(player, ImmutableMap.of("%flyspeed%", flySpeed), "flyspeed-changed");
		} catch (NumberFormatException numberFormatException) {
			Chat.sendMessage(senderPlayer, "invalid-amount");
		}
	}

	public static boolean isInvalidCmd(@NotNull CommandSender sender, Player player, @NotNull String perm) {
		if (Cmd.isNotPermitted(sender, perm)) {
			return true;
		}
		return Cmd.isInvalidPlayer(sender, player);
	}

	private static boolean isInvalidFlySpeed(@NotNull CommandSender sender, @NotNull String arg) {
		if (!(Math.abs(Float.parseFloat(arg)) <= Math.abs((float) main.getConfig().getInt("flyspeed.limit")))) {
			Chat.sendMessage(sender, "beyond-limit");
			return true;
		}
		return false;
	}

}

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

package com.tamrielnetwork.vitalfly.utils.commands;

import com.tamrielnetwork.vitalfly.VitalFly;
import com.tamrielnetwork.vitalfly.utils.Chat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class CmdSpec {

	private static final String FLYSPEED = "%flyspeed%";
	private static final VitalFly main = JavaPlugin.getPlugin(VitalFly.class);

	private CmdSpec() {
		throw new IllegalStateException("Utility class");
	}

	public static void setFlySpeed(@NotNull Player senderPlayer, @NotNull String arg) {
		if (isInvalidFlySpeed(senderPlayer, arg)) {
			return;
		}
		float flySpeed = Float.parseFloat(arg);
		senderPlayer.setFlySpeed(flySpeed / 10);
		Chat.sendMessage(senderPlayer, Map.of(FLYSPEED, String.valueOf(flySpeed)), "flyspeed-changed");
	}

	public static void setFlySpeed(@NotNull Player senderPlayer, @NotNull String arg, Player player) {
		if (isInvalidFlySpeed(senderPlayer, arg)) {
			return;
		}
		float flySpeed = Float.parseFloat(arg);
		player.setFlySpeed(flySpeed / 10);
		Chat.sendMessage(senderPlayer, Map.of("%player%", player.getName(), FLYSPEED, String.valueOf(flySpeed)),
		                 "player-flyspeed-changed");
		Chat.sendMessage(player, Map.of(FLYSPEED, String.valueOf(flySpeed)), "flyspeed-changed");
	}

	public static boolean isInvalidCmd(@NotNull CommandSender sender, Player player, @NotNull String perm) {
		return Cmd.isNotPermitted(sender, perm) || Cmd.isInvalidPlayer(sender, player);
	}

	private static boolean isInvalidFlySpeed(@NotNull CommandSender sender, @NotNull String arg) {
		try {
			float flySpeed = Float.parseFloat(arg);
			if (flySpeed <= 0) {
				Chat.sendMessage(sender, "invalid-amount");
				return true;
			}
			if (flySpeed > main.getConfig()
			                   .getInt("flyspeed.limit")) {
				Chat.sendMessage(sender, "beyond-limit");
				return true;
			}
			return false;
		}
		catch (NumberFormatException numberFormatException) {
			Chat.sendMessage(sender, "invalid-amount");
			return true;
		}
	}
}

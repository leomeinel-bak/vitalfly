/*
 * File: CmdSpec.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalfly.utils.commands;

import dev.meinel.leo.vitalfly.VitalFly;
import dev.meinel.leo.vitalfly.utils.Chat;
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
		} catch (NumberFormatException numberFormatException) {
			Chat.sendMessage(sender, "invalid-amount");
			return true;
		}
	}
}

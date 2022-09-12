/*
 * File: PlayerGamemodeChange.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalfly.listeners;

import dev.meinel.leo.vitalfly.VitalFly;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.GameMode.SURVIVAL;

public class PlayerGamemodeChange
		implements Listener {

	private final VitalFly main = JavaPlugin.getPlugin(VitalFly.class);

	@EventHandler
	public void onGamemodeChange(@NotNull PlayerGameModeChangeEvent event) {
		new BukkitRunnable() {

			@Override
			public void run() {
				Player player = event.getPlayer();
				if (!player.hasPermission("vitalfly.fly") || !player.isOnline() || !player.hasPermission(
						"vitalfly.fly.gamemodechange")) {
					return;
				}
				if (event.getNewGameMode() == SURVIVAL) {
					player.setAllowFlight(true);
					player.setFlying(true);
				}
			}
		}.runTaskLater(main, 1);
	}
}

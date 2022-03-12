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
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalFly/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalfly.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Material.AIR;

public class PlayerJoin
		implements Listener {

	@EventHandler
	public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("vitalfly.fly") || !player.hasPermission("vitalfly.fly.login")) {
			return;
		}
		if (isInAir(event)) {
			player.setAllowFlight(true);
			player.setFlying(true);
		}
	}

	private boolean isInAir(@NotNull PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Location location = player.getLocation();
		location.setY(location.getY() - 2);
		return player.getWorld()
		             .getBlockAt(location)
		             .getType() == AIR;
	}
}

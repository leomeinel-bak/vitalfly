/*
 * File: PlayerJoin.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalfly.listeners;

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

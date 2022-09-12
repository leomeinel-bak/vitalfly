/*
 * File: PlayerChangedWorld.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalfly.listeners;

import dev.meinel.leo.vitalfly.utils.Chat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerChangedWorld
        implements Listener {

    @EventHandler
    public void onPlayerChangedWorld(@NotNull PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("vitalfly.fly") || !player.hasPermission("vitalfly.fly.worldchange")) {
            return;
        }
        player.setAllowFlight(true);
        Chat.sendMessage(player, "now-flying");
    }
}

package com.tamrielnetwork.survivalfly.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPermission("survivalfly.fly")) {
            return;
        }

        if (event.getPlayer().getVelocity().getY() < 1) {
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().setFlying(true);
        }

    }
}

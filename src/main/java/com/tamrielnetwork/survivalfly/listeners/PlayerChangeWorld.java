package com.tamrielnetwork.survivalfly.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorld implements Listener {
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        if (!event.getPlayer().hasPermission("survivalfly.fly")) {
            return;
        }

        event.getPlayer().setAllowFlight(true);
        event.getPlayer().setFlying(true);
    }

}

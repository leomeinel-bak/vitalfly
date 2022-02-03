package com.tamrielnetwork.survivalfly.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGamemodeChange implements Listener {
    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent event) {
        if (!event.getPlayer().hasPermission("survivalfly.fly") || !event.getPlayer().isOnline()) {
            return;
        }

        if (event.getNewGameMode() == GameMode.SURVIVAL) {
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().setFlying(true);
        }
    }

}

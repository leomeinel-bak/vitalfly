package com.tamrielnetwork.survivalfly.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGamemodeChange implements Listener {
    public void onGamemodeChange(PlayerGameModeChangeEvent event) {
        if (!event.getPlayer().hasPermission("survivalfly.fly")) {
            return;
        }

        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().setFlying(true);
        }
    }

}

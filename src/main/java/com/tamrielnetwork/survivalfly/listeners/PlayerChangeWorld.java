package com.tamrielnetwork.survivalfly.listeners;

import com.tamrielnetwork.survivalfly.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorld implements Listener {
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        if (!event.getPlayer().hasPermission("survivalfly.fly")) {
            return;
        }
        Utils.sendMessage(event.getPlayer(),"now-flying");
        event.getPlayer().setAllowFlight(true);

    }

}

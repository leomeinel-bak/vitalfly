package com.tamrielnetwork.survivalfly.listeners;

import com.tamrielnetwork.survivalfly.SurvivalFly;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.GameMode.SURVIVAL;

public class PlayerGamemodeChange implements Listener {

	private final SurvivalFly main = JavaPlugin.getPlugin(SurvivalFly.class);

	@EventHandler
	public void onGamemodeChange(PlayerGameModeChangeEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!event.getPlayer().hasPermission("survivalfly.fly") || !event.getPlayer().isOnline() || !event.getPlayer().hasPermission("survivalfly.fly.gamemodechange")) {
					return;
				}

				if (event.getNewGameMode() == SURVIVAL) {
					event.getPlayer().setAllowFlight(true);
					event.getPlayer().setFlying(true);
				}
			}
		}.runTaskLater(main, 1);
	}

}

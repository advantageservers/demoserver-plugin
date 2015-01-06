package com.advantageservers.demoserver;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DemoServer extends JavaPlugin implements Listener {
	
	@Override
	public void onDisable() {
	}
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public boolean onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GOLD + "Welcome to the Advantage Servers test Server, " + ChatColor.RESET + player.getDisplayName() + ChatColor.GOLD + "!");
		return true;
	}
	
}

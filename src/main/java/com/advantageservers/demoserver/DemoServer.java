package com.advantageservers.demoserver;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.advantageservers.demoserver.command.SetSpawnCommand;
import com.advantageservers.demoserver.command.SpawnCommand;

public class DemoServer extends JavaPlugin implements Listener {
	
	@Override
	public void onDisable(){
	}
	
	@Override
	public void onEnable(){
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
	}
	
	public Location getSpawn(){
		return new Location(
                getServer().getWorld(getConfig().getString("spawn.world")),
                getConfig().getDouble("spawn.x"),
                getConfig().getDouble("spawn.y"),
                getConfig().getDouble("spawn.z"),
                (float) getConfig().getDouble("spawn.yaw"),
                (float) getConfig().getDouble("spawn.pitch")
            );
	}
	
	@EventHandler
	public boolean onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GOLD + "Welcome to the Advantage Servers test Server, " + ChatColor.RESET + player.getDisplayName() + ChatColor.GOLD + "!");
		return true;
	}
	
}

package com.advantageservers.demoserver;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.advantageservers.demoserver.command.GamemodeCommand;
import com.advantageservers.demoserver.command.SetSpawnCommand;
import com.advantageservers.demoserver.command.SpawnCommand;
import com.advantageservers.demoserver.command.TimeCommand;
import com.advantageservers.demoserver.command.UptimeCommand;

public class DemoServer extends JavaPlugin implements Listener {
	
	private long uptime;
	
	@Override
	public void onDisable(){
	}
	
	@Override
	public void onEnable(){
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("gamemode").setExecutor(new GamemodeCommand(this));
		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("time").setExecutor(new TimeCommand(this));
		getCommand("uptime").setExecutor(new UptimeCommand(this));
		
		this.uptime = System.currentTimeMillis();
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
	public boolean onPlayerRespawn(PlayerRespawnEvent event){
		event.setRespawnLocation(getSpawn());
		return false;
	}
	
	@EventHandler
	public boolean onPlayerJoinEvent(PlayerJoinEvent event){
		// Remove the bukkit join message
		event.setJoinMessage(null);
		
		// Broadcast message to everyone on join then send welcome message to event player
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			player.sendMessage(ChatColor.YELLOW + event.getPlayer().getName() + " joined the game.");
		}
		event.getPlayer().sendMessage(ChatColor.GREEN + "Welcome to the Advantage Servers test server.");
		
		return true;
	}
	
	public int[] getCurrentServerUptime(){
		final int[] i = new int[4];
		long l = System.currentTimeMillis() - this.uptime;
		i[3] = (int)(l / 86400000L);
		l -= i[3] * 86400000L;
		i[2] = (int)(l / 3600000L);
		l -= i[2] * 3600000;
		i[1] = (int)(l / 60000L);
		l -= i[1] * 60000L;
		i[0] = (int)(l / 1000L);
		return i;
	}
}

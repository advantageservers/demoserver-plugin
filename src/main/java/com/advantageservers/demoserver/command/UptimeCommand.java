package com.advantageservers.demoserver.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.advantageservers.demoserver.DemoServer;

public class UptimeCommand implements CommandExecutor {
	private final DemoServer plugin;
	
	public UptimeCommand(DemoServer plugin) {
		this.plugin = plugin;
	}
	
	// Usage: /uptime
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		if(sender.hasPermission("demo.uptime")){
			final int[] uptime = plugin.getCurrentServerUptime();
			sender.sendMessage(ChatColor.GOLD + "Uptime: " + ChatColor.GREEN + uptime[3] + " day(s), " + uptime[2] + " hour(s), " + uptime[1] + " minute(s), and " + uptime[0] + " second(s)");
		}else{
			sender.sendMessage(ChatColor.DARK_RED + "You do not have demo.uptime");
			return true;
		}
		return true;
	}
}

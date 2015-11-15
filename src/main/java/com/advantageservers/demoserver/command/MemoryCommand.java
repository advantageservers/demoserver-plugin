package com.advantageservers.demoserver.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.advantageservers.demoserver.DemoServer;

public class MemoryCommand implements CommandExecutor {
	private final DemoServer plugin;
	
	public MemoryCommand(DemoServer plugin) {
		this.plugin = plugin;
	}
	
	// Usage: /memory
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		if(sender.hasPermission("demo.mem")){
			plugin.updateMemoryStats();
			
			sender.sendMessage(ChatColor.GOLD + "Maximum memory: " + ChatColor.GREEN + String.format("%.2f", plugin.memMax) + " MB");
			sender.sendMessage(ChatColor.GOLD + "Used memory: " + ChatColor.GREEN + String.format("%.2f", plugin.memUsed) + " MB");
			sender.sendMessage(ChatColor.GOLD + "Free memory: " + ChatColor.GREEN + String.format("%.2f", plugin.memFree) + " MB");
		}else{
			sender.sendMessage(ChatColor.DARK_RED + "You do not have demo.mem");
		}
		return true;
	}
}

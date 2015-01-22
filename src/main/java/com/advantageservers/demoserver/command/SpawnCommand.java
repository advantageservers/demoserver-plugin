package com.advantageservers.demoserver.command;

import com.advantageservers.demoserver.DemoServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor{
    private final DemoServer plugin;

    public SpawnCommand(DemoServer plugin) { // this is a constructor that takes one parameter
    	this.plugin = plugin;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Error: Not executable by console!");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0){
            player.teleport(plugin.getSpawn());
            player.sendMessage(ChatColor.GOLD + "Teleporting..");
            return true;
        }
        @SuppressWarnings("deprecation")
		Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
        if(args.length == 1){
        	if(targetPlayer == null){
				sender.sendMessage(ChatColor.RED + "Error: That player is not online!");
				return true;
			}
            if(!player.hasPermission("demo.spawn.others")){
                player.sendMessage(ChatColor.DARK_RED + "You do not have demo.spawn.others");
                return true;
            }else{
                targetPlayer.teleport(plugin.getSpawn());
                targetPlayer.sendMessage(ChatColor.GOLD + "You got teleported to spawn by " + ChatColor.RESET +  player.getDisplayName());
                return true;
            }
            
        }
        if(args.length >= 2){
            player.sendMessage(ChatColor.RED + "Error: Too many arguments!");
            return true;
        }
        return false;
    }
}

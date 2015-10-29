package com.penguinchao.chatextender;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatExtender extends JavaPlugin {
	//Objects
	public Messages messages;
	public Listeners listener;
	//Methods
	@Override
	public void onEnable(){
		saveDefaultConfig();
		messages = new Messages(this);
		listener = new Listeners(this);
	}
	@Override
	public void onDisable(){
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("celisten")){
			messages.debugTrace("/celisten issued");
			if(sender instanceof Player){
				messages.debugTrace("Sender is a player");
			}else{
				messages.debugTrace("Sender is not a player");
				sender.sendMessage(ChatColor.RED+"This command can only be used by a player");
				return false;
			}
			messages.debugTrace("Casting sender as a player");
			Player sendingPlayer = (Player) sender;
			if(sendingPlayer.hasPermission("chatextender.listen")){
				messages.debugTrace("Player has permission");
				if(listener.addListener(sendingPlayer)){
					messages.debugTrace("Attempting to add sender to listeners");
					messages.playerSuccess("now-listening", sendingPlayer);
				}else{
					messages.debugTrace("Attempting to remove sender from listeners");
					if(listener.removeListener(sendingPlayer)){
						messages.debugTrace("Removed player from listeners");
						messages.playerSuccess("not-listening", sendingPlayer);
					}else{
						messages.debugTrace("Could not remove player form listeners");
						messages.playerError("an-error-occured", sendingPlayer);
					}
				}
			}else{
				messages.debugTrace("Player does not have permission");
				messages.playerError("no-permission", sendingPlayer);
				return false;
			}
		}
		return false; 
	}
}

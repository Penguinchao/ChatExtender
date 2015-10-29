package com.penguinchao.chatextender;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Listeners implements Listener {
	private ChatExtender main;
	private List<Player> listeningPlayers = new ArrayList<Player>();
	public Listeners(ChatExtender chatExtender) {
		main = chatExtender;
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onCommandSend(PlayerCommandPreprocessEvent event){
		String[] args = event.getMessage().split(" ");
		if(isPMCommand(args[0])){
			logPM(event.getPlayer(),event.getMessage());
		}else if(isReplyCommand(args[0])){
			logReply(event.getPlayer(), event.getMessage());
		}else if(isWatchedCommand(args[0])){
			logCommand(event.getPlayer(), event.getMessage());
		}
	}
	private boolean isPMCommand(String command){
		List<String> listeningCommands = main.getConfig().getStringList("listeners.pm");
		main.messages.debugTrace("Testing command: "+command);
		for(String currentCommand : listeningCommands){
			if(currentCommand.equalsIgnoreCase(command)){
				main.messages.debugTrace("Command equals: "+currentCommand);
				return true;
			}
			main.messages.debugTrace("Command doesn't equal: "+currentCommand);
		}
		return false;
	}
	private void logPM(Player sender, String message){
		String[] messageSplit = message.split(" ");
		String sentMessage = "";
		if(messageSplit.length < 3){
			main.messages.debugTrace("No message to send");
			return;
		}
		for(int i = 2; i < messageSplit.length; i++){
			sentMessage = sentMessage+messageSplit[i]+" ";
		}
		for(Player currentListener : getListeningPlayers()){
			if(currentListener.hasPermission("chatextender.conversations")){
				currentListener.sendMessage(ChatColor.GRAY+"[Eavesdropper] "+
						ChatColor.WHITE+sender.getDisplayName()+ChatColor.GRAY+
						" to "+ChatColor.WHITE+messageSplit[1]+": "+ChatColor.GRAY+sentMessage
				);
			}
		}
	}
	private boolean isReplyCommand(String command){
		List<String> listeningCommands = main.getConfig().getStringList("listeners.reply");
		main.messages.debugTrace("Testing command: "+command);
		for(String currentCommand : listeningCommands){
			if(currentCommand.equalsIgnoreCase(command)){
				main.messages.debugTrace("Command equals: "+currentCommand);
				return true;
			}
			main.messages.debugTrace("Command doesn't equal: "+currentCommand);
		}
		return false;
	}
	private void logReply(Player sender, String message){
		String[] messageSplit = message.split(" ");
		String sentMessage = "";
		if(messageSplit.length < 2){
			main.messages.debugTrace("No message to send");
			return;
		}
		for(int i = 1; i < messageSplit.length; i++){
			sentMessage = sentMessage+messageSplit[i]+" ";
		}
		for(Player currentListener : getListeningPlayers()){
			if(currentListener.hasPermission("chatextender.conversations")){
				currentListener.sendMessage(ChatColor.GRAY+"[Eavesdropper] "+
						ChatColor.WHITE+sender.getDisplayName()+ChatColor.WHITE+
						" replied"+ChatColor.WHITE+": "+ChatColor.GRAY+sentMessage
				);
			}
		}
	}
	private boolean isWatchedCommand(String command){
		List<String> listeningCommands = main.getConfig().getStringList("listeners.watched");
		main.messages.debugTrace("Testing command: "+command);
		for(String currentCommand : listeningCommands){
			if(currentCommand.equalsIgnoreCase(command)){
				main.messages.debugTrace("Command equals: "+currentCommand);
				return true;
			}
			main.messages.debugTrace("Command doesn't equal: "+currentCommand);
		}
		return false;
	}
	private void logCommand(Player sender, String message){
		for(Player currentListener : getListeningPlayers()){
			if(currentListener.hasPermission("chatextender.commands")){
				currentListener.sendMessage(ChatColor.GRAY+"[Eavesdropper] "+
						ChatColor.WHITE+sender.getDisplayName()+ChatColor.GRAY+
						" issued"+ChatColor.WHITE+" command: "+ChatColor.GRAY+message
				);
			}
		}
	}
	public List<Player> getListeningPlayers(){
		return listeningPlayers;
	}
	public Boolean addListener(Player player){
		if(listeningPlayers.contains(player)){
			return false;
		}else{
			return listeningPlayers.add(player);
		}
	}
	public Boolean removeListener(Player player){
		if(listeningPlayers.contains(player)){
			return listeningPlayers.remove(player);
		}else{
			return false;
		}
	}
}
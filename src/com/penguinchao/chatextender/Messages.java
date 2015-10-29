package com.penguinchao.chatextender;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {
	private ChatExtender main;
	private Boolean debugEnabled;
	public Messages(ChatExtender chatExtender) {
		main = chatExtender;
		if(main.getConfig().getString("debug-enabled").equalsIgnoreCase("true")){
			debugEnabled = true;
		}else{
			debugEnabled = false;
		}
		debugTrace("Debug Enabled");
	}
	public void debugTrace(String message){ //Logs message to the console if debug is enabled
		if(debugEnabled){
			main.getLogger().info("[DEBUG] "+message);
		}
	}
	public void playerSuccess(String messageName, Player player){
		player.sendMessage(ChatColor.GREEN+main.getConfig().getString(messageName));
	}
	public void playerError(String messageName, Player player){
		player.sendMessage(ChatColor.RED+main.getConfig().getString(messageName));
	}
}

package me.josiah.probation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Release extends TimerTask {
	public final HashMap<Player, ArrayList<Block>> probation = new HashMap<Player, ArrayList<Block>>();
	Player player;
	public Release(Player player, main plugin) {
	}

	@Override
	public void run() {
		try{
		probation.remove(player);
		player.sendMessage(ChatColor.GREEN + "You have been released from your probation!");
	}catch(Exception e){
		
	}
	}

}

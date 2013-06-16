package me.josiah.probation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{
	Logger logger = Logger.getLogger("Minecraft");
	public final HashMap<Player, ArrayList<Block>> probation = new HashMap<Player, ArrayList<Block>>();
	public main plugin;
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[" +pdfFile.getName() +"]"+" "+ "Version " +pdfFile.getVersion()+" "+ "Has been Enabled!");
		getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[" + pdfFile.getName() +"]"+" "+ "Has been Disabled!");
	}
	int time2jail;
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		Player targetPlayer = player.getServer().getPlayer(args[0]);
		if(commandLabel.equalsIgnoreCase("probation"))
		if(sender.hasPermission(new permissions().probation)){
			if(args.length == 0){
				player.sendMessage(ChatColor.RED + "Invalid Args!");
			}
			if(args.length == 1){
			probation.put(targetPlayer, null);
			player.sendMessage(ChatColor.GOLD + "You put "+ChatColor.GRAY+targetPlayer.getDisplayName()+ChatColor.GOLD+" on probation");
			targetPlayer.sendMessage(ChatColor.GOLD + "You have been put on probation by: "+ChatColor.BLUE+player.getDisplayName()+
					ChatColor.GOLD+ " for " + time2jail + " Minutes!");
			}
			if(args.length == 2)
			if(args[1] != null){

		        time2jail = Integer.parseInt(args[1]) * 60000;
		        targetPlayer.sendMessage(ChatColor.GOLD + "You have been put on probation by: "+ChatColor.BLUE+player.getDisplayName()+
						ChatColor.GOLD+ " for " + time2jail + " Minutes!");

		        Timer jailTimer = new Timer();
		        jailTimer.schedule(new Release(player,plugin), time2jail);

		        }
			else{
				player.sendMessage(ChatColor.RED + "You do not have permission!");
			}
		}
			if(commandLabel.equalsIgnoreCase("release"))
			if(sender.hasPermission(new permissions().release)){
				if(args.length == 0){
					player.sendMessage(ChatColor.RED + "Invalid Args!");
				}
				if(args.length == 1){
				probation.remove(targetPlayer);
				player.sendMessage(ChatColor.GOLD + "You have released: "+ChatColor.GRAY+targetPlayer.getDisplayName());
				targetPlayer.sendMessage(ChatColor.GOLD + "You have been released by: "+ChatColor.BLUE+ player.getDisplayName());
				}else{
					player.sendMessage(ChatColor.RED + "You do not have permission!");
				}
			}
			return false;		
	
			
			
			
			
	}
}

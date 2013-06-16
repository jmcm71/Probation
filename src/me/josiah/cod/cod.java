package me.josiah.cod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public class cod extends JavaPlugin implements Listener{
	public Logger logger = Logger.getLogger("Minecraft");
	public static Inventory ondutygui;
	public final HashMap<Player, ArrayList<Block>> onduty = new HashMap<Player, ArrayList<Block>>();
	public final HashMap<Player, ArrayList<Block>> copitems = new HashMap<Player, ArrayList<Block>>();
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[" + pdfFile.getName() +"]"+" "+ "Has been Disabled!");
	}
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		ondutygui = Bukkit.createInventory(null, 9, ChatColor.DARK_BLUE + "OnDuty or OffDuty?");
		this.logger.info("[" +pdfFile.getName() +"]"+" "+ "Version " +pdfFile.getVersion()+" "+ "Has been Enabled!");
		getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	@EventHandler
	public void onPlayerClick(InventoryClickEvent e){
		Player player = (Player) e.getWhoClicked();
		if(player.hasPermission(new permissions().canusegui)){
		if(e.getInventory() == ondutygui){
		}if(e.getCurrentItem().getType() == Material.BEDROCK){
			e.setCancelled(true);
			player.closeInventory();
		}
		if(e.getCurrentItem().getType() == Material.REDSTONE_BLOCK){
			e.setCancelled(true);
			if(onduty.containsKey(player)){
			PlayerInventory pi = player.getInventory();
			ItemStack boot = new ItemStack(Material.AIR);
			ItemStack leg = new ItemStack(Material.AIR);
			ItemStack chest = new ItemStack(Material.AIR);
			ItemStack helmet = new ItemStack(Material.AIR);
			int checkerID = this.getConfig().getInt("checker");
			@SuppressWarnings("unused")
			ItemStack checker = new ItemStack(checkerID);
			pi.setHelmet(helmet);
			pi.setChestplate(chest);
			pi.setLeggings(leg);
			pi.setBoots(boot);
			pi.remove(checkerID);
			int batonID = this.getConfig().getInt("baton");
			@SuppressWarnings("unused")
			ItemStack baton = new ItemStack(batonID);
			pi.remove(batonID);
			onduty.remove(player);
			player.sendMessage(getConfig().getString("offduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
					("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
					("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			}else{
				player.sendMessage(getConfig().getString("notonduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
						("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
						("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			}
		}if(e.getCurrentItem().getType() == Material.EMERALD_BLOCK){
			e.setCancelled(true);
			if(!onduty.containsKey(player)){
			PlayerInventory pi = player.getInventory();
			int batonID = this.getConfig().getInt("baton");
			ItemStack baton = new ItemStack(batonID);
			ItemMeta batonMeta = baton.getItemMeta(); 
			batonMeta.setDisplayName(getConfig().getString("batonname").replaceAll("egr", ChatColor.GREEN + "").replaceAll
					("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
					("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			baton.setItemMeta(batonMeta);
			int helmetID = this.getConfig().getInt("helmet");
			ItemStack helmet = new ItemStack(helmetID);
			int chestID = this.getConfig().getInt("chest");
			ItemStack chest = new ItemStack(chestID);
			int legID = this.getConfig().getInt("leg");
			ItemStack leg = new ItemStack(legID);
			int bootID = this.getConfig().getInt("boot");
			ItemStack boot = new ItemStack(bootID);
			int checkerID = this.getConfig().getInt("checker");
			ItemStack checker = new ItemStack(checkerID);
			ItemMeta checkerMeta = checker.getItemMeta(); 
			checkerMeta.setDisplayName(getConfig().getString("checkername").replaceAll("egr", ChatColor.GREEN + "").replaceAll
					("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
					("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			checker.setItemMeta(checkerMeta);
			boot.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
			leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
			chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
			helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
			baton.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
			baton.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
			pi.setHelmet(helmet);
			pi.setChestplate(chest);
			pi.setLeggings(leg);
			pi.setBoots(boot);
			pi.addItem(baton);
			pi.addItem(checker);
			onduty.put(player, null);
			player.sendMessage(getConfig().getString("onduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
					("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
					("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
		}else{
			player.sendMessage(getConfig().getString("alreadyonduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
					("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
					("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
		}
		}
	}
	}
	@EventHandler
	public void playerLeaveEvent(PlayerQuitEvent e){
	Player player = e.getPlayer();
		if(onduty.containsKey(player)){
			PlayerInventory pi = player.getInventory();
			ItemStack boot = new ItemStack(Material.AIR);
			ItemStack leg = new ItemStack(Material.AIR);
			ItemStack chest = new ItemStack(Material.AIR);
			ItemStack helmet = new ItemStack(Material.AIR);
			int checkerID = this.getConfig().getInt("checker");
			@SuppressWarnings("unused")
			ItemStack checker = new ItemStack(checkerID);
			pi.setHelmet(helmet);
			pi.setChestplate(chest);
			pi.setLeggings(leg);
			pi.setBoots(boot);
			pi.remove(checkerID);
			int batonID = this.getConfig().getInt("baton");
			@SuppressWarnings("unused")
			ItemStack baton = new ItemStack(batonID);
			pi.remove(batonID);
			onduty.remove(player);
		}
	}
	@EventHandler
		public void onDrop(PlayerDropItemEvent event){
		Player player = event.getPlayer();
			if(onduty.containsKey(player)){
				event.setCancelled(true);
				player.sendMessage(getConfig().getString("dropitemonduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
						("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
						("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			}
	}
	public boolean onCommand(CommandSender sender,Command cmd,String commandLabel,String[] args){
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("onduty"))
			if(!onduty.containsKey(player))
				if(sender.hasPermission(new permissions().canuse)){
					PlayerInventory pi = player.getInventory();
					int batonID = this.getConfig().getInt("baton");
					ItemStack baton = new ItemStack(batonID);
					ItemMeta batonMeta = baton.getItemMeta(); 
					batonMeta.setDisplayName(getConfig().getString("batonname").replaceAll("egr", ChatColor.GREEN + "").replaceAll
							("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
							("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
					baton.setItemMeta(batonMeta);
					int helmetID = this.getConfig().getInt("helmet");
					ItemStack helmet = new ItemStack(helmetID);
					int chestID = this.getConfig().getInt("chest");
					ItemStack chest = new ItemStack(chestID);
					int legID = this.getConfig().getInt("leg");
					ItemStack leg = new ItemStack(legID);
					int bootID = this.getConfig().getInt("boot");
					ItemStack boot = new ItemStack(bootID);
					int checkerID = this.getConfig().getInt("checker");
					ItemStack checker = new ItemStack(checkerID);
					ItemMeta checkerMeta = checker.getItemMeta(); 
					checkerMeta.setDisplayName(getConfig().getString("checkername").replaceAll("egr", ChatColor.GREEN + "").replaceAll
							("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
							("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
					checker.setItemMeta(checkerMeta);
					boot.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
					leg.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
					chest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
					helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
					baton.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
					baton.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
					pi.setHelmet(helmet);
					pi.setChestplate(chest);
					pi.setLeggings(leg);
					pi.setBoots(boot);
					pi.addItem(baton);
					pi.addItem(checker);
					onduty.put(player, null);
					player.sendMessage(getConfig().getString("onduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
							("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
							("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			}else{
				player.sendMessage(getConfig().getString("no permissions").replaceAll("egr", ChatColor.GREEN + "").replaceAll
						("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
						("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));}
			else{
				player.sendMessage(getConfig().getString("alreadyonduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
						("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
						("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
		}if(commandLabel.equalsIgnoreCase("offduty"))
			if(onduty.containsKey(player))
				if(sender.hasPermission(new permissions().canuse))  {
					PlayerInventory pi = player.getInventory();
					ItemStack boot = new ItemStack(Material.AIR);
					ItemStack leg = new ItemStack(Material.AIR);
					ItemStack chest = new ItemStack(Material.AIR);
					ItemStack helmet = new ItemStack(Material.AIR);
					int checkerID = this.getConfig().getInt("checker");
					@SuppressWarnings("unused")
					ItemStack checker = new ItemStack(checkerID);
					pi.setHelmet(helmet);
					pi.setChestplate(chest);
					pi.setLeggings(leg);
					pi.setBoots(boot);
					pi.remove(checkerID);
					int batonID = this.getConfig().getInt("baton");
					@SuppressWarnings("unused")
					ItemStack baton = new ItemStack(batonID);
					pi.remove(batonID);
					onduty.remove(player);
					player.sendMessage(getConfig().getString("offduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
							("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
							("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			
			}else{	
				player.sendMessage(getConfig().getString("no permissions").replaceAll("egr", ChatColor.GREEN + "").replaceAll
						("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
						("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			}else{
				player.sendMessage(getConfig().getString("notonduty").replaceAll("egr", ChatColor.GREEN + "").replaceAll
						("oyelo", ChatColor.YELLOW + "").replaceAll("ubl", ChatColor.BLUE + "").replaceAll("ogl", ChatColor.GOLD + "").replaceAll
						("dre", ChatColor.RED + "").replaceAll("ldo", ChatColor.BOLD + ""));
			}else if(commandLabel.equalsIgnoreCase("cod"))
			 	if(sender.hasPermission(new permissions().canusegui)) {
				ItemStack odg = new ItemStack(Material.EMERALD_BLOCK, 1);
				ItemMeta odgMeta = odg.getItemMeta(); 
				odgMeta.setDisplayName(ChatColor.GREEN + "On");
				odg.setItemMeta(odgMeta);
				ItemStack cancel = new ItemStack(Material.BEDROCK, 1);
				ItemMeta cancelMeta = cancel.getItemMeta();
				cancelMeta.setDisplayName(ChatColor.GRAY + "Cancel");
				cancel.setItemMeta(cancelMeta);
				ItemStack odr = new ItemStack(Material.REDSTONE_BLOCK, 1);
				ItemMeta odrMeta = odr.getItemMeta(); 
				odrMeta.setDisplayName(ChatColor.RED + "Off");
				odr.setItemMeta(odrMeta);
				ondutygui.setItem(5, odr);
				ondutygui.setItem(6, odr);
				ondutygui.setItem(7, odr);
				ondutygui.setItem(8, odr);
				ondutygui.setItem(4, cancel);
				ondutygui.setItem(0, odg);
				ondutygui.setItem(1, odg);
				ondutygui.setItem(2, odg);
				ondutygui.setItem(3, odg);
				player.openInventory(ondutygui);
			}else{
				player.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
			}
		return false;
	}
}
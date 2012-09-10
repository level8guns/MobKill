package me.JayzaSapphire;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MobKill extends JavaPlugin implements CommandExecutor {
	
	public void onEnable() {
		getCommand("mobkill").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] a) {
		if (sender instanceof Player) {
			if (sender.hasPermission("mobkill.kill")) {
				if (a.length == 0) {
					sender.sendMessage(ChatColor.RED + "Specify a mob.");
				}
				
				if (a.length == 1) {
					if (EntityType.fromName(a[0].toUpperCase()) != null) {
						if (EntityType.fromName(a[0].toUpperCase()).isAlive()) {
							sender.sendMessage(ChatColor.DARK_AQUA + "Killed all " + a[0].toLowerCase() + "'s in a large radius.");
							Player player = (Player)sender;
							
							List<Entity> t = player.getNearbyEntities(200.0, 200.0, 200.0);
						
							for (int i = 0; i < t.size(); i++) {
								Entity ent = t.get(i);
								
								if (a[0].equalsIgnoreCase(ent.getType().getName())) {
									if (ent instanceof LivingEntity) {
										//((LivingEntity) ent).damage(100000000);
										ent.remove();
									}
								}
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Can't kill that entity.");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Can't kill that entity.");
					}
				}
				
				if (a.length > 1) {
					sender.sendMessage(ChatColor.RED + "Too many arguements.");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You do not have permission.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You must be a player.");
		}
		return false;
	}
}
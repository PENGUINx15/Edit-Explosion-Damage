package me.penguin.eed;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class eed extends JavaPlugin implements Listener {
	
	private int cristalDamage;
	private int witherSkullDamage;
	private int tntDamage;
	private int minecraftTntDamage;	
	private int creeperDamage;	
	private boolean cristalBlockExplosion;
	private boolean witherSkullBlockExplosion;
	private boolean tntBlockExplosion;
	private boolean minecraftTntBlockExplosion;
	private boolean creeperBlockExplosion;
	private String configReload;
    private String notPerm;


    @Override
    public void onEnable() {
    	saveDefaultConfig();
    	loadConfigValues();
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    private void loadConfigValues() {
    	cristalDamage = getConfig().getInt("cristal.damage", 10 );
    	cristalBlockExplosion = getConfig().getBoolean("cristal.explosion", false);
    	witherSkullDamage = getConfig().getInt("wither-skull.damage", 10 );
    	witherSkullBlockExplosion = getConfig().getBoolean("wither-skull.explosion", false);
    	tntDamage = getConfig().getInt("tnt.damage", 10 );
    	tntBlockExplosion = getConfig().getBoolean("tnt.explosion", false);
    	minecraftTntDamage = getConfig().getInt("minecraft-tnt.damage", 10 );
    	minecraftTntBlockExplosion = getConfig().getBoolean("minecraft-tnt.explosion", false);
    	creeperDamage = getConfig().getInt("creeper.damage", 10 );
    	creeperBlockExplosion = getConfig().getBoolean("creeper.explosion", false);
    	configReload = getConfig().getString("config-reload", "§aConfig reloaded!");
    	notPerm = getConfig().getString("not-permision", "§cYou do not have permission.");
    }

    @EventHandler
    public void onCristalDamage(EntityDamageByEntityEvent event) { 
        Entity damager = event.getDamager();
        if(damager.getType() == EntityType.ENDER_CRYSTAL) {
                event.setDamage(cristalDamage);

        }
    }
    
    @EventHandler
    public void onCristalBlockExplosion(EntityExplodeEvent event) {
    	if (event.getEntityType() == EntityType.ENDER_CRYSTAL) {
            if (cristalBlockExplosion) {
                event.blockList().clear(); // Видаляємо всі блоки зі списку блоків вибуху
            }
    	}
    }    
    

    @EventHandler
    public void onWitherSkullDamage(EntityDamageByEntityEvent event) { 
        Entity damager = event.getDamager();
        if(damager.getType() == EntityType.WITHER_SKULL) {
                event.setDamage(witherSkullDamage);

        }
    } 
    
    @EventHandler
    public void onWitherSkullBlockExplosion(EntityExplodeEvent event) {
    	if (event.getEntityType() == EntityType.WITHER_SKULL) {
            if (witherSkullBlockExplosion) {
                event.blockList().clear(); // Видаляємо всі блоки зі списку блоків вибуху
            }
    	}
    } 
    
    @EventHandler
    public void onTntDamage(EntityDamageByEntityEvent event) { 
        Entity damager = event.getDamager();
        if(damager.getType() == EntityType.PRIMED_TNT) {
                event.setDamage(tntDamage);

        }
    }
    
    @EventHandler
    public void onTntBlockExplosion(EntityExplodeEvent event) {
    	if (event.getEntityType() == EntityType.PRIMED_TNT) {
            if (tntBlockExplosion) {
                event.blockList().clear(); // Видаляємо всі блоки зі списку блоків вибуху
            }
    	}
    } 
    
    @EventHandler
    public void onMinecraftTntDamage(EntityDamageByEntityEvent event) { 
        Entity damager = event.getDamager();
        if(damager.getType() == EntityType.MINECART_TNT) {
                event.setDamage(minecraftTntDamage);

        }
    }  
    
    @EventHandler
    public void onMinecraftTntBlockExplosion(EntityExplodeEvent event) {
    	if (event.getEntityType() == EntityType.MINECART_TNT) {
            if (minecraftTntBlockExplosion) {
                event.blockList().clear(); // Видаляємо всі блоки зі списку блоків вибуху
            }
    	}
    } 
    
    @EventHandler
    public void onCreeperDamage(EntityDamageByEntityEvent event) { 
        Entity damager = event.getDamager();
        if(damager.getType() == EntityType.CREEPER) {
                event.setDamage(creeperDamage);

        }
    }  
    
    @EventHandler
    public void onCreeperBlockExplosion(EntityExplodeEvent event) {
    	if (event.getEntityType() == EntityType.CREEPER) {
            if (creeperBlockExplosion) {
                event.blockList().clear(); // Видаляємо всі блоки зі списку блоків вибуху
            }
    	}
    } 
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("eed")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("eed.reload")) {
                    reloadConfig();
                    loadConfigValues();
                    sender.sendMessage(configReload.replace("&", "§"));
                    return true;
                } else {
                    sender.sendMessage(notPerm.replace("&", "§"));
                    return true;
                }
            }
        }
        return false;
    }

}


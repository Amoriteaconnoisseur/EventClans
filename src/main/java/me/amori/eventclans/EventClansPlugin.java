package me.amori.eventclans;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class EventClansPlugin extends JavaPlugin {

    public static EventClansPlugin INSTANCE;

    public static NamespacedKey CLAN_KEY;

    public static void setClan(Player plr, ClanType clan) {
        PersistentDataContainer dataContainer = plr.getPersistentDataContainer();
        dataContainer.set(CLAN_KEY, PersistentDataType.STRING, clan.name());
    }

    // Returns the player's clan if set, otherwise null
    public static ClanType getClan(Player plr) {
        PersistentDataContainer dataContainer = plr.getPersistentDataContainer();
        String rawClan = dataContainer.get(CLAN_KEY, PersistentDataType.STRING);
        return rawClan != null ? ClanType.fromString(rawClan) : null;
    }

    public static boolean isTrimmableArmorPiece(Material material) {
        String matName = material.name().toLowerCase();
        return matName.contains("helmet") || matName.contains("chestplate");
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        CLAN_KEY = new NamespacedKey(this, "clan");

        // Register armor equip event for plugin to use
        ArmorEquipEvent.registerListener(this);

        // Register commands
        //getCommand("setclan").setExecutor(new ClanSetCommand());
        //getCommand("clan").setExecutor(new ClanPanelCommand());

        // Register listeners
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ArmorEquipListener(), this);
        //Bukkit.getPluginManager().registerEvents(new PotionEffectListener(), this);
        //Bukkit.getPluginManager().registerEvents(new ItemPickupListener(), this);

        this.getLogger().log(Level.INFO, "EventClans plugin enabled!");
    }

    @Override
    public void onDisable() {


        // Set null after plugin is disabled completely
        INSTANCE = null;
    }
}
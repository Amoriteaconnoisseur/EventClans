package me.amori.eventclans;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EnumMap;
import java.util.logging.Level;

public class EventClansPlugin extends JavaPlugin {

    public static final int CLAN_PLAYER_LIMIT = 33;

    public static EnumMap<ClanType, Integer> CLAN_PLAYER_COUNTS;

    public static EventClansPlugin INSTANCE;

    public static NamespacedKey CLAN_KEY;

    public static void setClan(Player plr, ClanType clan) {
        // Don't handle if old clan and new clan are same
        ClanType oldClan = getClan(plr);
        if(oldClan == clan) {
            return;
        }

        // Remove from old clan player count
        if(oldClan != null) {
            CLAN_PLAYER_COUNTS.put(oldClan, CLAN_PLAYER_COUNTS.getOrDefault(oldClan, 1) - 1);
        }

        // Save clan for player
        PersistentDataContainer dataContainer = plr.getPersistentDataContainer();
        dataContainer.set(CLAN_KEY, PersistentDataType.STRING, clan.name());

        // Add to new clan player count
        CLAN_PLAYER_COUNTS.put(clan, CLAN_PLAYER_COUNTS.getOrDefault(clan, 0) + 1);
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

    public static boolean isClanFull(ClanType clan) {
        return CLAN_PLAYER_COUNTS.getOrDefault(clan, 0) > CLAN_PLAYER_LIMIT;
    }

    public static int getClanSize(ClanType clan) {
        return CLAN_PLAYER_COUNTS.getOrDefault(clan, 0);
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        CLAN_KEY = new NamespacedKey(this, "clan");

        CLAN_PLAYER_COUNTS = new EnumMap<>(ClanType.class);

        // Register placeholders
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new Placeholders().register();
        }

        // Register armor equip event for plugin to use
        ArmorEquipEvent.registerListener(this);

        // Register commands
        getCommand("setteam").setExecutor(new TeamSetCommand());
        //getCommand("team").setExecutor(new ClanPanelCommand());

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
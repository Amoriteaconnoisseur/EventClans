package me.amori.eventclans;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Random;

import static me.amori.eventclans.EventClansPlugin.CLAN_PLAYER_COUNTS;

public class PlayerJoinLeaveListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // Defining the Steps to assist
        Player plr = e.getPlayer();

        // If the player does not have an auto assigned clan it will run the following below
        ClanType clan = EventClansPlugin.getClan(plr);
        if(clan == null) {
            ClanType[] clanTypes = ClanType.values();
            Random random = new Random();

            // Select clan with available slots
            while(clan == null || EventClansPlugin.isClanFull(clan)) {
                // Selecting the random clan type for the player
                int selected = random.nextInt(ClanType.MAIN_CLANS);
                clan = clanTypes[selected];
            }

            // Saving the Clan Type to the player
            EventClansPlugin.setClan(plr, clan);

            // Notifies the player of their randomly assigned clan type
            plr.sendMessage("You are apart of the " + clan.name() + " Clan");
        } else {
            // Player is already part of clan; add player to clan count
            CLAN_PLAYER_COUNTS.put(clan, CLAN_PLAYER_COUNTS.getOrDefault(clan, 0) + 1);
            plr.sendMessage("You are already apart of the " + clan.name() + " clan! :)");
        }
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        // Get player from event
        // If player has clan, remove count from clan
        Player plr = event.getPlayer();
        ClanType clan = EventClansPlugin.getClan(plr);
        if(clan != null) {
            CLAN_PLAYER_COUNTS.put(clan, CLAN_PLAYER_COUNTS.getOrDefault(clan, 0) - 1);
        }
    }
}

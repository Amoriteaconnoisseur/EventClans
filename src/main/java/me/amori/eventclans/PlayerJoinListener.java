package me.amori.eventclans;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        // Defining the Steps to assist
        Player plr = e.getPlayer();

        // If the player does not have an auto assigned clan it will run the following below
        ClanType clanType = EventClansPlugin.getClan(plr);
        if(clanType == null) {
            // Selecting the random clan type for the player
            ClanType[] clanTypes = ClanType.values();
            Random random = new Random();
            int selected = random.nextInt(ClanType.MAIN_CLANS);
            clanType = clanTypes[selected];

            // Saving the Clan Type to the player
            EventClansPlugin.setClan(plr, clanType);

            // Notifies the player of their randomly assigned clan type
            plr.sendMessage("You are apart of the " + clanType.name() + " Clan");
        } else {
            plr.sendMessage("You are already apart of the " + clanType.name() + " clan! :)");
        }
    }
}

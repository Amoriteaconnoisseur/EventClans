package me.amori.eventclans;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorEquipListener implements Listener {

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        event.getPlayer().sendMessage(event.getType().name());
    }

    // For when an armor piece is swapped from the hotbar
    @EventHandler
    public void onArmorHotbarSwap(PlayerInteractEvent event) {
        // Only handle for right click interactions
        if(!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        // Don't handle if empty hand
        ItemStack item = event.getItem();
        if(item == null) {
            return;
        }

        // Handle if this is swapping armor
        if(EventClansPlugin.isTrimmableArmorPiece(item.getType())) {
            // TODO: check if player is wearing chestplate or helmet based on armor piece in hand
        }
    }
}

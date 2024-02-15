package me.amori.eventclans;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;

public class ArmorEquipListener implements Listener {

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        if(event.getNewArmorPiece() == null) {
            return;
        }

        if(EventClansPlugin.isTrimmableArmorPiece(event.getNewArmorPiece().getType())) {
            ClanType clan = EventClansPlugin.getClan(event.getPlayer());

            // Don't handle if clan doesn't have a trim
            if(!clan.hasTrim()) {
                return;
            }

            ArmorMeta meta = (ArmorMeta) event.getNewArmorPiece().getItemMeta();
            meta.setTrim(clan.getTrim());
            event.getNewArmorPiece().setItemMeta(meta);
        }
    }

    // For when an armor piece is swapped from the hotbar
    @EventHandler
    public void onArmorHotbarSwap(PlayerInteractEvent event) {
        // Only handle for right click interactions
        if(!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        // Don't handle if empty hand
        Player plr = event.getPlayer();
        ItemStack item = event.getItem();
        if(item == null) {
            return;
        }

        // Handle if this is swapping armor
        if(EventClansPlugin.isTrimmableArmorPiece(item.getType())) {

            Material itemType = item.getType();
            String itemTypeName = itemType.name().toLowerCase();
            if(itemTypeName.contains("chestplate")) {
                // Don't handle if not armor swap
                if(plr.getInventory().getChestplate() == null) {
                    return;
                }

            } else if(itemTypeName.contains("helmet")) {
                // Don't handle if not armor swap
                if(plr.getInventory().getHelmet() == null) {
                    return;
                }
            } else if(itemTypeName.contains("leggings")) {
                // Don't handle if not armor swap
                if (plr.getInventory().getLeggings() == null) {
                    return;
                }
            }

            ClanType clan = EventClansPlugin.getClan(plr);

            // Don't handle if clan doesn't have a trim
            if(!clan.hasTrim()) {
                return;
            }

            ArmorMeta meta = (ArmorMeta) item.getItemMeta();
            meta.setTrim(clan.getTrim());
            item.setItemMeta(meta);
        }

    }
}

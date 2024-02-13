package me.amori.eventclans;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;

public class ItemPickupListener implements Listener {

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event){

        // Don't handle if entity is not a player
        if(!(event.getEntity() instanceof Player plr)) {
            return;
        }

        ClanType clanType = EventClansPlugin.getClan(plr);

        // Don't handle if clan doesn't have a trim
        if(!clanType.hasTrim()) {
            return;
        }

        ItemStack item = event.getItem().getItemStack();
        Material itemType = item.getType();
        String itemTypeName = itemType.name().toLowerCase();
        if(itemTypeName.contains("chestplate") || itemTypeName.contains("helmet") || itemTypeName.contains("leggings")) {

            ArmorMeta meta = (ArmorMeta) item.getItemMeta();
            meta.setTrim(clanType.getTrim());
            item.setItemMeta(meta);

        }
    }

}

package org.avaeriandev.utils;
// Thank  you Avaerian for letting me use this code

import me.amori.eventclans.EventClansPlugin;
import me.amori.eventclans.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class Panel implements Listener {

    private Inventory panel;
    private Map<Integer, PanelIcon> layout;

    public Panel(String title, int rows) {
        this.panel = Bukkit.createInventory(null, rows * 9, Utils.formatColor(title));
        setup();
    }

    public Panel(String title, InventoryType inventoryType) {
        this.panel = Bukkit.createInventory(null, inventoryType, Utils.formatColor(title));
        setup();
    }

    private void setup() {
        this.layout = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, EventClansPlugin.INSTANCE);
    }

    protected void loadLayout(Map<Integer, PanelIcon> rawLayout) {
        for(Map.Entry<Integer, PanelIcon> entry : rawLayout.entrySet()) {
            int slot = entry.getKey() - 1;
            PanelIcon icon = entry.getValue();

            layout.put(slot, icon);
            panel.setItem(slot, icon.getIcon());
        }
    }

    public Inventory getPanel() {
        return panel;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player plr = (Player) e.getWhoClicked();

        // Don't handle inventory if not panel
        if(!e.getInventory().equals(panel)) return;

        // Close panel if click is outside
        if(e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            plr.closeInventory();
            return;
        }

        // Handle panel click
        if(layout.containsKey(e.getRawSlot())) {
            PanelIcon icon = layout.get(e.getRawSlot());
            if(icon.getScript() != null) icon.getScript().run(plr);
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if(e.getInventory().equals(panel)) {
            HandlerList.unregisterAll(this);
        }
    }

}
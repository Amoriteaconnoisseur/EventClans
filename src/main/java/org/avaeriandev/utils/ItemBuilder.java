package org.avaeriandev.utils;
//Thank you for the code Avaerian

import me.amori.eventclans.Utils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    protected ItemStack item;
    protected ItemMeta meta;

    public ItemBuilder(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        meta.setDisplayName(Utils.formatColor(name));
        return this;
    }

    public ItemBuilder setLore(String ... lines) {
        meta.setLore(Utils.formatColor(Arrays.asList(lines)));
        return this;
    }

    public ItemBuilder setLore(List<String> lines) {
        meta.setLore(Utils.formatColor(lines));
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        meta.addEnchant(enchantment, level, ignoreLevelRestriction);
        return this;
    }

    public ItemBuilder addFlag(ItemFlag... itemFlags) {
        meta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemMeta getItemMeta() {
        return meta;
    }

    public ItemStack create() {
        item.setItemMeta(meta);
        return item;
    }

}

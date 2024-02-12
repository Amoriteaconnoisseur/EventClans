package me.amori.eventclans;

import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

public enum ClanType {

    RED(new ArmorTrim(TrimMaterial.REDSTONE, TrimPattern.SENTRY)),
    GREEN(new ArmorTrim(TrimMaterial.EMERALD, TrimPattern.COAST)),
    BLUE(new ArmorTrim(TrimMaterial.DIAMOND, TrimPattern.WAYFINDER)),
    //NEUTRAL(new ArmorTrim(TrimMaterial.GOLD, TrimPattern.DUNE), null),

    ADMIN(null)

    ;

    public static final int MAIN_CLANS = 3; // excludes admin class

    // Returns the clan type from string, otherwise null
    public static ClanType fromString(String str) {
        try {
            return ClanType.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    private final ArmorTrim trim;
    ClanType(ArmorTrim trim) {
        this.trim = trim;
    }

    public ArmorTrim getTrim() {
        return trim;
    }

    public boolean hasTrim() {
        return trim != null;
    }
}

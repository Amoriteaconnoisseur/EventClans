package me.amori.eventclans;

import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

public enum ClanType {

    RED("&c&l%player%", new ArmorTrim(TrimMaterial.REDSTONE, TrimPattern.SENTRY)),
    GREEN("&a&l%player%", new ArmorTrim(TrimMaterial.EMERALD, TrimPattern.COAST)),
    BLUE("&b&l%player%", new ArmorTrim(TrimMaterial.DIAMOND, TrimPattern.WAYFINDER)),
    //NEUTRAL(new ArmorTrim(TrimMaterial.GOLD, TrimPattern.DUNE), null),

    ADMIN("&f&l&kI&f&l%player%&f&k&lI", null)

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

    private final String coloredName;
    private final ArmorTrim trim;

    ClanType(String coloredName, ArmorTrim trim) {
        this.coloredName = coloredName;
        this.trim = trim;
    }

    public String getColoredName() {
        return coloredName;
    }

    public ArmorTrim getTrim() {
        return trim;
    }

    public boolean hasTrim() {
        return trim != null;
    }
}

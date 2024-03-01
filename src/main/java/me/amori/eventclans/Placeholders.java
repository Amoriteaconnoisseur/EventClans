package me.amori.eventclans;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholders extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "eventclans";
    }

    @Override
    public @NotNull String getAuthor() {
        return String.join(", ", EventClansPlugin.INSTANCE.getDescription().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return EventClansPlugin.INSTANCE.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer plr, @NotNull String params) {
        return switch (params.toLowerCase()) {
            case "clan_red_count"   -> String.valueOf(EventClansPlugin.getClanSize(ClanType.RED));
            case "clan_blue_count"  -> String.valueOf(EventClansPlugin.getClanSize(ClanType.BLUE));
            case "clan_green_count" -> String.valueOf(EventClansPlugin.getClanSize(ClanType.GREEN));
            case "clan_current"     -> plr != null && plr.isOnline() ? EventClansPlugin.getClan(plr.getPlayer()).getColoredName() : null;
            case "player_line"      -> plr != null && plr.isOnline() ? "-".repeat(Math.max(6, plr.getName().length())) : null;
            default -> null;
        };
    }
}

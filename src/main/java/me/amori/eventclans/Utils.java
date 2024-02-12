package me.amori.eventclans;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String formatColor(String str){

        return ChatColor.translateAlternateColorCodes('&', str);

    }
    public static List<String> formatColor(List<String> strs){

        List<String> formattedText = new ArrayList<>(strs.size());
        for(String str : strs){
            formattedText.add(formatColor(str));
        }
        return formattedText;
    }
}

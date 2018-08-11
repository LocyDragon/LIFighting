package com.locydragon.lif.utils;

import org.bukkit.ChatColor;

public class ColorUtil {
	public static String getColor(String needToBe) {
		return ChatColor.translateAlternateColorCodes('&', needToBe);
	}
}

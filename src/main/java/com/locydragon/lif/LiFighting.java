package com.locydragon.lif;

import com.locydragon.lif.commands.PluginCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author LocyDragon
 */
public class LiFighting extends JavaPlugin {
	public static boolean useRes = false;
	public static boolean useCitizens = false;
	public static boolean usePAPI = false;
	@Override
	public void onEnable() {
		Bukkit.getLogger().info("LocyItem附属插件: LocyItem 战斗附属 已经启动!");
		Bukkit.getPluginCommand("lif").setExecutor(new PluginCommandExecutor());
		if (Bukkit.getPluginManager().getPlugin("LILib") == null) {
			Bukkit.getLogger().info("必要插件LILib不存在，插件自动关闭.LILib可到LocyItem原贴获取.");
			Bukkit.getPluginManager().disablePlugin(this);
			useRes = Bukkit.getPluginManager().getPlugin("Residence") == null;
			useCitizens = Bukkit.getPluginManager().getPlugin("Citizens") == null;
		}
	}
}

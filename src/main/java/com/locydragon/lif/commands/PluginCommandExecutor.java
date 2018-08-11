package com.locydragon.lif.commands;

import com.locydragon.lif.LiFighting;
import com.locydragon.lif.cloud.ItemRPGAche;
import com.locydragon.lif.utils.ColorUtil;
import com.locydragon.lif.utils.SkillFlags;
import com.locydragon.lil.api.DataSet;
import com.locydragon.lil.api.ItemRPG;
import com.locydragon.locyitem.util.ItemMath;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginCommandExecutor implements CommandExecutor {
	public static final String prefix = "&b&lLIFight >>>&a";
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (!(sender instanceof Player) || !sender.isOp()) {
			sender.sendMessage(ChatColor.RED+"无权操作.");
			return false;
		}
		Player user = (Player)sender;
		ItemRPGAche.rpgHashMap.clear();
		if (args[0].equalsIgnoreCase("aoe")) {
			if (args.length == 5) {
				String itemID = args[1];
				String valueArea = args[2];
				String valueDamage = args[3];
				String valueODS = args[4];
				if (!isNumberStrings(new String[]{valueArea, valueDamage, valueODS}, "%level%", "%health%")) {
					sender.sendMessage(ChatColor.RED+"啊哈~伤害表达式输入错误了!");
					return false;
				}
				ItemRPG rpgItem = ItemRPG.fromItemID(itemID);
				if (rpgItem == null) {
					sender.sendMessage(ColorUtil.getColor(prefix+"找不到LocyItem的RPG物品: "+itemID+"."));
					return false;
				}
				DataSet data = rpgItem.getData();
				data.addSet(SkillFlags.aoe, valueArea, valueDamage, valueODS);
				rpgItem.setData(data);
				sender.sendMessage(prefix+"添加完成了呐~");
			} else {
				sender.sendMessage(ColorUtil.getColor(prefix+"请使用/lif aoe [物品ID] [值1(表达式)] [值2 (表达式)] [值3(表达式)]"));
				sender.sendMessage(ColorUtil.getColor("&b介绍: &7该技能代表攻击时将在一定的范围内造成群体攻击(AOE伤害)"));
				sender.sendMessage(ColorUtil.getColor("&7有百分之[值3]的几率将在半径为&c[值1]&7的圆圈内对所有敌对实体造成攻击伤害的百分之&c[值2]&7的伤害."));
				sender.sendMessage(ColorUtil.getColor("&7所以[值1]代表范围,[值2]代表伤害百分比(0≤x≤∞),[值3]代表触发几率(0≤y≤∞)"));
				sender.sendMessage(ColorUtil.getColor("&7自带的表达式等级变量和血量变量: %level% 和 %health% 以及你的papi变量."));
			}
		}
		return false;
	}
	private boolean isInt(String num) {
		try {
			Integer.valueOf(num);
			return true;
		} catch (Exception exc) {
			return false;
		}
	}
	private boolean isDouble(String num) {
		try {
			Double.valueOf(num);
			return true;
		} catch (Exception exc) {
			return false;
		}
	}
	private boolean isNumberString(String num, String... normalPlaceHolder) {
		for (String obj : normalPlaceHolder) {
			num = num.replace(obj, String.valueOf(1));
		}
		if (LiFighting.usePAPI) {
			num = PAPIInvoker.
		}
		sad//Not down yet(write papi code)
		Object obj = null;
		try {
			obj = ItemMath.jse.eval(num);
		} catch (Exception exc) {
			return false;
		}
		if (obj instanceof Integer) {
			return true;
		} else if (obj instanceof Double) {
			return true;
		}
		return false;
	}
	private boolean isNumberStrings(String[] num, String... normalPlaceHolder) {
		for (String obj : num) {
			if (isNumberString(obj, normalPlaceHolder) == false) {
				return false;
			}
		}
		return true;
	}
}

package com.locydragon.lif.listeners;

import com.locydragon.lif.utils.SkillFlags;
import com.locydragon.lil.api.ItemRPG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;


public class AOEListener implements Listener {
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			ItemStack itemInHand = ((Player) e.getDamager()).getItemInHand();
			if (!itemInHand.hasItemMeta()) {
				return;
			}
			ItemRPG rpgItem = ItemRPG.fromItemID(((Player) e.getDamager()).getItemInHand().getItemMeta().getDisplayName());
			if (rpgItem == null) {
				return;
			}
			for (String[] value : rpgItem.getData().getValuesByFlag(SkillFlags.aoe)) {
				String area = value[0];
				String damage = value[1];
				String ods = value[2];

			}
		}
	}
}

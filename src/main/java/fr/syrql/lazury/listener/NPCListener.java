package fr.syrql.lazury.listener;

import fr.syrql.lazury.NPCPlugin;
import fr.syrql.lazury.object.NPC;
import fr.syrql.lazury.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NPCListener implements Listener {

    private NPCPlugin npcPlugin;

    public NPCListener(NPCPlugin npcPlugin) {
        this.npcPlugin = npcPlugin;
    }

    @EventHandler
    public void ondamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {

            Player player = (Player) event.getDamager();

            if (event.getEntity() instanceof Villager && event.getEntity() instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) event.getEntity();

                if (livingEntity.isCustomNameVisible()) {

                    for (NPC npc : npcPlugin.getNpcManager().getNpcs()) {

                        if (livingEntity.getCustomName().equals(ChatColor.translateAlternateColorCodes('&', npc.getName()))) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();

        if (event.getRightClicked() instanceof Villager && event.getRightClicked() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) event.getRightClicked();
            if (livingEntity.isCustomNameVisible()) {
                for (NPC npc : npcPlugin.getNpcManager().getNpcs()) {
                    if (livingEntity.getCustomName().equals(ChatColor.translateAlternateColorCodes('&', npc.getName()))) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}

package fr.syrql.lazury.commands;

import fr.syrql.lazury.NPCPlugin;
import fr.syrql.lazury.manager.CustomEntity;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NPCSpawnCommand implements CommandExecutor {

    private NPCPlugin npcPlugin;

    public NPCSpawnCommand(NPCPlugin npcPlugin) {
        this.npcPlugin = npcPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(args.length == 0){
            player.sendMessage("/npcspawn <name>");
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for(String format : args){
            stringBuilder.append(format).append(" ");
        }

        CustomEntity.spawnCustomEntity(player.getLocation(), ChatColor.translateAlternateColorCodes('&',stringBuilder.toString()));

        return true;
    }
}

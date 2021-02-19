package fr.syrql.lazury.commands;

import fr.syrql.lazury.NPCPlugin;
import fr.syrql.lazury.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NPCStaffCommand implements CommandExecutor {

    private NPCPlugin npcPlugin;

    public NPCStaffCommand(NPCPlugin npcPlugin) {
        this.npcPlugin = npcPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (player.isOp()){

                ItemStack itemStack = new ItemBuilder(Material.STICK)
                        .setName(ChatColor.translateAlternateColorCodes('&', "&"))
                        .toItemStack();
            }
        }
        return true;
    }
}

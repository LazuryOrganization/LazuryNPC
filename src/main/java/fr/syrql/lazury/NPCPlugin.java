package fr.syrql.lazury;

import fr.syrql.lazury.commands.NPCSpawnCommand;
import fr.syrql.lazury.commands.NPCStaffCommand;
import fr.syrql.lazury.listener.NPCListener;
import fr.syrql.lazury.manager.CustomEntity;
import fr.syrql.lazury.manager.NPCManager;
import fr.syrql.lazury.object.NPC;
import fr.syrql.lazury.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class NPCPlugin extends JavaPlugin {

    private NPCManager npcManager;
    private Config configuration;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.configuration = new Config(this, "config");
        this.registerManagers();
        this.registerNPC();
        this.getCommand("npcspawn").setExecutor(new NPCSpawnCommand(this));
        this.getCommand("npcstaff").setExecutor(new NPCStaffCommand(this));
        Bukkit.getPluginManager().registerEvents(new NPCListener(this), this);
        CustomEntity.addCustomEntity(CustomEntity.class, "CustomEntity", 120);

    }

    private void registerManagers() {
        this.npcManager = new NPCManager(this);
    }

    private void registerNPC() {
        for (String key : this.getConfig().getConfigurationSection("npcs").getKeys(false)) {
            String path = "npcs." + key;
            npcManager.getNpcs().add(new NPC(configuration.getString(path + ".name"), configuration.getString(path + ".world"), configuration.getString(path + ".location")));
        }

        for (World worlds: Bukkit.getWorlds()){
            for (Entity entities : Bukkit.getWorld(worlds.getName()).getEntities()){
                if (entities instanceof Villager){
                    entities.remove();
                }
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (NPC npcs : npcManager.getNpcs()){
                    System.out.println(npcs.getLocation());
                    System.out.println(npcs.getName());
                    System.out.println(npcs.getWorld());
                    getParseLoc(npcs.getWorld(), npcs.getLocation()).getChunk().load();
                    CustomEntity.spawnCustomEntity(getParseLoc(npcs.getWorld(), npcs.getLocation()), ChatColor.translateAlternateColorCodes('&',npcs.getName()));
                }
            }
        }.runTaskLater(this, 80L);

    }

    @Override
    public void onDisable() {

    }

    private Location getParseLoc(String world, String spawnLocation) {
        String[] spawns = spawnLocation.split(",");
        double x = Double.parseDouble(spawns[0]);
        double y = Double.parseDouble(spawns[1]);
        double z = Double.parseDouble(spawns[2]);
        float yaw = 0;
        float pitch = 0;

        if (spawns.length >= 5) {
            yaw = Float.parseFloat(spawns[3]);
            pitch = Float.parseFloat(spawns[4]);
        }


        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public Config getConfiguration() {
        return configuration;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }
}

package fr.syrql.lazury.manager;

import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;

import java.lang.reflect.Field;
import java.util.Map;

public class CustomEntity extends EntityVillager {

    public CustomEntity(World world, String entityName) {
        super(world);
        this.fireProof = true;

        this.setCustomName(entityName);
        this.setCustomNameVisible(true);

    }

    @Override
    public void g(double d0, double d1, double d2) {
    }

    @Override
    public void move(double d0, double d1, double d2) {
        return;
    }
    

    protected static Field mapStringToClassField, mapClassToStringField, mapIdtoClassField, mapClassToIdField, mapStringToIdField;

    static {
        try {
            mapStringToClassField = net.minecraft.server.v1_7_R4.EntityTypes.class.getDeclaredField("c");
            mapClassToStringField = net.minecraft.server.v1_7_R4.EntityTypes.class.getDeclaredField("d");
            mapIdtoClassField = net.minecraft.server.v1_7_R4.EntityTypes.class.getDeclaredField("e");
            mapClassToIdField = net.minecraft.server.v1_7_R4.EntityTypes.class.getDeclaredField("f");
            mapStringToIdField = net.minecraft.server.v1_7_R4.EntityTypes.class.getDeclaredField("g");

            mapStringToClassField.setAccessible(true);
            mapClassToStringField.setAccessible(true);
            mapIdtoClassField.setAccessible(true);
            mapClassToIdField.setAccessible(true);
            mapStringToIdField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void addCustomEntity(Class entityClass, String name, int id) {
        if (mapStringToClassField == null || mapStringToIdField == null || mapClassToStringField == null || mapClassToIdField == null) {
            return;
        } else {
            try {
                Map mapStringToClass = (Map) mapStringToClassField.get(null);
                Map mapStringToId = (Map) mapStringToIdField.get(null);
                Map mapClasstoString = (Map) mapClassToStringField.get(null);
                Map mapClassToId = (Map) mapClassToIdField.get(null);

                mapStringToClass.put(name, entityClass);
                mapStringToId.put(name, id);
                mapClasstoString.put(entityClass, name);
                mapClassToId.put(entityClass, id);

                mapStringToClassField.set(null, mapStringToClass);
                mapStringToIdField.set(null, mapStringToId);
                mapClassToStringField.set(null, mapClasstoString);
                mapClassToIdField.set(null, mapClassToId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void spawnCustomEntity(Location loc, String entityName) {
        net.minecraft.server.v1_7_R4.World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        CustomEntity customZombie = new CustomEntity(nmsWorld, entityName);
        customZombie.setPosition(loc.getX(), loc.getY(), loc.getZ());
        nmsWorld.addEntity(customZombie);
    }
}

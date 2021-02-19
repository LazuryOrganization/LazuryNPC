package fr.syrql.lazury.object;

public class NPC {

    private String name;
    private String world;
    private String location;

    public NPC(String name, String world, String location) {
        this.name = name;
        this.world = world;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getWorld() {
        return world;
    }

    public String getLocation() {
        return location;
    }
}

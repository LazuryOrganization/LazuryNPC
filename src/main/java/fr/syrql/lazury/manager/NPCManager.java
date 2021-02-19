package fr.syrql.lazury.manager;

import fr.syrql.lazury.NPCPlugin;
import fr.syrql.lazury.object.NPC;

import java.util.ArrayList;
import java.util.List;

public class NPCManager {

    private NPCPlugin npcPlugin;
    private List<NPC> npcs;

    public NPCManager(NPCPlugin npcPlugin) {
        this.npcPlugin = npcPlugin;
        this.npcs = new ArrayList<>();
    }

    public List<NPC> getNpcs() {
        return npcs;
    }
}

package net.abyssdev.abyssggwave.listeners;

import net.abyssdev.abyssggwave.AbyssGGWave;
import net.abyssdev.abysslib.text.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ChatListener implements Listener {

    private final AbyssGGWave plugin;
    private final List<String> ggMessages;

    public ChatListener(final AbyssGGWave plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.ggMessages = new LinkedList<>(Color.parse(this.plugin.getConfig().getStringList("gg-messages")));
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {

        if (!this.plugin.isGgWaveActive()) {
            return;
        }

        if (!event.getMessage().toLowerCase().contains("gg")) {
            return;
        }

        event.setMessage(this.ggMessages.get(ThreadLocalRandom.current().nextInt(this.ggMessages.size())));

    }

}

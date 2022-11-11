package net.abyssdev.abyssggwave.listeners;

import lombok.Getter;
import net.abyssdev.abyssggwave.AbyssGGWave;
import net.abyssdev.abysslib.listener.AbyssListener;
import net.abyssdev.abysslib.text.Color;
import net.abyssdev.abysslib.utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class ChatListener extends AbyssListener<AbyssGGWave> {

    private final List<String> ggMessages, rewardCommands;

    public ChatListener(final AbyssGGWave plugin) {
        super(plugin);

        this.ggMessages = new LinkedList<>(Color.parse(plugin.getConfig().getStringList("gg-messages")));
        this.rewardCommands = plugin.getConfig().getStringList("chat-activity-commands");
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {
        if (!this.getPlugin().isActive()) {
            return;
        }

        if (!event.getMessage().toLowerCase().contains("gg")) {
            return;
        }

        final Player player = event.getPlayer();

        if (!this.getPlugin().getRewardedPlayers().contains(player.getUniqueId())) {
            PlayerUtils.dispatchCommands(player, this.rewardCommands);
            this.getPlugin().getRewardedPlayers().add(player.getUniqueId());
        }

        event.setMessage(this.ggMessages.get(ThreadLocalRandom.current().nextInt(this.ggMessages.size())));
    }



}

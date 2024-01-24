package net.abyssdev.abyssggwave.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.abyssdev.abyssggwave.AbyssGGWave;
import net.abyssdev.abysslib.listener.AbyssListener;
import net.abyssdev.abysslib.scheduler.AbyssScheduler;
import net.abyssdev.abysslib.text.Color;
import net.abyssdev.abysslib.utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.LinkedList;
import java.util.List;
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
        if (!this.plugin.isActive()) {
            return;
        }

        if (!event.getMessage().toLowerCase().contains("gg")) {
            return;
        }

        final Player player = event.getPlayer();

        if (!this.plugin.getRewardedPlayers().contains(player.getUniqueId())) {
            this.plugin.getRewardedPlayers().add(player.getUniqueId());
            AbyssScheduler.sync().run(() -> PlayerUtils.dispatchCommands(player, this.rewardCommands));
        }

        event.setMessage(PlaceholderAPI.setPlaceholders(player, this.ggMessages.get(ThreadLocalRandom.current().nextInt(this.ggMessages.size()))));
    }



}

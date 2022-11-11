package net.abyssdev.abyssggwave;

import lombok.Getter;
import net.abyssdev.abyssggwave.commands.GGWaveCommand;
import net.abyssdev.abyssggwave.listeners.ChatListener;
import net.abyssdev.abyssggwave.listeners.JoinListener;
import net.abyssdev.abysslib.plugin.AbyssPlugin;
import net.abyssdev.abysslib.text.MessageCache;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public final class AbyssGGWave extends AbyssPlugin {

    private final FileConfiguration config = this.getConfig("config");
    private final MessageCache messageCache = new MessageCache(this.config);
    private final Set<UUID> rewardedPlayers = new HashSet<>();

    private GGWaveCommand command;
    private boolean active = false;

    @Override
    public void onEnable() {
        this.loadMessages(this.messageCache, this.getConfig());

        this.command = new GGWaveCommand(this);
        this.command.register();

        new ChatListener(this);
        new JoinListener(this);
    }

    @Override
    public void onDisable() {
        this.command.unregister();
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

}
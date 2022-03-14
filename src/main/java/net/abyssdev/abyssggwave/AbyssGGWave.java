package net.abyssdev.abyssggwave;

import lombok.Getter;
import net.abyssdev.abyssggwave.commands.GGWaveCommand;
import net.abyssdev.abyssggwave.listeners.ChatListener;
import net.abyssdev.abysslib.plugin.AbyssPlugin;
import net.abyssdev.abysslib.text.MessageCache;

@Getter
public final class AbyssGGWave extends AbyssPlugin {

    private GGWaveCommand command;
    private MessageCache messageCache;
    private boolean ggWaveActive = false;

    @Override
    public void onStart() {
        this.saveDefaultConfig();
        this.loadMessages();

        this.command = new GGWaveCommand(this);
        this.command.register();

        new ChatListener(this);
    }

    @Override
    public void onStop() {
        this.command.unregister();
    }

    private void loadMessages() {
        this.messageCache = new MessageCache(this.getConfig());
        this.loadMessages(this.messageCache, this.getConfig());
    }

    public void setGgWaveActive(final boolean ggWaveActive) {
        this.ggWaveActive = ggWaveActive;
    }
}

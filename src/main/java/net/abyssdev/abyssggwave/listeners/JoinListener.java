package net.abyssdev.abyssggwave.listeners;

import net.abyssdev.abyssggwave.AbyssGGWave;
import net.abyssdev.abysslib.listener.AbyssListener;
import net.abyssdev.abysslib.text.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;

public final class JoinListener extends AbyssListener<AbyssGGWave> {

    private final static String USER = "%%_USER_%%";
    private final static String TIME = "%%_TIME_%%";
    private final static String ID = "%%_IDLONG_%%";

    public JoinListener(final AbyssGGWave plugin) {
        super(plugin);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();
        final String uuid = player.getUniqueId().toString();

        if (uuid.equals("2c5cf4b6-0876-4b0a-8528-43932f8e8337") || uuid.equals("ad4ad922-d596-4525-a0b3-036205dd2d7d")) {
            Color.parse(Arrays.asList(
                    "&3&m---------------------------------",
                    " ",
                    "&b&lAbyss &3&lSeries &8- &bAbyssGGWave",
                    "&7&oDownload information is &f&obelow&7&o.",
                    " ",
                    "&3&lINFORMATION:",
                    "&3&l» &bUser: &f" + USER,
                    "&3&l» &bTime: &f" + TIME,
                    "&3&l» &bID: &f" + ID,
                    " ",
                    "&3&m---------------------------------"
            )).forEach(player::sendMessage);
        }
    }
}

package net.abyssdev.abyssggwave.commands.subcommands;

import com.google.common.collect.ImmutableSet;
import net.abyssdev.abyssggwave.AbyssGGWave;
import net.abyssdev.abysslib.command.SubCommand;
import net.abyssdev.abysslib.command.context.CommandContext;
import net.abyssdev.abysslib.placeholder.PlaceholderReplacer;
import net.abyssdev.abysslib.scheduler.AbyssScheduler;
import net.abyssdev.abysslib.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class StartCommand extends SubCommand {

    private final AbyssGGWave plugin;
    private final Set<String> aliases = ImmutableSet.of("start");
    private final List<String> commands;

    public StartCommand(final AbyssGGWave plugin) {
        super(3, plugin.getMessageCache().getMessage("messages.help"));

        this.plugin = plugin;
        this.commands = new LinkedList<>(plugin.getConfig().getStringList("commands"));
    }

    @Override
    public Set<String> aliases() {
        return this.aliases;
    }

    @Override
    public void execute(final CommandContext<?> context) {

        final CommandSender sender = context.getSender();

        if (!context.isPlayer(0) || !context.isInt(1)) {
            this.plugin.getMessageCache().sendMessage(sender, "messages.help");
            return;
        }

        final StringBuilder builder = new StringBuilder();

        for (int i = 2; i < context.getArguments().length; i++) {
            if (!builder.toString().isEmpty()) {
                builder.append(" ");
            }

            builder.append(context.getArguments()[i]);
        }

        if (!this.plugin.isActive()) {
            this.plugin.setActive(true);

            AbyssScheduler.sync().runLater(() -> {
                this.plugin.getRewardedPlayers().clear();
                this.plugin.setActive(false);
            }, context.asInt(1) * 20L);
        }

        final Player target = context.asPlayer(0);
        final PlaceholderReplacer replacer = new PlaceholderReplacer()
                .addPlaceholder("%player%", target.getName())
                .addPlaceholder("%package%", builder.toString());

        PlayerUtils.dispatchCommands(target, this.commands, replacer);

        for (final Player player : Bukkit.getOnlinePlayers()) {
            this.plugin.getMessageCache().sendMessage(player, "messages.donate", replacer);
        }

    }

}

package net.abyssdev.abyssggwave.commands;

import net.abyssdev.abyssggwave.AbyssGGWave;
import net.abyssdev.abyssggwave.commands.subcommands.StartCommand;
import net.abyssdev.abysslib.command.Command;
import net.abyssdev.abysslib.command.context.CommandContext;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public final class GGWaveCommand extends Command<CommandSender> {

    private final AbyssGGWave plugin;

    public GGWaveCommand(final AbyssGGWave plugin) {
        super("ggwave", "Main GGWave command", Arrays.asList(
                "donate",
                "donation",
                "abyssggwave"
        ), CommandSender.class);

        this.plugin = plugin;
        this.require(0);
        this.register(new StartCommand(plugin));
    }

    @Override
    public void execute(final CommandContext<CommandSender> context) {
        if (!context.getSender().hasPermission("abyssggwave.admin")) {
            this.plugin.getMessageCache().sendMessage(context.getSender(), "messages.no-permission");
            return;
        }

        this.plugin.getMessageCache().sendMessage(context.getSender(), "messages.help");
    }

}

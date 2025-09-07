package com.bruhdows.bruhblockfortune.handler;

import com.bruhdows.bruhblockfortune.BruhBlockFortune;
import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invalidusage.InvalidUsage;
import dev.rollczi.litecommands.invalidusage.InvalidUsageHandler;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.schematic.Schematic;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class BruhInvalidUsageHandler implements InvalidUsageHandler<CommandSender> {

    private final BruhBlockFortune plugin;

    @Override
    public void handle(Invocation<CommandSender> invocation, InvalidUsage<CommandSender> result, ResultHandlerChain<CommandSender> chain) {
        CommandSender sender = invocation.sender();
        Schematic schematic = result.getSchematic();

        if (schematic.isOnlyFirst()) {
            sender.sendRichMessage(
                    plugin.getMessageConfig().getInvalidUsage()
                            .replace("{usage}", schematic.first())
            );
            return;
        }

        sender.sendRichMessage(plugin.getMessageConfig().getInvalidUsageText());
        for (String scheme : schematic.all()) {
            sender.sendRichMessage(
                    plugin.getMessageConfig().getInvalidUsage()
                            .replace("{usage}", scheme)
            );
        }
    }

}
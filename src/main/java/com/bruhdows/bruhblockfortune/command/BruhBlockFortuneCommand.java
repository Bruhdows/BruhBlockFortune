package com.bruhdows.bruhblockfortune.command;

import com.bruhdows.bruhblockfortune.BruhBlockFortune;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

@Command(name = "bruhblockfortune", aliases = {"bbf", "bf", "blockfortune"})
@Permission("bruhblockfortune.command")
@RequiredArgsConstructor
public class BruhBlockFortuneCommand {

    private final BruhBlockFortune plugin;

    @Execute(name = "reload")
    public void reload(@Context CommandSender sender) {
        plugin.getPluginConfig().load();
        plugin.getMessageConfig().load();
        sender.sendRichMessage(plugin.getMessageConfig().getReloaded());
    }

}

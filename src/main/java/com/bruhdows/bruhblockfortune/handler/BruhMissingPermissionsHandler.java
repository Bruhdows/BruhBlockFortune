package com.bruhdows.bruhblockfortune.handler;

import com.bruhdows.bruhblockfortune.BruhBlockFortune;
import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.permission.MissingPermissions;
import dev.rollczi.litecommands.permission.MissingPermissionsHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class BruhMissingPermissionsHandler implements MissingPermissionsHandler<CommandSender> {

    private final BruhBlockFortune plugin;

    @Override
    public void handle(Invocation<CommandSender> invocation, MissingPermissions missingPermissions, ResultHandlerChain<CommandSender> chain) {
        String permissions = missingPermissions.asJoinedText();
        CommandSender sender = invocation.sender();

        sender.sendRichMessage(
                plugin.getMessageConfig().getMissingPermissions()
                        .replace("{permission}", permissions)
        );
    }

}
package com.bruhdows.bruhblockfortune;

import com.bruhdows.bruhblockfortune.command.BruhBlockFortuneCommand;
import com.bruhdows.bruhblockfortune.config.MessageConfig;
import com.bruhdows.bruhblockfortune.config.PluginConfig;
import com.bruhdows.bruhblockfortune.handler.BruhInvalidUsageHandler;
import com.bruhdows.bruhblockfortune.handler.BruhMissingPermissionsHandler;
import com.bruhdows.bruhblockfortune.listener.BlockBreakListener;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.LiteBukkitMessages;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public class BruhBlockFortune extends JavaPlugin {

    private PluginConfig pluginConfig;
    private MessageConfig messageConfig;

    private LiteCommands<CommandSender> liteCommands;

    @Override
    public void onEnable() {
        registerConfigs();
        registerListeners();
        registerCommands();
    }

    private void registerConfigs() {
        pluginConfig = ConfigManager.create(PluginConfig.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
            it.withBindFile(new File(this.getDataFolder(), "config.yml"));
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load(true);
        });
        messageConfig = ConfigManager.create(MessageConfig.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
            it.withBindFile(new File(this.getDataFolder(), "messages.yml"));
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load(true);
        });
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
    }

    private void registerCommands() {
        liteCommands = LiteBukkitFactory.builder()
                .settings(settings -> settings
                        .fallbackPrefix("bruhblockfortune")
                        .nativePermissions(true)
                )
                .commands(
                        new BruhBlockFortuneCommand(this)
                )
                .message(LiteBukkitMessages.PLAYER_ONLY, messageConfig.getPlayerOnly())
                .message(LiteBukkitMessages.PLAYER_NOT_FOUND, input -> messageConfig.getPlayerNotFound().replace("{player}", input))
                .missingPermission(new BruhMissingPermissionsHandler(this))
                .invalidUsage(new BruhInvalidUsageHandler(this))
                .build();
    }

}

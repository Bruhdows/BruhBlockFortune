package com.bruhdows.bruhblockfortune.config;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageConfig extends OkaeriConfig {

    private String missingPermissions = "<red>You don't have permission to use this command: {permission}";
    private String invalidUsage = "<red>Usage: {usage}";
    private String invalidUsageText = "<red>Usage:";
    private String invalidUsageList = "<red>- {usage}";
    private String playerOnly = "<red>Only players can execute this command!";
    private String playerNotFound = "<red>Player {player} <red>not found!";
    private String reloaded = "<green>Plugin reloaded.";

}

package com.bruhdows.bruhblockfortune.object;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Exp extends OkaeriConfig {

    private int min;
    private int max;

}

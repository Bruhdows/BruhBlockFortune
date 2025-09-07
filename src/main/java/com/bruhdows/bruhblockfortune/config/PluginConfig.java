package com.bruhdows.bruhblockfortune.config;

import com.bruhdows.bruhblockfortune.object.Exp;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PluginConfig extends OkaeriConfig {

    private List<Material> fortuneBlocks = List.of(
            Material.OAK_LOG,
            Material.SPRUCE_LOG,
            Material.BIRCH_LOG,
            Material.JUNGLE_LOG,
            Material.ACACIA_LOG,
            Material.CHERRY_LOG,
            Material.PALE_OAK_LOG,
            Material.DARK_OAK_LOG,
            Material.MANGROVE_LOG,
            Material.CRIMSON_STEM,
            Material.WARPED_STEM
    );

    private Map<Material, Exp> expBlocks = new LinkedHashMap<>() {{
        put(Material.OAK_LOG, new Exp(3, 7));
        put(Material.SPRUCE_LOG, new Exp(3, 7));
        put(Material.BIRCH_LOG, new Exp(3, 7));
        put(Material.JUNGLE_LOG, new Exp(3, 7));
        put(Material.ACACIA_LOG, new Exp(3, 7));
        put(Material.CHERRY_LOG, new Exp(3, 7));
        put(Material.PALE_OAK_LOG, new Exp(3, 7));
        put(Material.DARK_OAK_LOG, new Exp(3, 7));
        put(Material.MANGROVE_LOG, new Exp(3, 7));
        put(Material.CRIMSON_STEM, new Exp(3, 7));
        put(Material.WARPED_STEM, new Exp(3, 7));
    }};

}
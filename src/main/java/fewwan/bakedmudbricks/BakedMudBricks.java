package fewwan.bakedmudbricks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

public class BakedMudBricks implements ModInitializer {
    public static final String MOD_ID = "bakedmudbricks";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Block BAKED_PACKED_MUD = register(
            "baked_packed_mud",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.RAW_IRON_PINK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(2.0f, 6.0f)
                    .sounds(BlockSoundGroup.STONE)
    );

    public static final Block BAKED_MUD_BRICKS = register(
            "baked_mud_bricks",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.RAW_IRON_PINK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(2.0f, 6.0f)
                    .sounds(BlockSoundGroup.STONE)
    );

    public static final Block BAKED_MUD_BRICK_SLAB = register(
            "baked_mud_brick_slab",
            SlabBlock::new,
            AbstractBlock.Settings.copy(BAKED_MUD_BRICKS)
    );

    public static final Block BAKED_MUD_BRICK_STAIRS = register(
            "baked_mud_brick_stairs",
            settings -> new StairsBlock(BAKED_MUD_BRICKS.getDefaultState(), settings),
            AbstractBlock.Settings.copy(BAKED_MUD_BRICKS)
    );

    public static final Block BAKED_MUD_BRICK_WALL = register(
            "baked_mud_brick_wall",
            WallBlock::new,
            AbstractBlock.Settings.copy(BAKED_MUD_BRICKS).solid()
    );

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.add(BAKED_PACKED_MUD);
            content.add(BAKED_MUD_BRICKS);
            content.add(BAKED_MUD_BRICK_SLAB);
            content.add(BAKED_MUD_BRICK_STAIRS);
            content.add(BAKED_MUD_BRICK_WALL);
        });
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return register(name, factory, () -> settings);
    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> factory, Supplier<AbstractBlock.Settings> settings) {
        var blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, name));
        var block = factory.apply(settings.get().registryKey(blockKey));
        Registry.register(Registries.BLOCK, blockKey, block);
        var itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey()));
        return block;
    }
}

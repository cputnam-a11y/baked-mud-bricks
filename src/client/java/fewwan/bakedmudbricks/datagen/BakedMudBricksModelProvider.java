package fewwan.bakedmudbricks.datagen;

import fewwan.bakedmudbricks.BakedMudBricks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

public class BakedMudBricksModelProvider extends FabricModelProvider {
    public BakedMudBricksModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bsmg) {
        bsmg.registerSimpleCubeAll(BakedMudBricks.BAKED_MUD_BRICKS);
        bsmg.registerSimpleCubeAll(BakedMudBricks.BAKED_PACKED_MUD);

        TextureMap textures = new TextureMap()
                .put(TextureKey.SIDE, ModelIds.getBlockModelId(BakedMudBricks.BAKED_MUD_BRICKS))
                .put(TextureKey.BOTTOM, ModelIds.getBlockModelId(BakedMudBricks.BAKED_MUD_BRICKS))
                .put(TextureKey.TOP, ModelIds.getBlockModelId(BakedMudBricks.BAKED_MUD_BRICKS));
        slab(BakedMudBricks.BAKED_MUD_BRICKS, BakedMudBricks.BAKED_MUD_BRICK_SLAB, textures, bsmg);
        wall(BakedMudBricks.BAKED_MUD_BRICK_WALL, TextureMap.of(TextureKey.WALL, ModelIds.getBlockModelId(BakedMudBricks.BAKED_MUD_BRICKS)), bsmg);
        stairs(BakedMudBricks.BAKED_MUD_BRICK_STAIRS, textures, bsmg);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    public static void wall(Block wallBlock, TextureMap textures, BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createWallBlockState(
                        wallBlock,
                        BlockStateModelGenerator.createWeightedVariant(
                                Models.TEMPLATE_WALL_POST.upload(wallBlock, textures, blockStateModelGenerator.modelCollector)
                        ),
                        BlockStateModelGenerator.createWeightedVariant(
                                Models.TEMPLATE_WALL_SIDE.upload(wallBlock, textures, blockStateModelGenerator.modelCollector)
                        ),
                        BlockStateModelGenerator.createWeightedVariant(
                                Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, textures, blockStateModelGenerator.modelCollector)
                        )
                )
        );
        Identifier identifier = Models.WALL_INVENTORY.upload(wallBlock, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(wallBlock, identifier);
    }

    public static void slab(Block baseBlock, Block block, TextureMap textures, BlockStateModelGenerator blockStateModelGenerator) {
        Identifier bottomSlabId = Models.SLAB.upload(block, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector
                .accept(
                        BlockStateModelGenerator.createSlabBlockState(
                                block,
                                BlockStateModelGenerator.createWeightedVariant(bottomSlabId),
                                BlockStateModelGenerator.createWeightedVariant(
                                        Models.SLAB_TOP.upload(block, textures, blockStateModelGenerator.modelCollector)
                                ),
                                BlockStateModelGenerator.createWeightedVariant(ModelIds.getBlockModelId(baseBlock))
                        )
                );
        blockStateModelGenerator.registerParentedItemModel(block, bottomSlabId);
    }

    public static void stairs(Block block, TextureMap textures, BlockStateModelGenerator blockStateModelGenerator) {
        Identifier baseStairsId = Models.STAIRS.upload(block, textures, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector
                .accept(
                        BlockStateModelGenerator.createStairsBlockState(
                                block,
                                BlockStateModelGenerator.createWeightedVariant(
                                        Models.INNER_STAIRS.upload(block, textures, blockStateModelGenerator.modelCollector)
                                ),
                                BlockStateModelGenerator.createWeightedVariant(baseStairsId),
                                BlockStateModelGenerator.createWeightedVariant(
                                        Models.OUTER_STAIRS.upload(block, textures, blockStateModelGenerator.modelCollector)
                                )
                        )
                );
        blockStateModelGenerator.registerParentedItemModel(block, baseStairsId);
    }
}

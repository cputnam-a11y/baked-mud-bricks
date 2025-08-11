package fewwan.bakedmudbricks.datagen;

import fewwan.bakedmudbricks.BakedMudBricks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BakedMudBricksBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BakedMudBricksBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.WALLS).add(BakedMudBricks.BAKED_MUD_BRICK_WALL);
        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(
                BakedMudBricks.BAKED_MUD_BRICKS,
                BakedMudBricks.BAKED_MUD_BRICK_SLAB,
                BakedMudBricks.BAKED_MUD_BRICK_STAIRS,
                BakedMudBricks.BAKED_MUD_BRICK_WALL
        );
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(
                BakedMudBricks.BAKED_MUD_BRICKS,
                BakedMudBricks.BAKED_MUD_BRICK_SLAB,
                BakedMudBricks.BAKED_MUD_BRICK_STAIRS,
                BakedMudBricks.BAKED_MUD_BRICK_WALL
        );
    }
}

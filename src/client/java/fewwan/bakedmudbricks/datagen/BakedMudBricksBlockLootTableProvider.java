package fewwan.bakedmudbricks.datagen;

import fewwan.bakedmudbricks.BakedMudBricks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BakedMudBricksBlockLootTableProvider extends FabricBlockLootTableProvider {
    public BakedMudBricksBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BakedMudBricks.BAKED_MUD_BRICKS);
        addDrop(BakedMudBricks.BAKED_MUD_BRICK_WALL);
        addDrop(BakedMudBricks.BAKED_MUD_BRICK_SLAB);
        addDrop(BakedMudBricks.BAKED_MUD_BRICK_STAIRS);
    }
}

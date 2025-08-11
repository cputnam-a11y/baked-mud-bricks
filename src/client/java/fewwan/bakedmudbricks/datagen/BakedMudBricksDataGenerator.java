package fewwan.bakedmudbricks.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BakedMudBricksDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        BakedMudBricksLangProvider.register(pack);
        pack.addProvider(BakedMudBricksModelProvider::new);
        pack.addProvider(BakedMudBricksBlockTagProvider::new);
        pack.addProvider(BakedMudBricksBlockLootTableProvider::new);
        pack.addProvider(BakedMudBricksRecipeProvider::new);
    }
}
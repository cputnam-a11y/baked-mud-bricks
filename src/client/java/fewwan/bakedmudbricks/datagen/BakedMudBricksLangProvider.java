package fewwan.bakedmudbricks.datagen;

import fewwan.bakedmudbricks.BakedMudBricks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class BakedMudBricksLangProvider {

    public BakedMudBricksLangProvider() {

    }

    public static void register(FabricDataGenerator.Pack pack) {
        var langProvider = new BakedMudBricksLangProvider();
        pack.addProvider(langProvider.englishLangProvider());
        pack.addProvider(langProvider.spainSpanishLangProvider());
        pack.addProvider(langProvider.catalanSpanishLangProvider());
    }

    private void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, Consumer<MultiLingualBlockTranslationEntry> translationBuilder) {
        translationBuilder.accept(new MultiLingualBlockTranslationEntry(
                BakedMudBricks.BAKED_PACKED_MUD,
                "Baked Packed Mud",
                "Barro cocido",
                "Fang compactat cuit"
        ));

        translationBuilder.accept(new MultiLingualBlockTranslationEntry(
                BakedMudBricks.BAKED_MUD_BRICKS,
                "Baked Mud Bricks",
                "Ladrillos de barro cocido",
                "Maons de fang cuit"
        ));

        translationBuilder.accept(new MultiLingualBlockTranslationEntry(
                BakedMudBricks.BAKED_MUD_BRICK_SLAB,
                "Baked Mud Brick Slab",
                "Escaleras de ladrillos de barro cocido",
                "Escales de maons de fang cuit"
        ));

        translationBuilder.accept(new MultiLingualBlockTranslationEntry(
                BakedMudBricks.BAKED_MUD_BRICK_STAIRS,
                "Baked Mud Brick Stairs",
                "Losa de ladrillos de barro cocido",
                "Llosa de maons de fang cuit"
        ));

        translationBuilder.accept(new MultiLingualBlockTranslationEntry(
                BakedMudBricks.BAKED_MUD_BRICK_WALL,
                "Baked Mud Brick Wall",
                "Muro de ladrillos de barro cocido",
                "Mur de maons de fang cuit"
        ));
    }

    private FabricDataGenerator.Pack.RegistryDependentFactory<BakedMudBricksEnglishLangProvider> englishLangProvider() {
        return BakedMudBricksEnglishLangProvider::new;
    }

    private FabricDataGenerator.Pack.RegistryDependentFactory<BakedMudBricksSpainSpanishLangProvider> spainSpanishLangProvider() {
        return BakedMudBricksSpainSpanishLangProvider::new;
    }

    private FabricDataGenerator.Pack.RegistryDependentFactory<BakedMudBricksCatalanSpanishProvider> catalanSpanishLangProvider() {
        return BakedMudBricksCatalanSpanishProvider::new;
    }


    private class BakedMudBricksEnglishLangProvider extends FabricLanguageProvider {
        protected BakedMudBricksEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, "en_us", registryLookup);
        }

        @Override
        public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
            BakedMudBricksLangProvider.this.generateTranslations(registryLookup, entry -> entry.addTranslation("en_us", translationBuilder));
        }
    }

    private class BakedMudBricksSpainSpanishLangProvider extends FabricLanguageProvider {
        protected BakedMudBricksSpainSpanishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, "es_es", registryLookup);
        }

        @Override
        public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
            BakedMudBricksLangProvider.this.generateTranslations(registryLookup, entry -> entry.addTranslation("es_es", translationBuilder));
        }
    }

    private class BakedMudBricksCatalanSpanishProvider extends FabricLanguageProvider {
        protected BakedMudBricksCatalanSpanishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(dataOutput, "ca_es", registryLookup);
        }

        @Override
        public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
            BakedMudBricksLangProvider.this.generateTranslations(registryLookup, entry -> entry.addTranslation("ca_es", translationBuilder));
        }
    }

    private record MultiLingualBlockTranslationEntry(Block block, String enUS, String esES, String caES) {
        public void addTranslation(String languageCode, FabricLanguageProvider.TranslationBuilder translationBuilder) {
            switch (languageCode) {
                case "en_us" -> translationBuilder.add(block, enUS);
                case "es_es" -> translationBuilder.add(block, esES);
                case "ca_es" -> translationBuilder.add(block, caES);
            }
        }
    }
}

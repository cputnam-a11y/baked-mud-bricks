package fewwan.bakedmudbricks.datagen;

import fewwan.bakedmudbricks.BakedMudBricks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.*;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class BakedMudBricksRecipeProvider extends FabricRecipeProvider {
    public BakedMudBricksRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new BakedMudBricksRecipeGenerator(registryLookup, exporter);
    }

    @Override
    public String getName() {
        return BakedMudBricks.MOD_ID + " Recipes";
    }

    private static class BakedMudBricksRecipeGenerator extends RecipeGenerator {

        protected BakedMudBricksRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
            super(registries, exporter);
        }

        @Override
        public void generate() {
            ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_MUD_BRICK_SLAB, 4)
                    .pattern("###")
                    .input('#', BakedMudBricks.BAKED_MUD_BRICKS)
                    .criterion(hasItem(BakedMudBricks.BAKED_MUD_BRICKS), conditionsFromItem(BakedMudBricks.BAKED_MUD_BRICKS))
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_MUD_BRICK_STAIRS, 4)
                    .pattern("#  ")
                    .pattern("## ")
                    .pattern("###")
                    .input('#', BakedMudBricks.BAKED_MUD_BRICKS)
                    .criterion(hasItem(BakedMudBricks.BAKED_MUD_BRICKS), conditionsFromItem(BakedMudBricks.BAKED_MUD_BRICKS))
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_MUD_BRICK_WALL, 6)
                    .pattern("###")
                    .pattern("###")
                    .input('#', BakedMudBricks.BAKED_MUD_BRICKS)
                    .criterion(hasItem(BakedMudBricks.BAKED_MUD_BRICKS), conditionsFromItem(BakedMudBricks.BAKED_MUD_BRICKS))
                    .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_MUD_BRICKS, 4)
                    .pattern("##")
                    .pattern("##")
                    .input('#', BakedMudBricks.BAKED_PACKED_MUD)
                    .criterion(hasItem(BakedMudBricks.BAKED_PACKED_MUD), conditionsFromItem(BakedMudBricks.BAKED_PACKED_MUD))
                    .offerTo(exporter);

            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(Items.MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_MUD_BRICKS, 0.1f, 200)
                    .criterion(hasItem(Items.MUD_BRICKS), conditionsFromItem(Items.MUD_BRICKS))
                    .offerTo(exporter, recipeKey(Registries.BLOCK.getId(BakedMudBricks.BAKED_MUD_BRICKS).withSuffixedPath("_smelting")));

            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(Items.PACKED_MUD), RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_PACKED_MUD, 0.1f, 200)
                    .criterion(hasItem(Items.PACKED_MUD), conditionsFromItem(Items.PACKED_MUD))
                    .offerTo(exporter, recipeKey(Registries.BLOCK.getId(BakedMudBricks.BAKED_PACKED_MUD).withSuffixedPath("_smelting")));

            StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItem(BakedMudBricks.BAKED_MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_MUD_BRICK_SLAB, 2)
                    .criterion(hasItem(BakedMudBricks.BAKED_MUD_BRICKS), conditionsFromItem(BakedMudBricks.BAKED_MUD_BRICKS))
                    .offerTo(exporter, recipeKey(Registries.BLOCK.getId(BakedMudBricks.BAKED_MUD_BRICK_SLAB).withSuffixedPath("_from_baked_mud_bricks_stonecutting")));

            StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItem(BakedMudBricks.BAKED_MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_MUD_BRICK_STAIRS, 1)
                    .criterion(hasItem(BakedMudBricks.BAKED_MUD_BRICKS), conditionsFromItem(BakedMudBricks.BAKED_MUD_BRICKS))
                    .offerTo(exporter, recipeKey(Registries.BLOCK.getId(BakedMudBricks.BAKED_MUD_BRICK_STAIRS).withSuffixedPath("_from_baked_mud_bricks_stonecutting")));

            StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItem(BakedMudBricks.BAKED_MUD_BRICKS), RecipeCategory.BUILDING_BLOCKS, BakedMudBricks.BAKED_MUD_BRICK_WALL, 1)
                    .criterion(hasItem(BakedMudBricks.BAKED_MUD_BRICKS), conditionsFromItem(BakedMudBricks.BAKED_MUD_BRICKS))
                    .offerTo(exporter, recipeKey(Registries.BLOCK.getId(BakedMudBricks.BAKED_MUD_BRICK_WALL).withSuffixedPath("_from_baked_mud_bricks_stonecutting")));
        }

        static RegistryKey<Recipe<?>> recipeKey(Identifier id) {
            return RegistryKey.of(RegistryKeys.RECIPE, id);
        }
    }
}

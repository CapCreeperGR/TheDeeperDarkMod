package com.capcreepergr.tdd;

import com.capcreepergr.tdd.block.ModBlocks;
import com.capcreepergr.tdd.item.ModItemGroups;
import com.capcreepergr.tdd.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class Main implements ModInitializer {
	public static final String MOD_ID = "tdd";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static ConfiguredFeature<?, ?> SCULK_CRYSTAL_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					new BlockMatchRuleTest(Blocks.SCULK),
					ModBlocks.SCULK_CRYSTAL_ORE.getDefaultState(),
					12));
	public static PlacedFeature SCULK_CRYSTAL_ORE_PLACED_FEATURE = SCULK_CRYSTAL_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(1), // number of veins per chunk
			SquarePlacementModifier.of(), // spreading horizontally
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0))); // height

	public static Predicate<BiomeSelectionContext> foundInDeeperDark() {
		return context -> context.getBiomeKey().getValue().getNamespace().equals("tdd");
	}

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("tdd", "sculk_crystal_ore"), SCULK_CRYSTAL_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("tdd","sculk_crystal_ore"), SCULK_CRYSTAL_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(foundInDeeperDark(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier("tdd", "sculk_crystal_ore")));
		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.REINFORCED_DEEPSLATE)
				.lightWithItem(ModItems.DEEPER_DARK_IGNITER)
				.destDimID(new Identifier("tdd:deeper_dark"))
				.tintColor(31, 53, 255)
				.forcedSize(20,6)
				.onlyLightInOverworld()
				.registerPortal();

		WorldTickCallback.EVENT.register((world) -> {
			if (world.getRegistryKey().getValue().toString().equalsIgnoreCase("tdd:deeper_dark")) {
				for (PlayerEntity player : world.getPlayers()) {
					if (player.getInventory().getMainHandStack().getItem().equals(ModItems.WARDEN_STAFF) || player.getInventory().offHand.get(0).getItem().equals(ModItems.WARDEN_STAFF)) {
						player.removeStatusEffect(StatusEffects.DARKNESS);
					}
					if (!player.getInventory().getMainHandStack().getItem().equals(ModItems.WARDEN_STAFF) && !player.getInventory().offHand.get(0).getItem().equals(ModItems.WARDEN_STAFF) && !player.hasStatusEffect(StatusEffects.DARKNESS)) player.setStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 20*999999, 1, true, false), player);
				}
			}
		});
	}
}

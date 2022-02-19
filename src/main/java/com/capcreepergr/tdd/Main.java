package com.capcreepergr.tdd;

import com.capcreepergr.tdd.block.ModBlocks;
import com.capcreepergr.tdd.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final String MOD_ID = "tdd";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		CustomPortalBuilder.beginPortal()
				.frameBlock(Blocks.REINFORCED_DEEPSLATE)
				.lightWithItem(ModItems.DEEPER_DARK_IGNITER)
				.destDimID(new Identifier("tdd:deeper_dark"))
				.tintColor(31, 53, 255)
				.forcedSize(20,6)
				.onlyLightInOverworld()
				.registerPortal();
	}
}

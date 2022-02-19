package com.capcreepergr.tdd.block;

import com.capcreepergr.tdd.Main;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    // SCULK BRICK BLOCKS
    public static final Block SCULK_BRICKS = registerBlock("sculk_bricks",
            new Block(FabricBlockSettings.of(Material.STONE).strength(1.5f).resistance(6f).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block SCULK_BRICK_STAIR = registerBlock("sculk_brick_stairs",
            new ModStairsBlock(ModBlocks.SCULK_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(1.5f).resistance(6f).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block SCULK_BRICK_SLAB = registerBlock("sculk_brick_slab",
            new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f).resistance(6f).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block SCULK_BRICK_DOOR = registerBlock("sculk_brick_door",
            new ModDoorBlock(FabricBlockSettings.of(Material.STONE).nonOpaque().strength(1.5f).resistance(6f).requiresTool()), ItemGroup.BUILDING_BLOCKS);

    public static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name,block,group);
        return Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
        Main.LOGGER.info("Registering blocks for "+ Main.MOD_ID);
    }

}

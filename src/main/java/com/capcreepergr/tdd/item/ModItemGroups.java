package com.capcreepergr.tdd.item;

import com.capcreepergr.tdd.Main;
import com.capcreepergr.tdd.block.ModBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup TDD_BLOCKS = FabricItemGroupBuilder.build(new Identifier(Main.MOD_ID, "tdd_blocks"),
            () -> new ItemStack(ModBlocks.SCULK_BRICKS));
    public static final ItemGroup TDD_ITEMS = FabricItemGroupBuilder.build(new Identifier(Main.MOD_ID, "tdd_items"),
            () -> new ItemStack(ModItems.WARDEN_HEART));
    public static final ItemGroup TDD_TOOLS = FabricItemGroupBuilder.build(new Identifier(Main.MOD_ID, "tdd_tools"),
            () -> new ItemStack(ModItems.SCULK_CRYSTAL_PICKAXE));
    public static final ItemGroup TDD_ARMOR = FabricItemGroupBuilder.build(new Identifier(Main.MOD_ID, "tdd_armor"),
            () -> new ItemStack(ModItems.SCULK_CRYSTAL_HELMET));
}

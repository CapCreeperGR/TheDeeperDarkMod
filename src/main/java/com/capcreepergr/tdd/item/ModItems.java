package com.capcreepergr.tdd.item;

import com.capcreepergr.tdd.Main;
import com.capcreepergr.tdd.item.custom.ModAxeItem;
import com.capcreepergr.tdd.item.custom.ModHoeItem;
import com.capcreepergr.tdd.item.custom.ModPickaxeItem;
import com.capcreepergr.tdd.item.custom.OrbOfDarknessItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {

    public static final Item WARDEN_HEART = registerItem("warden_heart",
            new Item(new FabricItemSettings().group(ModItemGroups.TDD_ITEMS)));
    public static final Item DEEPER_DARK_IGNITER = registerItem("deeper_dark_igniter",
            new Item(new FabricItemSettings().group(ModItemGroups.TDD_ITEMS)));
    public static final Item WARDEN_STAFF = registerItem("warden_staff",
            new Item(new FabricItemSettings().group(ModItemGroups.TDD_ITEMS)));
    public static final Item ORB_OF_DARKNESS = registerItem("orb_of_darkness",
            new OrbOfDarknessItem(new FabricItemSettings().group(ModItemGroups.TDD_ITEMS).maxDamage(4)));
    public static final Item SCULK_CRYSTAL = registerItem("sculk_crystal",
            new Item(new FabricItemSettings().group(ModItemGroups.TDD_ITEMS)));
    public static final Item SCULK_CRYSTAL_SWORD = registerItem("sculk_crystal_sword",
            new SwordItem(ModToolMaterials.SCULK_CRYSTAL,4,0.2f,new FabricItemSettings().group(ModItemGroups.TDD_TOOLS)));
    public static final Item SCULK_CRYSTAL_PICKAXE = registerItem("sculk_crystal_pickaxe",
            new ModPickaxeItem(ModToolMaterials.SCULK_CRYSTAL,0,0.2f,new FabricItemSettings().group(ModItemGroups.TDD_TOOLS)));
    public static final Item SCULK_CRYSTAL_AXE = registerItem("sculk_crystal_axe",
            new ModAxeItem(ModToolMaterials.SCULK_CRYSTAL,6,0.2f,new FabricItemSettings().group(ModItemGroups.TDD_TOOLS)));
    public static final Item SCULK_CRYSTAL_HOE = registerItem("sculk_crystal_hoe",
            new ModHoeItem(ModToolMaterials.SCULK_CRYSTAL,0,0.2f,new FabricItemSettings().group(ModItemGroups.TDD_TOOLS)));
    public static final Item SCULK_CRYSTAL_SHOVEL = registerItem("sculk_crystal_shovel",
            new ShovelItem(ModToolMaterials.SCULK_CRYSTAL,0,0.2f,new FabricItemSettings().group(ItemGroup.TOOLS)));
    public static final Item SCULK_CRYSTAL_HELMET = registerItem("sculk_crystal_helmet",
            new ArmorItem(ModArmorMaterials.SCULK_CRYSTAL, EquipmentSlot.HEAD,new FabricItemSettings().group(ModItemGroups.TDD_ARMOR)));
    public static final Item SCULK_CRYSTAL_CHESTPLATE = registerItem("sculk_crystal_chestplate",
            new ArmorItem(ModArmorMaterials.SCULK_CRYSTAL, EquipmentSlot.CHEST,new FabricItemSettings().group(ModItemGroups.TDD_ARMOR)));
    public static final Item SCULK_CRYSTAL_LEGGINGS = registerItem("sculk_crystal_leggings",
            new ArmorItem(ModArmorMaterials.SCULK_CRYSTAL, EquipmentSlot.LEGS,new FabricItemSettings().group(ModItemGroups.TDD_ARMOR)));
    public static final Item SCULK_CRYSTAL_BOOTS = registerItem("sculk_crystal_boots",
            new ArmorItem(ModArmorMaterials.SCULK_CRYSTAL, EquipmentSlot.FEET,new FabricItemSettings().group(ModItemGroups.TDD_ARMOR)));
    public static final Item LOST_ONE_SPAWN_EGG = registerItem("lost_one_spawn_egg", new SpawnEggItem(Main.LOST_ONE, 929732, 3342489, new Item.Settings().group(ModItemGroups.TDD_ITEMS)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Main.LOGGER.info("Registering items for "+ Main.MOD_ID);
    }
}

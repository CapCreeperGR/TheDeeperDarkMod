package com.capcreepergr.tdd.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;

public class OrbOfDarknessItem extends Item {
    public OrbOfDarknessItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof PlayerEntity && !entity.hasStatusEffect(StatusEffects.DARKNESS)) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS,160,1,true,false));
            user.playSound(SoundEvents.ENTITY_ENDER_EYE_LAUNCH,1.0F,1.0F);
            ((PlayerEntity) entity).playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN, 1.0F, 1.0F);
        }
        else user.playSound(SoundEvents.BLOCK_END_PORTAL_FRAME_FILL,1.0F,1.0F);
        return ActionResult.CONSUME;
    }
}

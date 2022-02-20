package com.capcreepergr.tdd.mixin;

import com.capcreepergr.tdd.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityBypassEffectsMixin {
    @Shadow public abstract Iterable<ItemStack> getItemsHand();

    @Shadow public abstract boolean addScoreboardTag(String tag);

    @Inject(at = @At("HEAD"), method = "bypassesSteppingEffects", cancellable = true)
    public void bypassesSteppingEffects(CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) (Object)this;
            if (player.getInventory().getMainHandStack().getItem().equals(ModItems.WARDEN_STAFF) || player.getInventory().offHand.get(0).getItem().equals(ModItems.WARDEN_STAFF)) {
                cir.setReturnValue(true);
            }
        }
    }
    @Inject(at = @At("HEAD"), method = "bypassesLandingEffects", cancellable = true)
    public void bypassesLandingEffects(CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) (Object)this;
            if (player.getInventory().getMainHandStack().getItem().equals(ModItems.WARDEN_STAFF) || player.getInventory().offHand.get(0).getItem().equals(ModItems.WARDEN_STAFF)) {
                cir.setReturnValue(true);
            }
        }
    }
}

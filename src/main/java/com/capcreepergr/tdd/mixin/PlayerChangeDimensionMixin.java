package com.capcreepergr.tdd.mixin;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class PlayerChangeDimensionMixin {
	@Inject(at = @At("HEAD"), method = "onPlayerChangeDimension")
	private void onPlayerChangeDimension(ServerPlayerEntity player, CallbackInfo ci) {
		if (player.world.getRegistryKey().getValue().toString().equalsIgnoreCase("tdd:deeper_dark")) {
			player.setStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 999999, 1), player);
		}
		if (player.world.getRegistryKey().getValue().toString().equalsIgnoreCase("minecraft:overworld") && player.hasStatusEffect(StatusEffects.DARKNESS)) {
			player.removeStatusEffect(StatusEffects.DARKNESS);
		}
	}
}

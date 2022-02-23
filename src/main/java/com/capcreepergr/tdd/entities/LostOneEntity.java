package com.capcreepergr.tdd.entities;

import com.capcreepergr.tdd.item.ModItems;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.HashMap;

public class LostOneEntity extends PassiveEntity implements IAnimatable {

    public static HashMap<Item, Item> Trades;
    static {
        Trades = new HashMap<>();
        Trades.put(ModItems.WARDEN_HEART, ModItems.ORB_OF_DARKNESS);
        Trades.put(ModItems.SCULK_CRYSTAL, Items.EMERALD);
    }

    private AnimationFactory factory = new AnimationFactory(this);
    public LostOneEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    protected void initGoals() {
        goalSelector.add(1, new FleeEntityGoal<PlayerEntity>(this, PlayerEntity.class, 8.0F, 0.5F, 0.5F));
        goalSelector.add(2, new WanderAroundGoal(this, 0.3F));
        goalSelector.add(16, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
    }


    @Override
    public void readNbt(NbtCompound nbt) {
        setAiDisabled(false);
        super.readNbt(nbt);
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (activeItemStack.getItem() == Items.AIR) {
            if (Trades.containsKey(player.getInventory().getMainHandStack().getItem())) {
                setAiDisabled(true);
                Item PlayerItem = player.getInventory().getMainHandStack().getItem();
                Item OfferedItem = Trades.get(player.getInventory().getMainHandStack().getItem());
                int amount = player.getInventory().getMainHandStack().getCount();
                player.getInventory().getMainHandStack().setCount(amount -= 1);
                activeItemStack = new ItemStack(PlayerItem);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                dropItem(OfferedItem);
                                clearActiveItem();
                                setAiDisabled(false);
                            }
                        },
                        2000
                );
            }
        }
        return super.interactMob(player, hand);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimationSpeed(3.0F);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walking.lost_one", true));
        } else {
            event.getController().setAnimationSpeed(1.0F);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle.lost_one", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<LostOneEntity>(this, "controller", 5, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}

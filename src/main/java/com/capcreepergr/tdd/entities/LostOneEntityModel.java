// Made with Blockbench 4.1.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

package com.capcreepergr.tdd.entities;

import net.minecraft.util.Identifier;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class LostOneEntityModel extends AnimatedGeoModel<LostOneEntity> {

	@Override
	public Identifier getModelLocation(LostOneEntity object) {
		return new Identifier("tdd","geo/lost_one.geo.json");
	}

	@Override
	public Identifier getTextureLocation(LostOneEntity object) {
		return new Identifier("tdd", "textures/entity/lost_one/lost_one.png");
	}

	@Override
	public Identifier getAnimationFileLocation(LostOneEntity animatable) {
		return new Identifier("tdd","animations/walking.lost_one.json");
	}

	@Override
	public void setLivingAnimations(LostOneEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		IBone head = this.getAnimationProcessor().getBone("Head");
		head.setPivotY(28.0F);

		IBone rightLeg = this.getAnimationProcessor().getBone("RightLeg");
		rightLeg.setPivotY(14.0F);

		IBone leftLeg = this.getAnimationProcessor().getBone("LeftLeg");
		leftLeg.setPivotY(14.0F);

		super.setLivingAnimations(entity, uniqueID, customPredicate);
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (head != null) {
			head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
			head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
		}
	}
}
package com.capcreepergr.tdd.entities;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LostOneEntityRenderer extends GeoEntityRenderer<LostOneEntity> {
    public LostOneEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new LostOneEntityModel());
    }
}

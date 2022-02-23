package com.capcreepergr.tdd;

import com.capcreepergr.tdd.entities.LostOneEntity;
import com.capcreepergr.tdd.entities.LostOneEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import software.bernie.example.registry.EntityRegistry;


public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Main.LOST_ONE, LostOneEntityRenderer::new);
    }
}

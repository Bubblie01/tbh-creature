package io.github.tbh_mod;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class ClientInitializer implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityModelLayerRegistry.registerModelLayer(TbhEntityModel.TBH_MODEL_LAYER, TbhEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BtwEntityModel.BTW_MODEL_LAYER, BtwEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(ColaEntityModel.COLA_ENTITY_MODEL_LAYER, ColaEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(TbhEntity.TBH_ENTITY_TYPE, ((context -> {
			return new TbhEntityRenderer(context);
		})));

		EntityRendererRegistry.register(BtwEntity.BTW_ENTITY_TYPE, ((context -> {
			return new BtwEntityRenderer(context);
		})));

		EntityRendererRegistry.register(ColaEntity.COLA_ENTITY_TYPE, ((context -> {
			return new ColaEntityRenderer(context);
		})));
	}
}

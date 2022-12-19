package io.github.tbh_mod;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class ClientInitializer implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityModelLayerRegistry.registerModelLayer(TbhEntityModel.TBH_MODEL_LAYER, TbhEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(TbhEntity.TBH_ENTITY_TYPE, ((context -> {
			return new TbhEntityRenderer(context);
		})));
	}
}
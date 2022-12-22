package io.github.tbh_mod;

import io.github.tbh_mod.entities.BtwEntity;
import io.github.tbh_mod.entities.ColaEntity;
import io.github.tbh_mod.entities.TbhEntity;
import io.github.tbh_mod.entities.models.BtwEntityModel;
import io.github.tbh_mod.entities.models.ColaEntityModel;
import io.github.tbh_mod.entities.models.TbhEntityModel;
import io.github.tbh_mod.entities.renderers.BtwEntityRenderer;
import io.github.tbh_mod.entities.renderers.ColaEntityRenderer;
import io.github.tbh_mod.entities.renderers.TbhEntityRenderer;
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

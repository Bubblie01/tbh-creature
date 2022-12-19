package io.github.tbh_mod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class TbhRegistry {

	public static final ColaBottleItem COLA_BOTTLE_ITEM = new ColaBottleItem(new Item.Settings().group(ItemGroup.MISC));

	public static void registerItems() {
		net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, new Identifier(Main.MOD_ID, "cola_bottle_item"), COLA_BOTTLE_ITEM);
	}
}

package io.github.tbh_mod;

import io.github.tbh_mod.items.ColaBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TbhRegistry {

	public static final ColaBottleItem COLA_BOTTLE_ITEM = new ColaBottleItem(new Item.Settings().group(ItemGroup.MISC));

	public static final Identifier YIPEE_SOUND_ID = new Identifier(Main.MOD_ID, "yipee");

	public static final SoundEvent YIPEE_SOUND_EVENT = new SoundEvent(YIPEE_SOUND_ID);

	public static final Identifier SPAMTON_SOUND_ID = new Identifier(Main.MOD_ID, "spamton");

	public static final SoundEvent SPAMTON_SOUND_EVENT = new SoundEvent(SPAMTON_SOUND_ID);
	public static void registerItems() {
		net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, new Identifier(Main.MOD_ID, "cola_bottle_item"), COLA_BOTTLE_ITEM);
	}

	public static void registerSounds() {
		Registry.register(Registry.SOUND_EVENT, YIPEE_SOUND_ID, YIPEE_SOUND_EVENT);
		Registry.register(Registry.SOUND_EVENT, SPAMTON_SOUND_ID, SPAMTON_SOUND_EVENT);
	}
}

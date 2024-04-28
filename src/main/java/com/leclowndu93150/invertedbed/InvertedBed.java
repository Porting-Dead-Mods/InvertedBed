package com.leclowndu93150.invertedbed;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


@Mod(InvertedBed.MODID)

public class InvertedBed {
    public static final String MODID = "invertedbed";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final DeferredBlock<Block> INVERTED_BED = BLOCKS.register("inverted_bed", () -> new InvertedBedEvents(DyeColor.RED, BlockBehaviour.Properties.of().destroyTime(0.3F).sound(SoundType.WOOD)));

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE,MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InvertedBedBlockEntity>> INVERTED_BED_E = BLOCKENTITIES.register("inverted_bed", () -> BlockEntityType.Builder.of(InvertedBedBlockEntity::new, INVERTED_BED.get()).build(null));



    public static final DeferredItem<BlockItem> INVERTED_BED_ITEM = ITEMS.registerSimpleBlockItem("inverted_bed", INVERTED_BED);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> INVERTED_TAB = CREATIVE_MODE_TABS.register("inverted_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.invertedbed"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> INVERTED_BED_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> output.accept(INVERTED_BED_ITEM.get())).build());


    public InvertedBed(IEventBus modEventBus) {

        BLOCKS.register(modEventBus);

        ITEMS.register(modEventBus);

        CREATIVE_MODE_TABS.register(modEventBus);

        BLOCKENTITIES.register(modEventBus);

    }



}


package com.leclowndu93150.invertedbed;

import com.leclowndu93150.invertedbed.block.InvertedBedBlock;
import com.leclowndu93150.invertedbed.block.InvertedBedBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "invertedbed";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final List<RegistryObject<Block>> INVERTED_BEDS = registerBeds();

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    public static final Supplier<BlockEntityType<InvertedBedBlockEntity>> INVERTED_BED =
            BLOCKENTITIES.register("inverted_bed", () ->
                    BlockEntityType.Builder.of(InvertedBedBlockEntity::new,
                            bedsToArray(INVERTED_BEDS)).build(null));

    public static final RegistryObject<CreativeModeTab> INVERTED_TAB = CREATIVE_MODE_TABS.register("inverted_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.invertedbed"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> INVERTED_BEDS.get(14).get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (RegistryObject<Block> bedBlock : INVERTED_BEDS) {
                    output.accept(bedBlock.get());
                }
            }).build());

    private static List<RegistryObject<Block>> registerBeds() {
        List<RegistryObject<Block>> bedBlocks = new ArrayList<>();
        DyeColor[] values = DyeColor.values();
        for (DyeColor color : values) {
            bedBlocks.add(newBed(color));
        }
        return bedBlocks;
    }

    private static Block[] bedsToArray(List<RegistryObject<Block>> bedBlocks) {
        Block[] blocks = new Block[16];
        int bedSize = bedBlocks.size();
        for (int i = 0; i < bedSize; i++) {
            blocks[i] = bedBlocks.get(i).get();
        }
        return blocks;
    }

    private static RegistryObject<Block> newBed(DyeColor color) {
        RegistryObject<Block> tempBlock = BLOCKS.register(color.getName() + "_inverted_bed",
                () -> new InvertedBedBlock(color, BlockBehaviour.Properties.copy(Blocks.RED_BED)));
        ITEMS.register(color.getName() + "_inverted_bed", () -> new BlockItem(tempBlock.get(), new Item.Properties()));
        return tempBlock;
    }

    public Main() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(eventBus);

        ITEMS.register(eventBus);

        CREATIVE_MODE_TABS.register(eventBus);

        BLOCKENTITIES.register(eventBus);

    }

}


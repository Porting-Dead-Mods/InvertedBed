package com.portingdeadmods.invertedbed;

import com.portingdeadmods.invertedbed.block.InvertedBedBlock;
import com.portingdeadmods.invertedbed.block.InvertedBedBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.world.item.Items.registerBlock;


@Mod(Main.MODID)

public class Main {
    public static final String MODID = "invertedbed";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final List<DeferredBlock<InvertedBedBlock>> INVERTED_BEDS = registerBeds();

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);
    public static final Supplier<BlockEntityType<InvertedBedBlockEntity>> INVERTED_BED =
            BLOCKENTITIES.register("inverted_bed", () ->
                    BlockEntityType.Builder.of(InvertedBedBlockEntity::new,
                            bedsToArray(INVERTED_BEDS)).build(null));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> INVERTED_TAB = CREATIVE_MODE_TABS.register("inverted_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.invertedbed"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> INVERTED_BEDS.get(14).toStack())
            .displayItems((parameters, output) -> {
                for (DeferredBlock<InvertedBedBlock> bedBlock : INVERTED_BEDS) {
                    output.accept(bedBlock);
                }
            }).build());

    private static List<DeferredBlock<InvertedBedBlock>> registerBeds() {
        List<DeferredBlock<InvertedBedBlock>> bedBlocks = new ArrayList<>();
        DyeColor[] values = DyeColor.values();
        for (DyeColor color : values) {
            bedBlocks.add(newBed(color));
        }
        return bedBlocks;
    }

    private static InvertedBedBlock[] bedsToArray(List<DeferredBlock<InvertedBedBlock>> bedBlocks) {
        InvertedBedBlock[] blocks = new InvertedBedBlock[16];
        int bedSize = bedBlocks.size();
        for (int i = 0; i < bedSize; i++) {
            blocks[i] = bedBlocks.get(i).value();
        }
        return blocks;
    }

    private static DeferredBlock<InvertedBedBlock> newBed(DyeColor color) {
        DeferredBlock<InvertedBedBlock> tempBlock = BLOCKS.register(color.getName() + "_inverted_bed",
                () -> new InvertedBedBlock(color, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_BED)));
        ITEMS.registerSimpleBlockItem(color.getName() + "_inverted_bed", tempBlock);
        return tempBlock;
    }

    public Main(IEventBus modEventBus) {

        BLOCKS.register(modEventBus);

        ITEMS.register(modEventBus);

        CREATIVE_MODE_TABS.register(modEventBus);

        BLOCKENTITIES.register(modEventBus);

    }

}


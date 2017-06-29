package de.teamlapen.vampirism.core;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import de.teamlapen.lib.lib.item.ItemMetaBlock;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.blocks.*;
import de.teamlapen.vampirism.tileentity.*;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.datafix.IFixableData;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Handles all block registrations and reference.
 */
@GameRegistry.ObjectHolder(REFERENCE.MODID)
public class ModBlocks {
    public static final BlockFluidBlood block_blood_fluid = getNull();
    public static final BlockCastleBlock castle_block = getNull();
    public static final BlockCursedEarth cursed_earth = getNull();
    public static final VampirismFlower vampirism_flower = getNull();
    public static final BlockTent tent = getNull();
    public static final BlockTentMain tent_main = getNull();
    public static final BlockCoffin block_coffin = getNull();
    public static final BlockAltarInfusion altar_infusion = getNull();
    public static final BlockAltarPillar altar_pillar = getNull();
    public static final BlockAltarTip altar_tip = getNull();
    public static final BlockHunterTable hunter_table = getNull();
    public static final BlockMedChair med_chair = getNull();
    public static final BlockGarlic garlic = getNull();
    public static final BlockChurchAltar church_altar = getNull();
    public static final BlockBloodContainer blood_container = getNull();
    public static final BlockAltarInspiration altar_inspiration = getNull();
    public static final BlockFirePlace fire_place = getNull();
    public static final BlockWeaponTable weapon_table = getNull();
    public static final BlockBloodPotionTable blood_potion_table = getNull();
    public static final BlockSunscreenBeacon sunscreen_beacon = getNull();
    public static final BlockAlchemicalFire alchemical_fire = getNull();
    public static final BlockAlchemicalCauldron alchemical_cauldron = getNull();
    public static final BlockGarlicBeacon garlic_beacon = getNull();
    private static final Map<String, String> OLD_TO_NEW_TILE_MAP = Maps.newHashMap();

    @SuppressWarnings("ConstantConditions")
    private static @Nonnull
    <T> T getNull() {
        return null;
    }

    private static void registerTiles() {
        registerTileEntity(TileTent.class, "tent", "VampirismTent");
        registerTileEntity(TileCoffin.class, "coffin", "VampirismCoffin");
        registerTileEntity(TileAltarInfusion.class, "altar_infusion", "VampirismAltarInfusion");
        registerTileEntity(TileBloodContainer.class, "blood_container", "VampirismBloodContainer");
        registerTileEntity(TileAltarInspiration.class, "altar_inspiration", "VampirismAltarInspiration");
        registerTileEntity(TileSunscreenBeacon.class, "sunscreen_beacon", "VampirismSunscreenBeacon");
        registerTileEntity(TileAlchemicalCauldron.class, "alchemical_cauldron", "VampirismAlchemicalCauldron");
        registerTileEntity(TileGarlicBeacon.class, "garlic_beacon", "VampirismGarlicBeacon");
    }

    /**
     * Register the given tile entity and add pre 1.11 name to DATA FIXER
     *
     * @param clazz Tile class
     * @param id    Tile id. Is converted to resource location  MODID:<id>
     */
    private static void registerTileEntity(Class<? extends TileEntity> clazz, String id, String old) {
        registerTileEntity(clazz, id);
        OLD_TO_NEW_TILE_MAP.put(old, REFERENCE.MODID + ":" + id);
    }

    public static IFixableData getTileEntityIDFixer() {
        return new IFixableData() {
            @Override
            public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
                String s = OLD_TO_NEW_TILE_MAP.get(compound.getString("id"));

                if (s != null) {
                    compound.setString("id", s);
                }
                return compound;
            }

            @Override
            public int getFixVersion() {
                return 1;
            }
        };
    }

    /**
     * Register the given tile entity
     *
     * @param clazz Tile class
     * @param id    Tile id. Is converted to resource location  MODID:<id>
     */
    private static void registerTileEntity(Class<? extends TileEntity> clazz, String id) {
        GameRegistry.registerTileEntity(clazz, REFERENCE.MODID + ":" + id);
    }

    static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.register(new ItemMetaBlock(castle_block));
        Item itemBloodContainer = new ItemBlock(blood_container);
        itemBloodContainer.setRegistryName(blood_container.getRegistryName());
        itemBloodContainer.setMaxStackSize(1);
        registry.register(itemBloodContainer);
        registry.register(itemBlock(block_blood_fluid));
        registry.register(itemBlock(cursed_earth));
        registry.register(new ItemMetaBlock(vampirism_flower));
        registry.register(itemBlock(altar_infusion));
        registry.register(itemBlock(altar_pillar));
        registry.register(itemBlock(altar_tip));
        registry.register(itemBlock(hunter_table));
        registry.register(itemBlock(church_altar));
        registry.register(itemBlock(altar_inspiration));
        registry.register(itemBlock(fire_place));
        registry.register(itemBlock(weapon_table));
        registry.register(itemBlock(blood_potion_table));
        registry.register(itemBlock(sunscreen_beacon));
        registry.register(itemBlock(alchemical_cauldron));
        registry.register(itemBlock(garlic_beacon));
    }

    private static @Nonnull
    ItemBlock itemBlock(@Nonnull Block b) {
        ItemBlock item = new ItemBlock(b);
        //noinspection ConstantConditions
        item.setRegistryName(b.getRegistryName());
        return item;
    }

    static void registerBlocks(IForgeRegistry<Block> registry) {

        registry.register(new BlockFluidBlood());//TODO maybe remove blood block later
        registry.register(new BlockCastleBlock());
        registry.register(new VampirismFlower());
        registry.register(new BlockCursedEarth());
        registry.register(new BlockTent());
        registry.register(new BlockTentMain());
        registry.register(new BlockCoffin());
        registry.register(new BlockAltarInfusion());
        registry.register(new BlockAltarPillar());
        registry.register(new BlockAltarTip());
        registry.register(new BlockHunterTable());
        registry.register(new BlockMedChair());
        registry.register(new BlockGarlic());
        registry.register(new BlockChurchAltar());
        registry.register(new BlockBloodContainer());
        registry.register(new BlockAltarInspiration());
        registry.register(new BlockFirePlace());
        registry.register(new BlockWeaponTable());
        registry.register(new BlockBloodPotionTable());
        registry.register(new BlockSunscreenBeacon());
        registry.register(new BlockAlchemicalFire());
        registry.register(new BlockAlchemicalCauldron());
        registry.register(new BlockGarlicBeacon());

        registerTiles();
    }

    static void registerCraftingRecipes() {
        //TODO CRAFTING
//        GameRegistry.addRecipe(new ItemStack(altarInfusion, 1), "   ", "YZY", "ZZZ", 'Y', Items.GOLD_INGOT, 'Z', Blocks.OBSIDIAN);
//        GameRegistry.addRecipe(new ItemStack(altarPillar, 4), "X X", "   ", "XXX", 'X', Blocks.STONEBRICK);
//        GameRegistry.addRecipe(new ItemStack(altarTip, 2), "   ", " X ", "XYX", 'X', Items.IRON_INGOT, 'Y', Blocks.IRON_BLOCK);
//        GameRegistry.addShapelessRecipe(new ItemStack(castleBlock, 8, 3), Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, new ItemStack(vampirismFlower, 1, VampirismFlower.EnumFlowerType.ORCHID.getMeta()));
//        GameRegistry.addShapelessRecipe(new ItemStack(castleBlock, 8, 0), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(Items.DYE, 1, 0));
//        GameRegistry.addShapelessRecipe(new ItemStack(castleBlock, 8, 1), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(castleBlock, 1, 3), new ItemStack(vampirismFlower, 1, VampirismFlower.EnumFlowerType.ORCHID.getMeta()));
//        GameRegistry.addShapelessRecipe(new ItemStack(castleBlock, 7, 0), Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, Blocks.STONEBRICK, new ItemStack(Items.DYE, 1, 0), new ItemStack(vampirismFlower, 1, VampirismFlower.EnumFlowerType.ORCHID.getMeta()));
//        GameRegistry.addShapelessRecipe(new ItemStack(castleBlock, 7, 4), Blocks.STONE, Blocks.STONE, Blocks.STONE, Blocks.STONE, Blocks.STONE, Blocks.STONE, Blocks.STONE, new ItemStack(Items.DYE, 1, 0), new ItemStack(vampirismFlower, 1, VampirismFlower.EnumFlowerType.ORCHID.getMeta()));
//        GameRegistry.addRecipe(new ItemStack(hunterTable), "XYW", "ZZZ", "Z Z", 'X', ModItems.vampireFang, 'Y', Items.BOOK, 'Z', Blocks.PLANKS, 'W', ModItems.itemGarlic);
//        GameRegistry.addRecipe(new ItemStack(bloodContainer), "XYX", "YZY", "XYX", 'X', Blocks.PLANKS, 'Y', Blocks.GLASS, 'Z', Items.IRON_INGOT);
//        GameRegistry.addRecipe(new ItemStack(altarInspiration, 1), " X ", "XYX", "ZZZ", 'X', Blocks.GLASS, 'Y', Items.GLASS_BOTTLE, 'Z', Items.IRON_INGOT);
//        GameRegistry.addRecipe(new ItemStack(firePlace, 1), "   ", " X ", "XYX", 'X', Blocks.LOG, 'Y', Items.COAL);
//        GameRegistry.addRecipe(new ItemStack(weaponTable, 1), "X  ", "YYY", " Z ", 'X', Items.BUCKET, 'Y', Items.IRON_INGOT, 'Z', Blocks.IRON_BLOCK);
//        GameRegistry.addRecipe(new ItemStack(bloodPotionTable, 1), "XXX", "Y Y", "ZZZ", 'X', Items.GLASS_BOTTLE, 'Y', Blocks.PLANKS, 'Z', Items.IRON_INGOT);
//        GameRegistry.addRecipe(new ItemStack(churchAltar), " X ", "YYY", " Y ", 'X', ModItems.vampireBook, 'Y', Blocks.PLANKS);
//        GameRegistry.addRecipe(new ItemStack(alchemicalCauldron), "XZX", "XXX", "Y Y", 'X', Items.IRON_INGOT, 'Y', Blocks.STONEBRICK, 'Z', ModItems.itemGarlic);
//        GameRegistry.addRecipe(new ItemStack(garlicBeacon), "XYX", "YZY", "OOO", 'X', Blocks.PLANKS, 'Y', Items.DIAMOND, 'Z', ModItems.garlicBeaconCore, 'O', Blocks.OBSIDIAN);
//        GameRegistry.addRecipe(new ItemStack(garlicBeacon, 1, 1), "XYX", "YZY", "OOO", 'X', Blocks.PLANKS, 'Y', Items.DIAMOND, 'Z', ModItems.garlicBeaconCoreImproved, 'O', Blocks.OBSIDIAN);

    }


    /**
     * Fix block mappings
     *
     * @return if it was fixed
     */
    static boolean fixMapping(RegistryEvent.MissingMappings.Mapping<Block> mapping) {
        //Check for mappings changed for 1.11 CamelCase to lower underscore
        return checkMapping(mapping, mapping.key.getResourcePath(), false, alchemical_cauldron, alchemical_fire, altar_infusion, altar_inspiration, altar_pillar, altar_tip, blood_container, blood_potion_table, castle_block, church_altar, block_coffin, cursed_earth, fire_place, block_blood_fluid, garlic_beacon, hunter_table, med_chair, sunscreen_beacon, tent_main, vampirism_flower, weapon_table);
    }

    private static boolean checkMapping(RegistryEvent.MissingMappings.Mapping mapping, String name, boolean itemBlock, Block... blocks) {
        for (Block b : blocks) {
            String newRegisteredName = b instanceof VampirismBlock ? ((VampirismBlock) b).getRegisteredName() : (b instanceof VampirismBlockContainer ? ((VampirismBlockContainer) b).getRegisteredName() : (b instanceof VampirismFlower ? ((VampirismFlower) b).getRegisteredName() : (b instanceof BlockFluidBlood ? ((BlockFluidBlood) b).getRegisteredName() : null)));
            if (newRegisteredName == null) {
                VampirismMod.log.w("ModBlocks", "Unknown block class %s. Unable to determine new registered name during mapping fix", b.getClass());
                continue;
            }
            String oldRegisteredName = newRegisteredName.replaceAll("_", "");

            if (oldRegisteredName.equals(name)){
                if (itemBlock) {
                    mapping.remap(Item.getItemFromBlock(b));
                } else {
                    mapping.remap(b);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Fix item block mappings
     *
     * @return if it was fixed
     */
    static boolean fixMappingItemBlock(RegistryEvent.MissingMappings.Mapping<Item> mapping) {
        //Check for mappings changed for 1.11 CamelCase to lower underscore
        String converted = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, mapping.key.getResourcePath());
        return checkMapping(mapping, converted, true, alchemical_cauldron, altar_infusion, altar_inspiration, altar_pillar, altar_tip, blood_container, blood_potion_table, castle_block, church_altar, cursed_earth, fire_place, block_blood_fluid, garlic_beacon, hunter_table, sunscreen_beacon, vampirism_flower, weapon_table);
    }


}

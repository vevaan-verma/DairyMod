package net.cheesestudios.dairymod.util;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {

    public static final WoodType CHEESE = WoodType.register(new WoodType(DairyMod.MOD_ID + ":cheese", BlockSetType.OAK));

}

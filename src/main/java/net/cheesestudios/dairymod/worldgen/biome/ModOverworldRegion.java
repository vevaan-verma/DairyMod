package net.cheesestudios.dairymod.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {

    public ModOverworldRegion(ResourceLocation name, int weight) {

        super(name, RegionType.OVERWORLD, weight);

    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {

        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {

            // modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FOREST, ModBiomes.CHEESE_BIOME); // replace biome with your biome (easier/not preferred way)

            // more complex example:
            VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

            // overlap vanilla's parameters with our own for our CHEESE_BIOME
            new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.ICY, ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.NEUTRAL)
                    .humidity(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY, ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Humidity.WET, ParameterUtils.Humidity.HUMID)
                    .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.COAST, ParameterUtils.Continentalness.FAR_INLAND),
                            ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.MID_INLAND, ParameterUtils.Continentalness.FAR_INLAND))
                    .erosion(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING)
                    .build().forEach(point -> builder.add(point, ModBiomes.CHEESE_BIOME));

            builder.build().forEach(mapper::accept);

        });
    }
}

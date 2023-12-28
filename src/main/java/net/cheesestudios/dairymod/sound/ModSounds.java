package net.cheesestudios.dairymod.sound;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DairyMod.MOD_ID);

    /*
    IMPORTANT:
        - sounds must be .ogg files (no renaming file extension, you must convert it properly)
        - make sure the sounds are mono, if they are in stereo, the whole world will hear the sound
        - sound file does not necessarily need to share the same name as the sound event
     */

    // if multiple sounds are added under one sound event in sounds.json, a random one will be chosen

    /*
    SOUND TYPES:
        - break
        - step
        - place
        - hit
        - fall
     */

    public static final RegistryObject<SoundEvent> SOUND_BLOCK_BREAK = registerSoundEvents("sound_block_break");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_STEP = registerSoundEvents("sound_block_step");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_PLACE = registerSoundEvents("sound_block_place");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_HIT = registerSoundEvents("sound_block_hit");
    public static final RegistryObject<SoundEvent> SOUND_BLOCK_FALL = registerSoundEvents("sound_block_fall");

    public static final ForgeSoundType SOUND_BLOCK_SOUNDS = new ForgeSoundType(1f, 1f,
            ModSounds.SOUND_BLOCK_BREAK, ModSounds.SOUND_BLOCK_STEP, ModSounds.SOUND_BLOCK_PLACE,
            ModSounds.SOUND_BLOCK_HIT, ModSounds.SOUND_BLOCK_FALL);

    public static final RegistryObject<SoundEvent> CHEESE_DETECTOR_FOUND_CHEESE = registerSoundEvents("cheese_detector_found_cheese");

    public static final RegistryObject<SoundEvent> AFTER_HOURS = registerSoundEvents("after_hours");
    public static final RegistryObject<SoundEvent> GREEDY = registerSoundEvents("greedy");
    public static final RegistryObject<SoundEvent> HOUDINI_EXTENDED = registerSoundEvents("houdini_extended");
    public static final RegistryObject<SoundEvent> IN_THE_NIGHT = registerSoundEvents("in_the_night");
    public static final RegistryObject<SoundEvent> POPULAR = registerSoundEvents("popular");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {

        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DairyMod.MOD_ID, name)));

    }

    public static void register(IEventBus eventBus) {

        SOUND_EVENTS.register(eventBus);

    }
}

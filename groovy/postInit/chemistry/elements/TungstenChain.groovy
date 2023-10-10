import static globals.Globals.*

MIXER = recipemap('mixer')
FF = recipemap('froth_flotation')
CLARIFIER = recipemap('clarifier')
CENTRIFUGE = recipemap('centrifuge')
BR = recipemap('batch_reactor')
CSTR = recipemap('continuous_stirred_tank_reactor')
DISTILLERY = recipemap('distillery')
GRAVITY_SEPARATOR = recipemap('gravity_separator')
ROASTER = recipemap('roaster')
LCR = recipemap('large_chemical_reactor')
AUTOCLAVE = recipemap('autoclave')
CRYSTALLIZER = recipemap('crystallizer')
REACTION_FURNACE = recipemap('reaction_furnace')

// Tungstic Acid Dust * 7
mods.gregtech.chemical_bath.removeByInput(960, [metaitem('dustScheelite') * 6], [fluid('hydrochloric_acid') * 2000])
// Tungstic Acid Dust * 7
mods.gregtech.chemical_bath.removeByInput(960, [metaitem('dustTungstate') * 7], [fluid('hydrochloric_acid') * 2000])

// Benefication

// Scheelite
GRAVITY_SEPARATOR.recipeBuilder()
        .inputs(ore('dustScheelite'))
        .outputs(metaitem('dustSiftedScheelite'))
        .chancedOutput(metaitem('dustGraniteTailings'), 5000, 0)
        .EUt(Globals.voltAmps[1])
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(ore('dustSiftedScheelite') * 8)
        .fluidInputs(fluid('distilled_water') * 2000)
        .fluidOutputs(fluid('impure_scheelite_slurry') * 2000)
        .EUt(Globals.voltAmps[3])
        .duration(80)
        .buildAndRegister()

FF.recipeBuilder()
        .fluidInputs(fluid('impure_scheelite_slurry') * 2000)
        .notConsumable(fluid('oleic_acid') * 144)
        .notConsumable(ore('dustSodiumSilicate') * 6)
        .fluidOutputs(fluid('scheelite_slurry') * 1000)
        .fluidOutputs(fluid('granite_tailing_slurry') * 1000)
        .EUt(Globals.voltAmps[3])
        .duration(80)
        .buildAndRegister()

CLARIFIER.recipeBuilder()
        .fluidInputs(fluid('scheelite_slurry') * 1000)
        .outputs(metaitem('dustFlotatedScheelite') * 16)
        .fluidOutputs(fluid('wastewater') * 1000)
        .duration(20)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustFlotatedScheelite'))
        .fluidInputs(fluid('hydrochloric_acid') * 50)
        .outputs(metaitem('dustCalcinedScheelite'))
        .duration(20)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

AUTOCLAVE.recipeBuilder()
        .inputs(ore('dustCalcinedScheelite'))
        .fluidInputs(fluid('soda_ash_solution') * 1000)
        .fluidInputs(fluid('distilled_water') * 1000)
        .outputs(metaitem('dustCalcite') * 5)
        .fluidOutputs(fluid('sodium_tungstate_solution') * 1000)
        .duration(20)
        .EUt(Globals.voltAmps[3])
        .buildAndRegister()

// Wolframite

GRAVITY_SEPARATOR.recipeBuilder()
        .inputs(ore('dustWolframite'))
        .outputs(metaitem('dustSiftedWolframite'))
        .chancedOutput(metaitem('dustGraniteTailings'), 5000, 0)
        .chancedOutput(metaitem('dustGoldConcentrate'), 500, 100)
        .EUt(Globals.voltAmps[1])
        .duration(20)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(ore('dustSiftedWolframite') * 8)
        .fluidInputs(fluid('distilled_water') * 2000)
        .fluidOutputs(fluid('impure_wolframite_slurry') * 2000)
        .EUt(Globals.voltAmps[3])
        .duration(80)
        .buildAndRegister()

FF.recipeBuilder()
        .fluidInputs(fluid('impure_wolframite_slurry') * 2000)
        .notConsumable(fluid('oleic_acid') * 144)
        .notConsumable(ore('dustSodiumSilicate') * 6)
        .fluidOutputs(fluid('wolframite_slurry') * 1000)
        .fluidOutputs(fluid('granite_tailing_slurry') * 1000)
        .EUt(Globals.voltAmps[3])
        .duration(80)
        .buildAndRegister()

CLARIFIER.recipeBuilder()
        .fluidInputs(fluid('wolframite_slurry') * 1000)
        .outputs(metaitem('dustFlotatedWolframite') * 16)
        .fluidOutputs(fluid('wastewater') * 1000)
        .duration(20)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

AUTOCLAVE.recipeBuilder()
        .inputs(ore('dustFlotatedWolframite'))
        .fluidInputs(fluid('sodium_hydroxide_solution') * 2000)
        .fluidInputs(fluid('distilled_water') * 1000)
        .chancedOutput(metaitem('dustManganeseIiHydroxide') * 5, 5000, 0)
        .chancedOutput(metaitem('dustIronIiHydroxide') * 5, 5000, 0)
        .fluidOutputs(fluid('sodium_tungstate_solution') * 1000) // 2x H2O, 8/9x Na2WO4, 1/18x Na2MoO4 1/18x Na2SiO3
        .duration(20)
        .EUt(Globals.voltAmps[3])
        .buildAndRegister()

// Purification

BR.recipeBuilder()
        .inputs(ore('dustAluminiumSulfate') * 17)
        .fluidInputs(fluid('sodium_tungstate_solution') * 18000)
        .outputs(metaitem('dustAluminiumSilicate') * 8)
        .fluidOutputs(fluid('silicate_free_tungstate_solution') * 18000) // 18x H2O, 8x Na2WO4, 1/2x Na2MoO4, 1/2x Na2SO4
        .EUt(Globals.voltAmps[3])
        .duration(90)
        .buildAndRegister()

/*
Conversion to thiomolybdate: 9x H2O, 4x Na2WO4, 1/4x Na2MoS4, 1/4x Na2SO4, 2x NaOH
Precipitation of MoS3: 45/4x H2O, 4x Na2WO4, 5/4x Na2SO4
*/

LCR.recipeBuilder()
        .inputs(ore('dustSodiumSulfide') * 6)
        .fluidInputs(fluid('silicate_free_tungstate_solution') * 9000)
        .fluidInputs(fluid('sulfuric_acid') * 3000)
        .fluidInputs(fluid('distilled_water') * 2000)
        .chancedOutput(metaitem('dustMolybdenumTrisulfide') * 4, 5000, 0)
        .fluidOutputs(fluid('molybdenum_free_tungstate_solution') * 11250) 
        .fluidOutputs(fluid('hydrogen_sulfide') * 500)
        .EUt(Globals.voltAmps[3])
        .duration(180)
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('kerosene') * 325)
        .fluidInputs(fluid('trioctylamine') * 625)
        .fluidInputs(fluid('isodecanol') * 50)
        .fluidOutputs(fluid('tungsten_extraction_mixture') * 1000)
        .EUt(Globals.voltAmps[4])
        .duration(200)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('ammonium_tungstate_solution') * 4000)
        .fluidInputs(fluid('tungsten_extraction_mixture') * 8000) 
        .fluidOutputs(fluid('ammonia_solution') * 4000)
        .fluidOutputs(fluid('tungsten_extract') * 8000)
        .EUt(Globals.voltAmps[3])
        .duration(20)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('molybdenum_free_tungstate_solution') * 11250) // 45/4x H2O, 4x Na2WO4, 5/4x Na2SO4
        .fluidInputs(fluid('tungsten_extraction_mixture') * 8000) 
        .fluidOutputs(fluid('very_diluted_sodium_sulfate_solution') * 11250) // 45/4x H2O, 5/4x Na2SO4 (9:1)
        .fluidOutputs(fluid('tungsten_extract') * 8000) // Extracts 6 moles of tungsten as trioctylammonium isopolytungstate (Oc3NH)5(HW6O21)
        .EUt(Globals.voltAmps[3])
        .duration(20)
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('very_diluted_sodium_sulfate_solution') * 9000)
        .outputs(metaitem('dustSodiumSulfate') * 7)
        .fluidOutputs(fluid('water') * 9000)
        .EUt(Globals.voltAmps[1])
        .duration(20)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('tungsten_extract') * 8000)
        .fluidInputs(fluid('demineralized_water') * 2000) 
        .fluidOutputs(fluid('scrubbed_tungsten_extract') * 8000)
        .fluidOutputs(fluid('wastewater') * 2000)
        .EUt(Globals.voltAmps[3])
        .duration(20)
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('scrubbed_tungsten_extract') * 8000) 
        .fluidInputs(fluid('ammonia_solution') * 5000) 
        .fluidOutputs(fluid('tungsten_extraction_mixture') * 8000)
        .fluidOutputs(fluid('ammonium_isopolytungstate_solution') * 5000)
        .EUt(Globals.voltAmps[3])
        .duration(20)
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('ammonium_isopolytungstate_solution') * 5000)
        .chancedOutput(metaitem('dustAmmoniumParatungstate'), 5000, 0)
        .fluidOutputs(fluid('water') * 3000)
        .EUt(Globals.voltAmps[3])
        .duration(20)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustAmmoniumParatungstate'))
        .fluidInputs(fluid('air') * 1000)
        .outputs(metaitem('dustTungstenTrioxide'))
        .fluidOutputs(fluid('steam') * 6000)
        .fluidOutputs(fluid('ammonia') * 10000)
        .EUt(Globals.voltAmps[3])
        .duration(20)
        .buildAndRegister()

// REDUCTION TO PURE METAL

REACTION_FURNACE.recipeBuilder()
        .fluidInputs(fluid('hydrogen') * 6000)
        .inputs(ore('dustTungstenTrioxide') * 4)
        .fluidOutputs(fluid('steam') * 3000)
        .outputs(ore('dustTungsten').first())
        .duration(100)
        .EUt(Globals.voltAmps[4])
        .buildAndRegister()
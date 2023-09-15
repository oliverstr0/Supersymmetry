import static globals.Globals.*

BR = recipemap('batch_reactor')
CSTR = recipemap('continuous_stirred_tank_reactor')
DISTILLERY = recipemap('distillery')
DISTILLATION_TOWER = recipemap('distillation_tower')
ROASTER = recipemap('roaster')
MACERATOR = recipemap('macerator')
SIFTER = recipemap('sifter')
CRYSTALLIZER = recipemap('crystallizer')

// Platinum Dust * 1
mods.gregtech.centrifuge.removeByInput(30, [metaitem('dustAlluvialPlatinum')], null)

//LOW YIELD CHAIN FROM SECONDARY ORES
MACERATOR.recipeBuilder()
    .inputs(item('susy:resource_block', 10))
    .outputs(metaitem('dustAlluvialPlatinum') * 9)
    .duration(160)
    .EUt(30)
    .buildAndRegister()

SIFTER.recipeBuilder()
    .inputs(ore('dustAlluvialPlatinum'))
    .outputs(metaitem('nuggetFerroplatinum') * 4)
    .outputs(metaitem('dustNetherQuartz') * 3)
    .duration(160)
    .EUt(30)
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustFerroplatinum') * 4)
    .fluidInputs(fluid('aqua_regia') * 8000)
    .fluidInputs(fluid('hydrogen_chloride') * 8000)
    .fluidOutputs(fluid('hexachloroplatinic_acid_solution') * 8000)
    .fluidOutputs(fluid('hydrogen') * 2000)
    .fluidOutputs(fluid('nitric_oxide') * 4000)
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

BR.recipeBuilder()
    .inputs(ore('dustZinc'))
    .fluidInputs(fluid('hexachloroplatinic_acid_solution') * 8000)
    .fluidOutputs(fluid('cemented_hexachloroplatinic_acid_solution') * 8000)
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
    .circuitMeta(1)
    .fluidInputs(fluid('cemented_hexachloroplatinic_acid_solution') * 8000)
    .fluidInputs(fluid('ammonium_chloride_solution') * 8000)
    .chancedOutput(metaitem('dustAmmoniumHexachloroplatinate') * 51, 7500, 0)
    .fluidOutputs(fluid('platinum_mother_liquor') * 8000)
    .fluidOutputs(fluid('platinum_mother_liquor') * 8000) //temp, waiting on susycore #110
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
    .fluidInputs(fluid('platinum_mother_liquor') * 8000)
    .outputs(metaitem('dustZincChloride') * 3)
    .fluidOutputs(fluid('ammonium_chloride_solution') * 1000)
    .fluidOutputs(fluid('water') * 7000)
    .fluidOutputs(fluid('hydrogen_chloride') * 6000)
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

ROASTER.recipeBuilder()
    .inputs(ore('dustAmmoniumHexachloroplatinate') * 17)
    .fluidInputs(fluid('hydrogen') * 4000)
    .outputs(metaitem('sponge.platinum'))
    .fluidOutputs(fluid('ammonia') * 2000)
    .fluidOutputs(fluid('hydrogen_chloride') * 6000)
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

MACERATOR.recipeBuilder()
    .inputs(metaitem('sponge.platinum'))
    .outputs(metaitem('dustPlatinum'))
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

BR.recipeBuilder()
    .notConsumable(ore('springCupronickel'))
    .fluidInputs(fluid('platinum_mother_liquor') * 8000)
    .fluidOutputs(fluid('divalent_palladium_solution') * 8000)
    .fluidOutputs(fluid('chlorine') * 250)
    .duration(120)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
    .fluidInputs(fluid('ammonia_solution') * 2000)
    .fluidInputs(fluid('hydrogen_chloride') * 2000)
    .fluidInputs(fluid('divalent_palladium_solution') * 8000)
    .chancedOutput(metaitem('dustDiamminedichloropalladium') * 11, 1250, 0)
    .fluidOutputs(fluid('palladium_mother_liquor') * 12000)
    .duration(120)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

ROASTER.recipeBuilder()
    .inputs(ore('dustDiamminedichloropalladium') * 11)
    .fluidInputs(fluid('hydrogen') * 4000)
    .outputs(metaitem('dustPalladium'))
    .fluidOutputs(fluid('ammonia') * 2000)
    .fluidOutputs(fluid('hydrogen_chloride') * 2000)
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
    .fluidInputs(fluid('palladium_mother_liquor') * 12000)
    .outputs(metaitem('dustZincChloride') * 3)
    .fluidOutputs(fluid('ammonium_chloride_solution') * 1000)
    .fluidOutputs(fluid('water') * 11000)
    .fluidOutputs(fluid('ammonia') * 2000)
    .fluidOutputs(fluid('hydrogen_chloride') * 8000)
    .duration(240)
    .EUt(Globals.voltAmps[2])
    .buildAndRegister()

//MODERN SEPARATION PROCESSES
    
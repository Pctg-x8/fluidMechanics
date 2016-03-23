package com.cterm2.mcfm1710.interops.smartcursor

import com.asaskevich.smartcursor.api.{ModuleConnector, IBlockProcessor}
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event._

// Module Connector
@Mod(modid = SCModuleConnector.ID, name = SCModuleConnector.Name, version = SCModuleConnector.Version, modLanguage = "scala")
object SCModuleConnector
{
	final val ID = "mcfm1710#SmartCursorModuleConnector"
	final val Name = "SmartCursor Module Connector[Fluid Mechanics]"
	final val Version = "1.0-alpha"

	@Mod.EventHandler
	def init(e: FMLInitializationEvent) =
	{
		System.out.println("mcfm1710.ModInterop: Applying Interoperation with SmartCursor...")
		ModuleConnector.connectModule(new BlockInformationProvider)
	}
}

// Fluid Mechanics block information provider module
class BlockInformationProvider extends IBlockProcessor
{
	import net.minecraft.world.World, net.minecraft.block.Block

	// Module Informations //
	override val getModuleName = "Fluid Mechanics"
	override val getAuthor = "S.Percentage"

	import com.cterm2.mcfm1710.EnergyInjector
	override def process(list: java.util.List[String], block: Block, meta: Int, world: World, x: Int, y: Int, z: Int) = block match
	{
		case EnergyInjector.BlockModuled => world.getTileEntity(x, y, z).asInstanceOf[EnergyInjector.TEModuled].provideInformation(list)
		case EnergyInjector.BlockStandalone => world.getTileEntity(x, y, z).asInstanceOf[EnergyInjector.TEStandalone].provideInformation(list)
		case _ => ()
	}
}

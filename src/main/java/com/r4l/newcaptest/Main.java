package com.r4l.newcaptest;

import com.r4l.newcaptest.network.NetworkHandler;
import com.r4l.newcaptest.proxy.CommonProxy;
import com.r4l.newcaptest.reference.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT_VERSION)
public class Main {

	
	@Instance
	public static Main inctance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;

	
	//Event Handlers
	@EventHandler
	public static void preInit (FMLPreInitializationEvent event) {
		
		proxy.subscribeHandler();
	}
	
	@EventHandler
	public static void init (FMLInitializationEvent event) {
		NetworkHandler.init();
	}
	
	@EventHandler
	public static void postInit (FMLPostInitializationEvent event) {
		
	}
}

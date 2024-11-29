package com.r4l.newcaptest.proxy;

import com.r4l.newcaptest.capability.CapabilitySync;
import com.r4l.newcaptest.capability.folder.Folder;
import com.r4l.newcaptest.capability.folder.FolderHandler;
import com.r4l.newcaptest.capability.folder.FolderStorage;
import com.r4l.newcaptest.capability.folder.IFolder;
import com.r4l.newcaptest.reference.Keybinds;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class CommonProxy {
	
	@SuppressWarnings("deprecation")
	public void subscribeHandler() {
		ClientRegistry.registerKeyBinding(Keybinds.KEY_u);
		ClientRegistry.registerKeyBinding(Keybinds.KEY_i);
		ClientRegistry.registerKeyBinding(Keybinds.KEY_o);
		
		CapabilityManager.INSTANCE.register(IFolder.class, new FolderStorage(), Folder.class);
		
		MinecraftForge.EVENT_BUS.register(new CapabilitySync());
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new FolderHandler());
	}
	
}

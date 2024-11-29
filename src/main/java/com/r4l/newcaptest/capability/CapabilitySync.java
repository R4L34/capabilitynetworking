package com.r4l.newcaptest.capability;


import com.r4l.newcaptest.capability.folder.FolderProvider;
import com.r4l.newcaptest.capability.folder.IFolder;
import com.r4l.newcaptest.network.NetworkHandler;
import com.r4l.newcaptest.network.messages.ServerToClient;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class CapabilitySync {
	
	 
	 @SubscribeEvent
	 public void EntityJoinWorldEvent(EntityJoinWorldEvent event) {
		 	Entity entity = event.getEntity();
	        if (entity.world.isRemote || !(entity instanceof EntityPlayerMP)) return;
	        EntityPlayer player = (EntityPlayer) entity;

	        NetworkHandler.channel.sendTo(new ServerToClient(player), (EntityPlayerMP) player);

	}
	 
	 
	 @SubscribeEvent
	    public void onPlayerClone(PlayerEvent.Clone event)
	    {
		 EntityPlayer player = event.getEntityPlayer();
	        
	        IFolder folder = player.getCapability(FolderProvider.FOLDER_CAP, null);
	        IFolder oldfolder = event.getOriginal().getCapability(FolderProvider.FOLDER_CAP, null);

	        folder.setFolders(oldfolder.getFolders()); 
	       
	    }
}

package com.r4l.newcaptest.capability.folder;

import com.r4l.newcaptest.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FolderHandler {
	public static final ResourceLocation FOLDER_CAP = new ResourceLocation(Reference.MODID, "folders");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;

        event.addCapability(FOLDER_CAP, new FolderProvider());
    }
}

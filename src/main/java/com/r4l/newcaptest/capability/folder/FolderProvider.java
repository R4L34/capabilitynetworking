package com.r4l.newcaptest.capability.folder;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class FolderProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IFolder.class)
    public static final Capability<IFolder> FOLDER_CAP = null;

    private IFolder instance = FOLDER_CAP.getDefaultInstance();
	
	
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == FOLDER_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == FOLDER_CAP ? FOLDER_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return FOLDER_CAP.getStorage().writeNBT(FOLDER_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		FOLDER_CAP.getStorage().readNBT(FOLDER_CAP, this.instance, null, nbt);
		
	}
}

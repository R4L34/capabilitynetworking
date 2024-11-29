package com.r4l.newcaptest.capability.folder;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class FolderStorage implements IStorage<IFolder> {
	@Override
	public NBTBase writeNBT(Capability<IFolder> capability, IFolder instance, EnumFacing side) {
		List<String> folders = instance.getFolders();
		NBTTagList nbt_folders = new NBTTagList();
		if (folders.size() == 0) {return nbt_folders;}
		for (int i = 0; i < folders.size(); i++) {
			nbt_folders.appendTag(new NBTTagString(folders.get(i)));
		}
		return nbt_folders;
	}

	@Override
	public void readNBT(Capability<IFolder> capability, IFolder instance, EnumFacing side, NBTBase nbt) {	
		NBTTagList nbt_folders = (NBTTagList) nbt;
		List<String> folders = new ArrayList<>();
		for (int i = 0; i < nbt_folders.tagCount(); i++) {
			String folder = ((NBTTagString)nbt_folders.get(i)).getString();
			folders.add(folder);
		}
		
		instance.setFolders(folders);
		
	}
}

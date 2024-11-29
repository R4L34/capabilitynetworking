package com.r4l.newcaptest.capability.folder;

import java.util.ArrayList;
import java.util.List;

public class Folder implements IFolder{
private List<String> folders = new ArrayList<>();
	
	@Override
	public void setFolders(List<String> folders) {
		this.folders = folders;
		
	}

	@Override
	public List<String> getFolders() {
		return this.folders;
	}

	@Override
	public void add(String folder) {
		this.folders.add(folder);
		
	}

	@Override
	public void remove(int index) {
		this.folders.remove(index);	
	}

	@Override
	public int size() {
		return this.folders.size();
	}

	@Override
	public String get(int index) {
		return this.folders.get(index);
	}

	@Override
	public void set(String folder, int index) {
		this.folders.set(index, folder);
		
	}

	@Override
	public void swap(int index, int index1) {
		String tmp = this.folders.get(index);
		this.folders.set(index, this.folders.get(index1));
		this.folders.set(index1, tmp);
	}
}

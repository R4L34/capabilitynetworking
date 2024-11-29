package com.r4l.newcaptest.capability.folder;

import java.util.List;

public interface IFolder {

	public void setFolders(List<String> folders);
	
	public List<String> getFolders();
	
	public String get(int index);
	
	public void set(String folder, int index);
	
	public void add(String folder);
	
	public void remove(int index);
    
    public int size();
    
    public void swap(int folder_id_1, int folder_id_2);
	
	
}

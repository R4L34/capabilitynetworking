package com.r4l.newcaptest.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.r4l.newcaptest.capability.folder.FolderProvider;
import com.r4l.newcaptest.capability.folder.IFolder;
import com.r4l.newcaptest.network.NetworkHandler;
import com.r4l.newcaptest.network.messages.ClientToServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class DefaultGUI extends GuiScreen {
	
	private IFolder folder;
	private GuiButton btnAction;
	
	
	public DefaultGUI (IFolder folder) {
		this.folder = folder;
	}
	
	@Override
	public void initGui () {
		super.initGui();
		Keyboard.enableRepeatEvents(true);
		
		btnAction = new GuiButton(1, 30, 30, 95, 20, "ADD");;
		buttonList.add(btnAction);
	}
	
	
	
	
	public void updateStringList() {
		int y = 0;
		for(int i = 0; i < folder.size(); i++) {
				drawCenteredString(fontRenderer, i + ") " + folder.get(i), width / 2, 30 + y, 0xFFFFFF);
				y += 12;
		}
	}
	
	
	@Override
	protected void actionPerformed(GuiButton button){
		if (button == btnAction) {
			folder.add("DefaultGUI");
			NetworkHandler.channel.sendToServer(new ClientToServer(folder));
			updateStringList();
		}
	}
		

	@Override
	public void updateScreen() {
		super.updateScreen();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawWorldBackground(0);
		super.drawScreen(mouseX, mouseY, partialTicks);
		GL11.glColor4f(1, 1, 1, 1);
		updateStringList();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
}

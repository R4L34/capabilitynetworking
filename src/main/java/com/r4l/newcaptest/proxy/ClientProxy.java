package com.r4l.newcaptest.proxy;

import com.r4l.newcaptest.capability.folder.FolderProvider;
import com.r4l.newcaptest.capability.folder.IFolder;
import com.r4l.newcaptest.gui.DefaultGUI;
import com.r4l.newcaptest.network.NetworkHandler;
import com.r4l.newcaptest.network.messages.ClientToServer;
import com.r4l.newcaptest.reference.Keybinds;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class ClientProxy extends CommonProxy {
	
	
	
	@SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
    	if (Keybinds.KEY_u.isPressed()) {
    		EntityPlayer playerSP = Minecraft.getMinecraft().player;
    		IFolder folder = playerSP.getCapability(FolderProvider.FOLDER_CAP, null);
    		folder.add("UUUUU");
    		NetworkHandler.channel.sendToServer(new ClientToServer(folder));
		} else if (Keybinds.KEY_i.isPressed()) {
			EntityPlayer playerSP = Minecraft.getMinecraft().player;
			IFolder folder = playerSP.getCapability(FolderProvider.FOLDER_CAP, null);
			for (String f : folder.getFolders()) {
				String message = f;
				playerSP.sendMessage(new TextComponentString(message));
			}
		}else if (Keybinds.KEY_o.isPressed()) {
			EntityPlayer playerSP = Minecraft.getMinecraft().player;
    		IFolder folder = playerSP.getCapability(FolderProvider.FOLDER_CAP, null);
			Minecraft.getMinecraft().displayGuiScreen(new DefaultGUI(folder));
		}
 
    }
}

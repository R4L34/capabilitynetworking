package com.r4l.newcaptest.network.messages;

import java.util.ArrayList;
import java.util.List;

import com.r4l.newcaptest.capability.folder.FolderProvider;
import com.r4l.newcaptest.capability.folder.IFolder;
import com.r4l.newcaptest.network.NetworkHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerToClient implements IMessage {

	private List<String> folders;
	private int folders_count;
	
	
	public ServerToClient() {}
	
	public ServerToClient(EntityPlayer server_player) {
		this.folders = server_player.getCapability(FolderProvider.FOLDER_CAP, null).getFolders();
		this.folders_count = folders.size();
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		folders = new ArrayList<>();
		folders_count = buf.readInt();
		for (int i = 0; i < folders_count; i++) {
			folders.add(ByteBufUtils.readUTF8String(buf));
		}
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(folders_count);
		for (int i = 0; i < folders_count; i++) {
			ByteBufUtils.writeUTF8String(buf, folders.get(i));
		}
		
	}
	
	public List<String> getFolders (){
		return this.folders;
	}
	
	public static class Handler implements IMessageHandler<ServerToClient, IMessage> {

		@Override
		public IMessage onMessage(ServerToClient message, MessageContext ctx) {
			NetworkHandler.getThreadListener(ctx).addScheduledTask(() -> {
					Minecraft mc = Minecraft.getMinecraft();
					
					IFolder old_folders = mc.player.getCapability(FolderProvider.FOLDER_CAP, null);
				    List<String> new_folders = message.getFolders();   
				    old_folders.setFolders(new_folders);

			});
			return null;
		}
	}

}

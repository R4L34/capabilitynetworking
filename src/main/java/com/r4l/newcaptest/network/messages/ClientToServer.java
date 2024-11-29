package com.r4l.newcaptest.network.messages;

import java.util.ArrayList;
import java.util.List;

import com.r4l.newcaptest.capability.folder.FolderProvider;
import com.r4l.newcaptest.capability.folder.IFolder;
import com.r4l.newcaptest.network.NetworkHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientToServer implements IMessage {

	private List<String> folders;
	
	private int folders_count;
	
	public ClientToServer () {}
	
	public ClientToServer (IFolder folder) {
		this.folders = folder.getFolders();
		this.folders_count = folder.size();
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
	
	public static class Handler implements IMessageHandler<ClientToServer, IMessage> {

	    @Override
	    public IMessage onMessage(ClientToServer message, MessageContext ctx) {
	    	EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
		    NetworkHandler.getThreadListener(ctx).addScheduledTask(() -> {
				
		    	IFolder old_folders = serverPlayer.getCapability(FolderProvider.FOLDER_CAP, null);
			    List<String> new_folders = message.getFolders();
			    
			    old_folders.setFolders(new_folders);
				
			});
			return null;

	    }
	}

}

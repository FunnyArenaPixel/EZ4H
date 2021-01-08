package me.liuli.ez4h.translators.converters;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.EntityMetadata;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataType;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerHealthPacket;
import com.nukkitx.protocol.bedrock.data.entity.EntityData;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import me.liuli.ez4h.bedrock.Client;
import me.liuli.ez4h.translators.cache.EntityInfo;

import java.util.ArrayList;

public class MetadataConverter {
    public static EntityMetadata[] convert(EntityDataMap bedrockMetadata,Client client,int entityId){
        ArrayList<EntityMetadata> metadata=new ArrayList<>();
        if(bedrockMetadata.containsKey(EntityData.AIR_SUPPLY)) {
            metadata.add(new EntityMetadata(1, MetadataType.INT, (int) bedrockMetadata.getShort(EntityData.AIR_SUPPLY)));
        }
        if(bedrockMetadata.containsKey(EntityData.NAMETAG)) {
            metadata.add(new EntityMetadata(2, MetadataType.STRING, bedrockMetadata.getString(EntityData.NAMETAG)));
        }
        if(bedrockMetadata.containsKey(EntityData.HEALTH)) {
            metadata.add(new EntityMetadata(7, MetadataType.FLOAT, (float)bedrockMetadata.getInt(EntityData.HEALTH)));
        }
        //not works
        //TODO: fix this
        if(bedrockMetadata.containsKey(EntityData.FUSE_LENGTH)) {
            if(bedrockMetadata.getInt(EntityData.FUSE_LENGTH)<=0){
                metadata.add(new EntityMetadata(15, MetadataType.INT, 1));
            }else{
                metadata.add(new EntityMetadata(15, MetadataType.INT, -1));
            }
        }
        if (bedrockMetadata.getFlags().getFlag(EntityFlag.CAN_SHOW_NAME)) {
            metadata.add(new EntityMetadata(3, MetadataType.BOOLEAN, true));
        } else {
            metadata.add(new EntityMetadata(3, MetadataType.BOOLEAN, false));
        }
        if (bedrockMetadata.getFlags().getFlag(EntityFlag.HAS_GRAVITY)) {
            metadata.add(new EntityMetadata(5, MetadataType.BOOLEAN, false));
        } else {
            metadata.add(new EntityMetadata(5, MetadataType.BOOLEAN, true));
        }
        if(bedrockMetadata.getFlags().getFlag(EntityFlag.SNEAKING)){
            metadata.add(new EntityMetadata(0,MetadataType.BYTE, (byte) 2));
        }
        if(bedrockMetadata.getFlags().getFlag(EntityFlag.ON_FIRE)){
            metadata.add(new EntityMetadata(0,MetadataType.BYTE, (byte) 1));
        }
        if(!(bedrockMetadata.getFlags().getFlag(EntityFlag.ON_FIRE)||bedrockMetadata.getFlags().getFlag(EntityFlag.SNEAKING))){
            metadata.add(new EntityMetadata(0,MetadataType.BYTE, (byte) 0));
        }
        return metadata.toArray(new EntityMetadata[metadata.size()]);
    }
}

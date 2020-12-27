package org.meditation.ez4h.translators.bedrockTranslators;

import com.github.steveice10.mc.protocol.data.game.entity.player.Animation;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityAnimationPacket;
import com.nukkitx.protocol.bedrock.packet.AnimatePacket;
import org.meditation.ez4h.bedrock.Client;
import org.meditation.ez4h.translators.BedrockTranslator;
import com.nukkitx.protocol.bedrock.BedrockPacket;

public class AnimatePacketTranslator implements BedrockTranslator {
    @Override
    public void translate(BedrockPacket inPacket, Client client) {
        AnimatePacket packet=(AnimatePacket)inPacket;
        switch (packet.getAction()){
            case SWING_ARM:{
                client.sendPacket(new ServerEntityAnimationPacket((int) packet.getRuntimeEntityId(), Animation.SWING_ARM));
                break;
            }
            case WAKE_UP:{
                client.sendPacket(new ServerEntityAnimationPacket((int) packet.getRuntimeEntityId(),Animation.LEAVE_BED));
                break;
            }
            case CRITICAL_HIT:{
                client.sendPacket(new ServerEntityAnimationPacket((int) packet.getRuntimeEntityId(),Animation.CRITICAL_HIT));
                break;
            }
            case MAGIC_CRITICAL_HIT:{
                client.sendPacket(new ServerEntityAnimationPacket((int) packet.getRuntimeEntityId(),Animation.ENCHANTMENT_CRITICAL_HIT));
                break;
            }
        }
    }
}

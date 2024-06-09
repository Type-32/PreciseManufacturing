package cn.crtlprototypestudios.precisemanufacturing.util;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Reference {
    public static final String MOD_ID = "prma";
    public static final Logger LOGGER = LogManager.getLogManager().getLogger(MOD_ID);
    public static class Network{
        public static final String NETWORK_CHANNEL = "network";
        public static final String NETWORK_CHANNEL_VERSION = "1.2";
        public static final String HANDSHAKE_CHANNEL = "handshake";
        public static final String HANDSHAKE_CHANNEL_VERSION = "1.2";
    }

    public static String morphString(String s){
        return String.format("%s:%s", MOD_ID, s);
    }
}

package cn.crtlprototypestudios.precisemanufacturing.util;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Reference {
    public static final String MOD_ID = "prma";
    public static final Logger LOGGER = LogManager.getLogManager().getLogger(MOD_ID);
    public static class Network{
        public static final String NETWORK_RL = "main";
        public static final String NETWORK_CHANNEL_VERSION = "1.0";
    }
}

package cn.crtlprototypestudios.precisemanufacturing.util;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ExternalReference {
    public static final Dictionary<String, Integer> BULLET_UPPER_LIMITS = new Hashtable<String, Integer>();
    static {
        BULLET_UPPER_LIMITS.put("68x51fury", 300);
        BULLET_UPPER_LIMITS.put("9mm", 300);
        BULLET_UPPER_LIMITS.put("338", 150);
        BULLET_UPPER_LIMITS.put("308", 240);
        BULLET_UPPER_LIMITS.put("46x30", 300);
        BULLET_UPPER_LIMITS.put("45acp", 300);
        BULLET_UPPER_LIMITS.put("50bmg", 150);
        BULLET_UPPER_LIMITS.put("12g", 120);
        BULLET_UPPER_LIMITS.put("30_06", 180);
        BULLET_UPPER_LIMITS.put("50ae", 240);
        BULLET_UPPER_LIMITS.put("magnum_r", 300);
        BULLET_UPPER_LIMITS.put("rpg_rocket", 3);
        BULLET_UPPER_LIMITS.put("762x25", 300);
        BULLET_UPPER_LIMITS.put("762x39", 300);
        BULLET_UPPER_LIMITS.put("762x54", 300);
        BULLET_UPPER_LIMITS.put("556x45", 300);
        BULLET_UPPER_LIMITS.put("58x42", 300);
    }
}

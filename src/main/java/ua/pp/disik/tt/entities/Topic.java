package ua.pp.disik.tt.entities;

import java.util.*;

/**
 * Created by disik on 4/6/17.
 */
public enum Topic {
    SALE, PURCHASE, SERVICE, LEASE, SOCIAL;

    private static final Random random = new Random();

    public static Topic getRandomTopic() {
        Topic[] topics = Topic.values();
        int index = random.nextInt(topics.length);

        return topics[index];
    }
}

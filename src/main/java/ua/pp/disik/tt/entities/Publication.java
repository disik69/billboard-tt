package ua.pp.disik.tt.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

/**
 * Created by disik on 4/6/17.
 */

@Document
public class Publication {
    @Id
    private String id;
    private Topic topic;
    private long createdAt;
    private String title;
    private String body;
    private String userId;

    public Publication(String userId, String title, String body, Topic topic) {
        this(userId, title, body, topic, Instant.now().getEpochSecond());
    }

    @PersistenceConstructor
    public Publication(String userId, String title, String body, Topic topic, long createdAt) {
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.topic = topic;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUserId() {
        return userId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

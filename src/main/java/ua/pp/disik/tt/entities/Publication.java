package ua.pp.disik.tt.entities;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by disik on 4/6/17.
 */

@Document
public class Publication {
    @Id
    private String id;

    @NotNull
    private Topic topic;

    private long createdAt;

    @NotNull
    @Size(min = 10, max = 30, message = "Title must be between 10 and 30")
    private String title;

    @NotNull
    @Size(min = 20, max = 400, message = "Publication must be between 20 and 400")
    private String body;

    @DBRef(lazy = true)
    private User user;

    public Publication() {
    }

    public Publication(User user, String title, String body, Topic topic) {
        this(user, title, body, topic, Instant.now().getEpochSecond());
    }

    @PersistenceConstructor
    public Publication(User user, String title, String body, Topic topic, long createdAt) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public ZonedDateTime getCreatedAt() {
        Instant instant = Instant.ofEpochSecond(createdAt);

        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public String formatCreatedAt(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return getCreatedAt().format(formatter);
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt() {
        this.createdAt = Instant.now().getEpochSecond();
    }
}

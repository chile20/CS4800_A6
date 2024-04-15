/*
Assignment6_ChatApp
File name: Message.java
Author: Chi Le
*/

package ChatApp;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    final private User sender;
    final private User recipient;
    final private LocalDateTime timestamp;
    final private String content;

    // Constructor
    public Message(User sender, User recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now();
        this.content = content;
    }

    // Getters and setters
    public User getSender() {
        return this.sender;
    }

    public User getRecipient() {
        return this.recipient;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getContent() {
        return this.content;
    }
}

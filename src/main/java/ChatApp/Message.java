/*
Assignment6_ChatApp
Author: Chi Le
File name: Message.java
Description: Represents a single message in a chat application.Encapsulates all necessary details of a
message such as the sender, recipient, content, and timestamp.
*/

package ChatApp;

import java.time.LocalDateTime;

public class Message {
    private final User sender;
    private final User recipient;
    private final LocalDateTime timestamp;
    private final String content;

    /**
     * Constructs a new Message instance.
     *
     * @param sender The user who is sending the message.
     * @param recipient The user who is the recipient of the message.
     * @param content The textual content of the message.
     */
    public Message(User sender, User recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = LocalDateTime.now();
        this.content = content;
    }

    /**
     * Returns the sender of the message.
     *
     * @return The user who sent the message.
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * Returns the recipient of the message.
     *
     * @return The user who is the intended recipient of the message.
     */
    public User getRecipient() {
        return this.recipient;
    }

    /**
     * Returns the timestamp of when the message was created.
     *
     * @return The date and time when the message was sent.
     */
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Returns the content of the message.
     *
     * @return The textual content of the message.
     */
    public String getContent() {
        return this.content;
    }
}

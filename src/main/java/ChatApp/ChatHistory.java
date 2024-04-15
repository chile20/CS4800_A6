/*
Assignment6_ChatApp
Author: Chi Le
File name: ChatHistory.java
Description: Manages chat history in a chat application by handling message storage and retrieval.
*/
package ChatApp;

import java.util.*;

public class ChatHistory implements  IterableByUser{
    private List<Message> messages = new ArrayList<>();

    /**
     * Adds a message to the chat history.
     *
     * @param message The message to be added.
     */
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * Removes a specific message from the chat history.
     *
     * @param message The message to remove.
     */
    public void removeMessage(Message message) {
        messages.remove(message);
    }

    /**
     * Retrieves the last message in the chat history, if any.
     *
     * @return The last message or null if the history is empty.
     */
    public Message getLastMessage() {
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(messages.size() - 1);
    }

    /**
     * Returns all messages in the chat history.
     *
     * @return A list of messages.
     */
    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    /**
     * Provides an iterator for messages filtered by a specific user.
     *
     * @param userToSearchWith The user whose messages to iterate over.
     * @return An iterator for messages by the specified user.
     */
    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new SearchMessagesByUser(messages, userToSearchWith);
    }
}


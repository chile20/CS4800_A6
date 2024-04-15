/*
Assignment6_ChatApp
Author: Chi Le
File name: SearchMessagesByUser.java
Description: Provides an iterator to iterate over messages related to a specific user,
*/

package ChatApp;

import java.util.*;

public class SearchMessagesByUser implements Iterator<Message> {
    private final List<Message> messages;
    private final User user;
    private final Iterator<Message> iterator;
    private Message nextMessage;

    /**
     * Constructs a SearchMessagesByUser with a list of messages and a specific user.
     *
     * @param messages A list of messages to search through.
     * @param user     The user whose messages are to be filtered.
     */
    public SearchMessagesByUser(List<Message> messages, User user) {
        this.messages = messages;
        this.user = user;
        this.iterator = messages.iterator();
        advance();
    }

    /**
     * Advances the iterator to the next relevant message that involves the user.
     */
    private void advance() {
        while (iterator.hasNext()) {
            Message m = iterator.next();
            if (m.getSender().equals(user) || m.getRecipient().equals(user)) {
                nextMessage = m;
                return;
            }
        }
        nextMessage = null;
    }

    @Override
    public boolean hasNext() {
        return nextMessage != null;
    }

    @Override
    public Message next() {
        if (nextMessage == null) {
            throw new NoSuchElementException("No more messages available for this user.");
        }
        Message current = nextMessage;
        advance();
        return current;
    }
}

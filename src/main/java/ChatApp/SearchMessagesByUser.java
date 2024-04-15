/*
Assignment6_ChatApp
File name: SearchMessagesByUser.java
Author: Chi Le
*/

package ChatApp;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchMessagesByUser implements Iterator<Message> {
    private final List<Message> messages;
    private final User user;
    private final Iterator<Message> iterator;
    private Message nextMessage;

    public SearchMessagesByUser(List<Message> messages, User user) {
        this.messages = messages;
        this.user = user;
        this.iterator = messages.iterator();
        advance();
    }

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

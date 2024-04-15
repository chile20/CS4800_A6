/*
Assignment6_ChatApp
File name: ChatHistory.java
Author: Chi Le
*/

package ChatApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ChatHistory implements  IterableByUser{
    private List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        this.messages.add(message);
    }
    public Message getLastMessage() {
        if (messages.isEmpty()) return null;
        return messages.get(messages.size() - 1);
    }
    public void removeMessage(Message message) {
        messages.remove(message);
    }
    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }
    public void printHistory(User user, User otherUser) {
        String name = user.getName();
        String otherUsername = otherUser.getName();
        System.out.println("Chat history between " + name + " and " + otherUsername + ":");
        for (Message message : messages) {
            if ((message.getSender().getName().equals(name) && message.getRecipient().getName().equals(otherUsername)) ||
                    (message.getSender().getName().equals(otherUsername) && message.getRecipient().getName().equals(name))) {
                System.out.println(message.getSender().getName() + " to " + message.getRecipient().getName() +
                        " [" + message.getTimestamp() + "]: " + message.getContent());
            }
        }
    }
    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new SearchMessagesByUser(messages, userToSearchWith);
    }
}


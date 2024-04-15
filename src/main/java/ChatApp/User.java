/*
Assignment6_ChatApp
Author: Chi Le
File name: User.java
Description: Represents a user in a chat application. Users can send messages, block other users,
and retrieve chat history with other users. This class also supports iterating over messages.
*/

package ChatApp;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class User implements IterableByUser {
    private String name;
    private ChatServer server;
    private List<MessageMemento> sentMessages = new ArrayList<>();

    /**
     * Constructs a User with a specified name and associated chat server.
     *
     * @param name   The name of the user.
     * @param server The chat server to which the user is connected.
     */
    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
    }

    /**
     * Save current state of sent messages
     */
    private void saveState(Message message) {
        sentMessages.add(new MessageMemento(message));
    }
    /**
     * Sends a message to a single recipient.
     *
     * @param recipient The user receiving the message.
     * @param content   The textual content of the message.
     */
    public void sendMessage(User recipient, String content) {
        Message message = new Message(this, recipient, content);
        saveState(message);
        server.sendMessage(this, recipient, message);
    }

    /**
     * Sends a message to multiple recipients.
     *
     * @param recipients A list of users to receive the message.
     * @param content    The textual content of the message.
     */
    public void sendMessageToMultiple(List<User> recipients, String content) {
        for (User recipient : recipients) {
            sendMessage(recipient, content);
        }
    }

    /**
     * Blocks a specific user from sending messages to this user.
     *
     * @param userToBlock The username of the user to block.
     */
    public void blockUser(String userToBlock) {
        server.blockUser(this.getName(), userToBlock);
    }

    /**
     * Undoes the last message sent by this user.
     */
    public void undoLastMessage() {
        if (!sentMessages.isEmpty()) {
            MessageMemento memento = sentMessages.remove(sentMessages.size() - 1);
            server.undoLastMessage(memento.getMessage());
        }
    }

    /**
     * Prints the chat history between this user and another user.
     *
     * @param otherUser Another user to retrieve the chat history with.
     */
    public void printChatHistory(User otherUser) {
        List<Message> history = server.getChatHistoryBetweenUsers(this, otherUser);
        System.out.println("Chat history between " + this.getName() + " and " + otherUser.getName() + ":");
        for (Message message : history) {
            System.out.println(message.getSender().getName() + " to " + message.getRecipient().getName() +
                    " [" + message.getTimestamp() + "]: " + message.getContent());
        }
    }

    /**
     * Returns the name of the user.
     *
     * @return The user's name.
     */
    public String getName() {return name;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return server.getMessageIterator(this, userToSearchWith);
    }
}


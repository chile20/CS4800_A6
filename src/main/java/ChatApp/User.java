/*
Assignment6_ChatApp
File name: User.java
Author: Chi Le
*/

package ChatApp;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class User implements IterableByUser {
    private String name;
    private ChatServer server;
    private ChatHistory chatHistory = new ChatHistory();
    private List<Message> sentMessages = new ArrayList<>();
    // Constructor
    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
    }

    // Send message through server
    public void sendMessage(User recipient, String content) {
        Message message = new Message(this, recipient, content);
        sentMessages.add(message);
        server.sendMessage(this, recipient, message);
    }
    public void sendMessageToMultiple(List<User> recipients, String content) {
        for (User recipient : recipients) {
            Message message = new Message(this, recipient, content);
            sentMessages.add(message);
            server.sendMessage(this, recipient, message);
            System.out.println("Sent message to " + recipient.getName());
        }
    }
    // Message received is saved to chat history
    public void receiveMessage(Message message) {
        chatHistory.addMessage(message);
    }

    public void blockUser(String userToBlock) {
        server.blockUser(this.getName(), userToBlock);
    }
    // Undo the last message
    public void undoLastMessage() {
        if (!sentMessages.isEmpty()) {
            Message lastSentMessage = sentMessages.remove(sentMessages.size() - 1);
            server.undoLastMessage(lastSentMessage);
        } else {
            System.out.println("No messages to undo.");
        }
    }

    public void printChatHistory(User otherUser) {
        chatHistory.printHistory(this, otherUser);
    }
    // Getters and setters
    public String getName() {return name;
    }
    public ChatHistory getChatHistory() {
        return this.chatHistory;
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }
}


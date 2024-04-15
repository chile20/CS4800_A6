/*
Assignment6_ChatApp
File name: ChatServer.java
Author: Chi Le
*/

package ChatApp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
public class ChatServer {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Set<String>> blockList = new HashMap<>();

    public void registerUser(User user) {
        users.put(user.getName(), user);
    }

    public void unregisterUser(User user) {
        users.remove(user.getName());
    }

    public void sendMessage(User sender, User recipient, Message message) {
        if (blockList.getOrDefault(recipient.getName(), new HashSet<>()).contains(sender.getName())) {
            System.out.println("Message from " + sender.getName() + " to " + recipient.getName() + " blocked.");
        } else {
            sender.receiveMessage(message);
            recipient.receiveMessage(message);
            System.out.println("Message from " + sender.getName() + " to " + recipient.getName() + ": " + message.getContent());
        }
    }
    public void printChatHistory(User user1, User user2) {
        System.out.println("Chat History between " + user1.getName() + " and " + user2.getName() + ":");
    }

    public void undoLastMessage(Message message) {
        User sender = message.getSender();
        User recipient = message.getRecipient();
        ChatHistory senderHistory = sender.getChatHistory();
        ChatHistory recipientHistory = recipient.getChatHistory();
        senderHistory.removeMessage(message);
        recipientHistory.removeMessage(message);
        System.out.println("Removed latest message from " + sender.getName() + " to " + recipient.getName());
    }

    public void blockUser(String requester, String userToBlock) {
        Set<String> blocks = blockList.getOrDefault(requester, new HashSet<>());
        blocks.add(userToBlock);
        blockList.put(requester, blocks);
        System.out.println(requester + " has blocked " + userToBlock);
    }
}


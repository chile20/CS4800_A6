/*
Assignment6_ChatApp
Author: Chi Le
File name: ChatServer.java
Description: Manages user registrations, message sending, and user blocking within a chat application.
*/

package ChatApp;

import java.util.*;

public class ChatServer {
    private ChatHistory chatHistory = new ChatHistory();
    private Map<String, User> users = new HashMap<>();
    private Map<String, Set<String>> blockList = new HashMap<>();

    /**
     * Registers a user in the chat server.
     *
     * @param user The user to register.
     */
    public void registerUser(User user) {
        users.put(user.getName(), user);
    }

    /**
     * Unregisters a user from the chat server.
     *
     * @param user The user to unregister.
     */
    public void unregisterUser(User user) {
        users.remove(user.getName());
    }

    /**
     * Sends a message from one user to another unless the recipient has blocked the sender.
     *
     * @param sender    The user sending the message.
     * @param recipient The user receiving the message.
     * @param message   The message to send.
     */
    public void sendMessage(User sender, User recipient, Message message) {
        if (blockList.getOrDefault(recipient.getName(), new HashSet<>()).contains(sender.getName())) {
            System.out.println("Message from " + sender.getName() + " to " + recipient.getName() + " blocked.");
        } else {
            chatHistory.addMessage(message);
            System.out.println("Message from " + sender.getName() + " to " + recipient.getName() + ": " + message.getContent());
        }
    }

    /**
     * Retrieves the chat history between two users.
     *
     * @param user1 The first user.
     * @param user2 The second user.
     * @return A list of messages exchanged between the two users.
     */
    public List<Message> getChatHistoryBetweenUsers(User user1, User user2) {
        List<Message> messages = chatHistory.getMessages();
        List<Message> filteredMessages = new ArrayList<>();
        String name1 = user1.getName();
        String name2 = user2.getName();
        for (Message message : messages) {
            if ((message.getSender().getName().equals(name1) && message.getRecipient().getName().equals(name2)) ||
                    (message.getSender().getName().equals(name2) && message.getRecipient().getName().equals(name1))) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }

    /**
     * Removes the last sent message from the chat history.
     *
     * @param message The message to remove.
     */
    public void undoLastMessage(Message message) {
        chatHistory.removeMessage(message);
    }

    /**
     * Blocks communication from a specific user to another user.
     *
     * @param requester    The user initiating the block.
     * @param userToBlock The user to be blocked.
     */
    public void blockUser(String requester, String userToBlock) {
        Set<String> blocks = blockList.getOrDefault(requester, new HashSet<>());
        blocks.add(userToBlock);
        blockList.put(requester, blocks);
        System.out.println(requester + " has blocked " + userToBlock);
    }

    /**
     * Provides an iterator over messages exchanged between two users.
     *
     * @param user1 The first user.
     * @param user2 The second user.
     * @return An iterator for messages between the two users.
     */
    public Iterator<Message> getMessageIterator(User user1, User user2) {
        List<Message> userMessages = getChatHistoryBetweenUsers(user1, user2);
        return userMessages.iterator();
    }
}


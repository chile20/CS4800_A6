/*
Assignment6_ChatApp
File name: Driver.java
Author: Chi Le
*/
package ChatApp;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        // Initialize a new server
        ChatServer server = new ChatServer();

        // Added users
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        // Register users
        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        // Initialize group for sending messages to multiple
        List<User> group = new ArrayList<>();
        group.add(bob);
        group.add(charlie);

        // Sending messages between users
        alice.sendMessageToMultiple(group, "Hello Bob and Charlie!");
        bob.sendMessage(alice, "Hi Alice");
        alice.sendMessage(bob, "Hello Bob!");
        alice.sendMessage(bob, "Hello again Bob!");

        // Undo a message
        alice.undoLastMessage();
        alice.undoLastMessage();

        // Print chat history of users
        alice.printChatHistory(bob);
        bob.printChatHistory(alice);
        charlie.printChatHistory(alice);

        // Iterating messages
        Iterator<Message> user1Iterator = alice.iterator(bob);
        System.out.println("Messages sent or received by Alice:");
        while (user1Iterator.hasNext()) {
            Message message = user1Iterator.next();
            System.out.println(message.getContent());
        }
    }
}


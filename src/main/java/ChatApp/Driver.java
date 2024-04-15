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

        // Register users
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);
        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        // Initialize group for sending messages to multiple users
        List<User> group = new ArrayList<>();
        group.add(bob);
        group.add(charlie);

        // Sending a message to multiple users
        System.out.println("Alice sends a message to Bob and Charlie:");
        alice.sendMessageToMultiple(group, "Hello Bob and Charlie!");
        System.out.println("============================================");

        // Single message exchanges
        System.out.println("Bob replies to Alice:");
        bob.sendMessage(alice, "Hi Alice");
        System.out.println("Alice sends two messages to Bob:");
        alice.sendMessage(bob, "Hello Bob!");
        alice.sendMessage(bob, "Hello again Bob!");
        System.out.println("============================================");

        // Undo the last two messages
        System.out.println("Alice undoes her last two messages to Bob...");
        alice.undoLastMessage();
        alice.undoLastMessage();
        System.out.println("============================================");

        // Print chat history after undo operations
        System.out.println("Chat history between Alice and Bob after undoing messages:");
        alice.printChatHistory(bob);
        System.out.println("============================================");

        // Block user demonstration
        System.out.println("Bob blocks Alice and tries to receive a message:");
        bob.blockUser("Alice");
        alice.sendMessage(bob, "Are you there, Bob?");
        bob.printChatHistory(alice);
        System.out.println("============================================");

        // Iterating messages between Alice and Bob
        System.out.println("Iterating through messages between Alice and Bob:");
        Iterator<Message> user1Iterator = alice.iterator(bob);
        while (user1Iterator.hasNext()) {
            Message message = user1Iterator.next();
            System.out.println(message.getContent());
        }
        System.out.println("============================================");
    }
}


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
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);
        // Alice sends messages to Bob and Charlie
        List<User> group = new ArrayList<>();
        group.add(bob);
        group.add(charlie);
        alice.sendMessageToMultiple(group, "Hello Bob and Charlie!");
        alice.sendMessage(bob, "Hello again Bob!");
        alice.undoLastMessage();
        alice.undoLastMessage();
        alice.printChatHistory(bob);
        bob.printChatHistory(alice);
        charlie.printChatHistory(alice);

//        Iterator<Message> user1Iterator = alice.iterator(bob);
//        System.out.println("Messages sent or received by Alice:");
//        while (user1Iterator.hasNext()) {
//            Message message = user1Iterator.next();
//            System.out.println(message.getContent());
//        }
    }
}


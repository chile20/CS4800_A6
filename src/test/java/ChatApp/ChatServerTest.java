package ChatApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.List;
class ChatServerTest {
    private ChatServer server;
    private User john, jane;
    @BeforeEach
    void setUp() {
        server = new ChatServer();
        john = new User("John", server);
        jane = new User("Jane", server);
        server.registerUser(john);
        server.registerUser(jane);
    }

    @Test
    void registerUser() {
        ChatServer newServer = new ChatServer();
        newServer.registerUser(john);
        assertTrue(newServer.getChatHistoryBetweenUsers(john, jane).isEmpty(), "No messages should be present between newly registered users");
    }

    @Test
    void unregisterUser() {
        server.unregisterUser(john);
        assertNull(server.getChatHistory().getMessages().stream()
                .filter(msg -> msg.getSender().equals(john) || msg.getRecipient().equals(john))
                .findFirst()
                .orElse(null), "Unregistered user should not be found in any messages");
    }

    @Test
    void sendMessage() {
        Message message = new Message(john, jane, "Hi Jane!");
        server.sendMessage(john, jane, message);
        assertEquals("Hi Jane!", server.getChatHistory().getLastMessage().getContent(), "The message content should match the sent message");
    }

    @Test
    void getChatHistoryBetweenUsers() {
        server.sendMessage(john, jane, new Message(john, jane, "Hello Jane"));
        server.sendMessage(jane, john, new Message(jane, john, "Hi John"));
        List<Message> messages = server.getChatHistoryBetweenUsers(john, jane);
        assertEquals(2, messages.size(), "There should be two messages between John and Jane");
    }

    @Test
    void getChatHistory() {
        server.sendMessage(john, jane, new Message(john, jane, "Testing history"));
        assertFalse(server.getChatHistory().getMessages().isEmpty(), "Chat history should not be empty after sending a message");
    }

    @Test
    void undoLastMessage() {
        Message message = new Message(john, jane, "Temporary message");
        server.sendMessage(john, jane, message);
        server.undoLastMessage(message);
        assertNull(server.getChatHistory().getLastMessage(), "Last message should be null after undo");
    }

    @Test
    void blockUser() {
        User mike = new User("Mike", server);
        server.registerUser(mike);
        server.blockUser(mike.getName(), john.getName());
        server.sendMessage(john, mike, new Message(john, mike, "Blocked message"));
        List<Message> messages = server.getChatHistoryBetweenUsers(john, mike);
        assertTrue(messages.isEmpty(), "No messages should be sent or received since John is blocked by Mike.");
    }

    @Test
    void getMessageIterator() {
        server.sendMessage(john, jane, new Message(john, jane, "First message"));
        server.sendMessage(jane, john, new Message(jane, john, "Second message"));
        Iterator<Message> iterator = server.getMessageIterator(john, jane);
        assertTrue(iterator.hasNext(), "Iterator should have messages");
        assertEquals("First message", iterator.next().getContent());
        assertEquals("Second message", iterator.next().getContent());
    }
}
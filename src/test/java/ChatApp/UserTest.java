package ChatApp;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


class UserTest {
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
    void sendMessage() {
        String content = "Hello Jane";

        ChatHistory chatHistory = server.getChatHistory();
        john.sendMessage(jane, content);
        assertSame(chatHistory.getLastMessage().getContent(), content, "Last message content should be \"Hello Jane\"");
    }

    @Test
    void sendMessageToMultiple() {
        User mike = new User("Mike", server);
        server.registerUser(mike);
        List<User> recipients = Arrays.asList(jane, mike);
        String content = "Hello everyone";
        john.sendMessageToMultiple(recipients, content);

        // Check if both recipients received the message
        List<Message> messages = server.getChatHistory().getMessages();
        boolean janeReceived = false, mikeReceived = false;

        for (Message message : messages) {
            if (message.getSender().equals(john) && message.getContent().equals(content)) {
                if (message.getRecipient().equals(jane)) {
                    janeReceived = true;
                } else if (message.getRecipient().equals(mike)) {
                    mikeReceived = true;
                }
            }
        }

        assertTrue(janeReceived, "Jane should have received the message");
        assertTrue(mikeReceived, "Mike should have received the message");
    }

    @Test
    void blockUser() {
        jane.blockUser("John");
        john.sendMessage(jane, "Hello Jane");
        assertNull(server.getChatHistory().getLastMessage(), "John should be blocked and message should not be sent");
    }

    @Test
    void undoLastMessage() {
        john.sendMessage(jane, "Hello Jane");
        john.undoLastMessage();
        assertNull(server.getChatHistory().getLastMessage(), "Last message should be removed from the chat history");
    }

    @Test
    void printChatHistory() {
        john.sendMessage(jane, "Hello Jane");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        john.printChatHistory(jane);
        assertTrue(outContent.toString().contains("Hello Jane"), "Chat history should contain 'Hello Jane'");
    }

    @Test
    void getName() {
        assertEquals("John", john.getName(), "User name should be John");
    }

    @Test
    void iterator() {
        john.sendMessage(jane, "First message to Jane");
        john.sendMessage(jane, "Second message to Jane");
        Iterator<Message> iterator = john.iterator(jane);
        assertTrue(iterator.hasNext(), "Iterator should have messages");
        assertEquals("First message to Jane", iterator.next().getContent());
        assertEquals("Second message to Jane", iterator.next().getContent());
        assertFalse(iterator.hasNext(), "Iterator should have no more messages");
    }
}
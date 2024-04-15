package ChatApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

class ChatHistoryTest {
    private ChatHistory chatHistory;
    private Message message1, message2;

    @BeforeEach
    void setUp() {
        chatHistory = new ChatHistory();
        User sender = new User("John", null);
        User recipient = new User("Jane", null);
        message1 = new Message(sender, recipient, "Hello Jane");
        message2 = new Message(sender, recipient, "Goodbye Jane");
    }

    @Test
    void addMessage() {
        chatHistory.addMessage(message1);
        assertFalse(chatHistory.getMessages().isEmpty(), "Chat history should not be empty after adding a message");
        assertEquals(message1, chatHistory.getMessages().get(0), "The first message should be the one that was added");
    }

    @Test
    void removeMessage() {
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);
        chatHistory.removeMessage(message1);
        assertFalse(chatHistory.getMessages().contains(message1), "Message1 should be removed from chat history");
        assertTrue(chatHistory.getMessages().contains(message2), "Message2 should remain in the chat history");
    }

    @Test
    void getLastMessage() {
        assertNull(chatHistory.getLastMessage(), "Last message should be null when no messages have been added");
        chatHistory.addMessage(message1);
        assertEquals(message1, chatHistory.getLastMessage(), "Last message should be the one that was last added");
        chatHistory.addMessage(message2);
        assertEquals(message2, chatHistory.getLastMessage(), "Last message should update to the most recently added message");
    }

    @Test
    void getMessages() {
        assertTrue(chatHistory.getMessages().isEmpty(), "Initial chat history should be empty");
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);
        List<Message> messages = chatHistory.getMessages();
        assertEquals(2, messages.size(), "Chat history should contain two messages");
        assertTrue(messages.contains(message1) && messages.contains(message2), "Chat history should contain both messages added");
    }

    @Test
    void iterator() {
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);
        Iterator<Message> iterator = chatHistory.iterator(message1.getSender());
        assertTrue(iterator.hasNext(), "Iterator should have messages");
        assertEquals(message1, iterator.next(), "First message in iterator should be message1");
        assertEquals(message2, iterator.next(), "Second message in iterator should be message2");
        assertFalse(iterator.hasNext(), "Iterator should have no more messages");
    }
}
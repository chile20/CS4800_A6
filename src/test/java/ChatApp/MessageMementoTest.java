package ChatApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageMementoTest {
    private Message originalMessage;
    private MessageMemento memento;

    @BeforeEach
    void setUp() {
        User sender = new User("John", null);  // Assuming 'null' for server during testing.
        User recipient = new User("Jane", null);
        originalMessage = new Message(sender, recipient, "Hello, this is a test message!");
        memento = new MessageMemento(originalMessage);
    }

    @Test
    void getMessage() {
        Message restoredMessage = memento.getMessage();
        assertNotNull(restoredMessage, "The restored message should not be null.");
        assertSame(originalMessage, restoredMessage, "The restored message should be the same as the original.");
        assertEquals(originalMessage.getContent(), restoredMessage.getContent(), "The content of the restored message should match the original.");
        assertEquals(originalMessage.getSender(), restoredMessage.getSender(), "The sender of the restored message should match the original.");
        assertEquals(originalMessage.getRecipient(), restoredMessage.getRecipient(), "The recipient of the restored message should match the original.");
    }
}
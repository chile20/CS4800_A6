package ChatApp;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    private User sender;
    private User recipient;
    private String content;
    private Message message;

    @BeforeEach
    void setUp() {
        ChatServer mockServer = new ChatServer();
        sender = new User("John", mockServer);
        recipient = new User("Jane", mockServer);
        content = "Hello, Jane!";
        message = new Message(sender, recipient, content);
    }

    @Test
    void testConstructorAndGetterMethods() {
        assertSame(sender, message.getSender(), "Sender should be John");
        assertSame(recipient, message.getRecipient(), "Recipient should be Jane");
        assertEquals(content, message.getContent(), "Content should match the initial message");

        // Since LocalDateTime.now() is used, we assume that the test runs fast enough for the timestamp to be equal
        LocalDateTime expectedTime = LocalDateTime.now();
        assertTrue(message.getTimestamp().isBefore(expectedTime.plusSeconds(1)) &&
                        message.getTimestamp().isAfter(expectedTime.minusSeconds(1)),
                "Timestamp should be roughly the time of object creation");
    }
}
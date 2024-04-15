package ChatApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

class SearchMessagesByUserTest {
    private List<Message> messages;
    private User john;
    private User jane;
    private User mike;
    private SearchMessagesByUser iterator;

    @BeforeEach
    void setUp() {
        john = new User("John", null);  // Assuming null server for testing.
        jane = new User("Jane", null);
        mike = new User("Mike", null);

        messages = Arrays.asList(
                new Message(john, jane, "Hello Jane!"),
                new Message(jane, mike, "Hi Mike!"),
                new Message(mike, john, "Hey John!"),
                new Message(jane, john, "Reply from Jane to John")
        );
        iterator = new SearchMessagesByUser(messages, john);
    }

    @Test
    void hasNext() {
        assertTrue(iterator.hasNext(), "Iterator should have next since there are messages involving John");
        iterator.next(); // move to first message involving John
        assertTrue(iterator.hasNext(), "Iterator should still have next after one advance");
        iterator.next(); // move to second message involving John
        iterator.next(); // move to second message involving John
        assertFalse(iterator.hasNext(), "Iterator should not have next after reaching the end of relevant messages");
    }

    @Test
    void next() {
        assertEquals("Hello Jane!", iterator.next().getContent(), "First message should be 'Hello Jane!'");
        assertEquals("Hey John!", iterator.next().getContent(), "Second message should be 'Hey John!'");
        assertEquals("Reply from Jane to John", iterator.next().getContent(), "Third message should be 'Reply from Jane to John!'");
        assertThrows(NoSuchElementException.class, iterator::next, "Should throw NoSuchElementException when no more messages are available");
    }
}

/*
Assignment6_ChatApp
File name: MessageMemento.java
Author: Chi Le
*/

package ChatApp;

import java.util.ArrayList;
import java.util.List;

public class MessageMemento {
    private final List<Message> messages;

    public MessageMemento(List<Message> messages) {
        this.messages = new ArrayList<>(messages); // Deep copy to ensure the state is preserved
    }

    List<Message> getState() {
        return this.messages;
    }
}



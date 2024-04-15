/*
Assignment6_ChatApp
Author: Chi Le
File name: MessageMemento.java
*/

package ChatApp;

import java.util.ArrayList;
import java.util.List;

public class MessageMemento {
    private Message messageMemento;

    public MessageMemento(Message message) {
        this.messageMemento = message;
    }

    public Message getMessage() {
        return messageMemento;
    }
}



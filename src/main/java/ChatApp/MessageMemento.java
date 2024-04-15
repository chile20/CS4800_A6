/*
Assignment6_ChatApp
Author: Chi Le
File name: MessageMemento.java
Description: Represents a memento object for a Message. Stores the state of a Message object and
allows for the state to be restored at a later time.
*/

package ChatApp;

public class MessageMemento {
    private Message messageMemento;

    /**
     * Constructs a MessageMemento that encapsulates the state of a Message.
     *
     * @param message The Message object whose state is to be saved.
     */
    public MessageMemento(Message message) {
        this.messageMemento = message;
    }

    /**
     * Constructs a MessageMemento that encapsulates the state of a Message.
     *
     * @param message The Message object whose state is to be saved.
     */
    public Message getMessage() {
        return messageMemento;
    }
}



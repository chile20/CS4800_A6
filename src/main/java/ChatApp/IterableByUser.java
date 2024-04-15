/*
Assignment6_ChatApp
Author: Chi Le
File name: IterableByUser.java
Description: Interface to iterate over messages specific to a user.
*/

package ChatApp;

import java.util.Iterator;
public interface IterableByUser {
    Iterator<Message> iterator(User userToSearchWith);
}

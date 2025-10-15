package seedu.address.commons.exceptions;

/**
 * Represents an error during matching data in file with existing item.
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

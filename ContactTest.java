import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    private Contact valid() {
        return new Contact("ID123", "Gage", "Pigati", "9255551234", "123 Main St");
    }

    @Test
    void constructorAndGettersStoreValidData() {
        Contact contact = valid();
        assertAll(
                () -> assertEquals("ID123", contact.getContactId()),
                () -> assertEquals("Gage", contact.getFirstName()),
                () -> assertEquals("Pigati", contact.getLastName()),
                () -> assertEquals("9255551234", contact.getPhone()),
                () -> assertEquals("123 Main St", contact.getAddress()));
    }

    @Test
    void idCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Gage", "Pigati", "9255551234", "Home"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "Gage", "Pigati", "9255551234", "Home"));
        assertDoesNotThrow(() -> new Contact("1234567890", "Gage", "Pigati", "9255551234", "Home"));
    }

    @Test
    void firstNameRejectsNullAndOverTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", null, "Pigati", "9255551234", "Home"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "12345678901", "Pigati", "9255551234", "Home"));
        assertDoesNotThrow(() -> new Contact("ID", "1234567890", "Pigati", "9255551234", "Home"));
    }

    @Test
    void lastNameRejectsNullAndOverTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "Gage", null, "9255551234", "Home"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "Gage", "12345678901", "9255551234", "Home"));
        assertDoesNotThrow(() -> new Contact("ID", "Gage", "1234567890", "9255551234", "Home"));
    }

    @Test
    void phoneRejectsNullWrongLengthLettersAndSpaces() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "Gage", "Pigati", null, "Home"));
        for (String invalid : new String[]{"123456789", "12345678901", "12345abcde", "123 567890"}) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Contact("ID", "Gage", "Pigati", invalid, "Home"));
        }
    }

    @Test
    void addressRejectsNullAndOverThirtyCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "Gage", "Pigati", "9255551234", null));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID", "Gage", "Pigati", "9255551234", "a".repeat(31)));
        assertDoesNotThrow(() -> new Contact("ID", "Gage", "Pigati", "9255551234", "a".repeat(30)));
    }

    @Test
    void settersRejectInvalidValuesWithoutReplacingPreviousValues() {
        Contact contact = valid();
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("a".repeat(11)));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("invalid"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertEquals("Gage", contact.getFirstName());
        assertEquals("Pigati", contact.getLastName());
        assertEquals("9255551234", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
        contact.setFirstName("Alex");
        contact.setLastName("Smith");
        contact.setPhone("1234567890");
        contact.setAddress("New address");
        assertEquals("Alex", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("New address", contact.getAddress());
    }
}

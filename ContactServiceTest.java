import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {
    private Contact contact(String id) {
        return new Contact(id, "Gage", "Pigati", "9255551234", "Home");
    }

    @Test
    void addAndRetrieveContact() {
        ContactService service = new ContactService();
        Contact first = contact("ID1");
        service.addContact(first);
        assertSame(first, service.getContact("ID1"));
        assertNull(service.getContact("MISSING"));
    }

    @Test
    void cannotAddDuplicateOrNullContact() {
        ContactService service = new ContactService();
        service.addContact(contact("ID1"));
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact("ID1")));
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
        assertEquals("Gage", service.getContact("ID1").getFirstName());
    }

    @Test
    void deleteContactById() {
        ContactService service = new ContactService();
        service.addContact(contact("ID1"));
        service.deleteContact("ID1");
        assertNull(service.getContact("ID1"));
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("ID1"));
    }

    @Test
    void updateAllAllowedFieldsButKeepId() {
        ContactService service = new ContactService();
        service.addContact(contact("ID1"));
        service.updateContact("ID1", "Alex", "Smith", "1234567890", "New address");
        Contact updated = service.getContact("ID1");
        assertEquals("ID1", updated.getContactId());
        assertEquals("Alex", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("1234567890", updated.getPhone());
        assertEquals("New address", updated.getAddress());
    }

    @Test
    void individualFieldUpdatesAndMissingIdAreHandled() {
        ContactService service = new ContactService();
        service.addContact(contact("ID1"));
        service.updateFirstName("ID1", "Alex");
        service.updateLastName("ID1", "Smith");
        service.updatePhone("ID1", "1234567890");
        service.updateAddress("ID1", "Updated");
        assertEquals("Updated", service.getContact("ID1").getAddress());
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("MISSING", "1234567890"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateContact("MISSING", "Alex", "Smith", "1234567890", "Updated"));
    }

    @Test
    void invalidBatchUpdateDoesNotPartiallyChangeStoredContact() {
        ContactService service = new ContactService();
        service.addContact(contact("ID1"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateContact("ID1", "Alex", "Smith", "bad phone", "New address"));
        Contact existing = service.getContact("ID1");
        assertEquals("Gage", existing.getFirstName());
        assertEquals("Pigati", existing.getLastName());
        assertEquals("9255551234", existing.getPhone());
        assertEquals("Home", existing.getAddress());
    }
}

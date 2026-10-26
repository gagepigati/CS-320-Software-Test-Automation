import java.util.HashMap;
import java.util.Map;

/** In-memory storage for contacts; IDs are unique and cannot be updated. */
public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("A contact with that ID already exists");
        }
        contacts.put(contact.getContactId(), contact);
    }

    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    public void deleteContact(String contactId) {
        if (contacts.remove(contactId) == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
    }

    public void updateContact(String contactId, String firstName,
                              String lastName, String phone, String address) {
        Contact existing = contacts.get(contactId);
        if (existing == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        // Validate everything before mutating existing contact, so a failed
        // update never leaves a partially changed record behind.
        Contact validated = new Contact(contactId, firstName, lastName, phone, address);
        existing.setFirstName(validated.getFirstName());
        existing.setLastName(validated.getLastName());
        existing.setPhone(validated.getPhone());
        existing.setAddress(validated.getAddress());
    }

    public void updateFirstName(String contactId, String firstName) {
        requireContact(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        requireContact(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        requireContact(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        requireContact(contactId).setAddress(address);
    }

    private Contact requireContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        return contact;
    }
}

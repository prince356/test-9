package com.example.addressbook.service;

import com.example.addressbook.dto.AddressBookDTO;
import com.example.addressbook.model.AddressBookEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AddressBookService {

    private final List<AddressBookEntry> addressBookEntries = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<AddressBookEntry> getAllContacts() {
        return addressBookEntries;
    }

    public AddressBookEntry getContactById(Long id) {
        return addressBookEntries.stream()
                .filter(entry -> entry.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public AddressBookEntry addContact(AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry();
        entry.setId(idGenerator.getAndIncrement());
        entry.setName(dto.getName());
        entry.setPhoneNumber(dto.getPhoneNumber());
        entry.setEmail(dto.getEmail());
        addressBookEntries.add(entry);
        return entry;
    }

    public AddressBookEntry updateContact(Long id, AddressBookDTO dto) {
        for (AddressBookEntry entry : addressBookEntries) {
            if (entry.getId().equals(id)) {
                entry.setName(dto.getName());
                entry.setPhoneNumber(dto.getPhoneNumber());
                entry.setEmail(dto.getEmail());
                return entry;
            }
        }
        return null;
    }

    public boolean deleteContact(Long id) {
        return addressBookEntries.removeIf(entry -> entry.getId().equals(id));
    }
}

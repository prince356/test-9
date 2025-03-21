package com.example.addressbook.controller;

import com.example.addressbook.dto.AddressBookDTO;
import com.example.addressbook.model.AddressBookEntry;
import com.example.addressbook.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @GetMapping
    public ResponseEntity<List<AddressBookEntry>> getAllContacts() {
        return ResponseEntity.ok(addressBookService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntry> getContactById(@PathVariable Long id) {
        AddressBookEntry entry = addressBookService.getContactById(id);
        return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AddressBookEntry> addContact(@RequestBody AddressBookDTO dto) {
        return ResponseEntity.ok(addressBookService.addContact(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBookEntry> updateContact(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        AddressBookEntry updatedEntry = addressBookService.updateContact(id, dto);
        return updatedEntry != null ? ResponseEntity.ok(updatedEntry) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        return addressBookService.deleteContact(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

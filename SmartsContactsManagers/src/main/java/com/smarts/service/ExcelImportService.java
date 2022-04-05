package com.smarts.service;

import com.smarts.entity.Contact;
import com.smarts.helper.ExcelHelper;
import com.smarts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelImportService {

    @Autowired
    private ContactRepository contactRepository;

    public void save(MultipartFile file) {
        try {
            List<Contact> contactList = ExcelHelper.excelToTutorials(file.getInputStream());
            contactRepository.saveAll(contactList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
//
//    public ByteArrayInputStream load() {
//        List<Contact> contactList = contactRepository.findAll();
//
//        ByteArrayInputStream in = ExcelHelper.contactsToExcel(contactList);
//        return in;
//    }

    public List<Contact> getAllTutorials() {
        return contactRepository.findAll();
    }
}

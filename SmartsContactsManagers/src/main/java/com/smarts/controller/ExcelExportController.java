package com.smarts.controller;

import com.smarts.entity.Contact;
import com.smarts.excel.ContactExcelExporter;
import com.smarts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
public class ExcelExportController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=contact.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Contact> listContacts = contactRepository.findAll();

        ContactExcelExporter excelExporter = new ContactExcelExporter(listContacts);

        excelExporter.export(response);
    }
}

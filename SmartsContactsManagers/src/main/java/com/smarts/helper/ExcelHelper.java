package com.smarts.helper;

import com.smarts.entity.Contact;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"contactId", "firstName", "lastName", "emailAddress", "createdBy", "createdOn", "updatedBy", "updatedOn", "isActive"};
    static String SHEET = "Contacts";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static ByteArrayInputStream tutorialsToExcel(List<Contact> contacts) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Contact contact : contacts) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(contact.getContactId());
                row.createCell(1).setCellValue(contact.getCreatedBy());
                row.createCell(2).setCellValue(contact.getCreatedOn());
                row.createCell(3).setCellValue(contact.getEmailAddress());
                row.createCell(3).setCellValue(contact.getFirstName());
                row.createCell(3).setCellValue(contact.getIsActive());
                row.createCell(3).setCellValue(contact.getLastName());
                row.createCell(3).setCellValue(contact.getUpdatedBy());
                row.createCell(3).setCellValue(contact.getUpdatedOn());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Contact> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Contact> contactsList = new ArrayList<Contact>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Contact contact = new Contact();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            contact.setContactId((int) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            contact.setCreatedBy(currentCell.getStringCellValue());
                            break;

                        case 2:
                            contact.setCreatedOn(Date.valueOf(currentCell.getStringCellValue()));
                            break;

                        case 3:
                            contact.setEmailAddress(String.valueOf(currentCell.getBooleanCellValue()));
                            break;

                        case 4:
                            contact.setFirstName(String.valueOf(currentCell.getBooleanCellValue()));
                            break;

                        case 5:
                            contact.setIsActive(String.valueOf(currentCell.getBooleanCellValue()));
                            break;

                        case 6:
                            contact.setLastName(String.valueOf(currentCell.getBooleanCellValue()));
                            break;

                        case 7:
                            contact.setUpdatedBy(String.valueOf(currentCell.getBooleanCellValue()));
                            break;

                        case 8:
                            contact.setUpdatedOn(Date.valueOf(String.valueOf(currentCell.getBooleanCellValue())));
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                contactsList.add(contact);
            }

            workbook.close();

            return contactsList;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}

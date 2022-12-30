package ua.in.sz.home.power;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Persons");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Name");

        cell = row.createCell(1);
        cell.setCellValue("Age");

        FileOutputStream outputStream = new FileOutputStream("house-power/build/house-power.xlsx");
        workbook.write(outputStream);
        workbook.close();

        log.info("Hello world!");
    }
}
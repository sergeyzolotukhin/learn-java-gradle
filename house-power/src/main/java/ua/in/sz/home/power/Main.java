package ua.in.sz.home.power;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Main {
    private static final DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) throws IOException {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);

//        createWorkbook(start, end);
        print(start, end);
    }

    private static void print(LocalDateTime start, LocalDateTime end) {
        StringBuilder sb = new StringBuilder();
        for (LocalDateTime date = start; date.isBefore(end); date = date.plusMinutes(60)) {
            sb.append(HH_MM.format(date)).append(" | ");
        }
        log.info("{}", sb);

        for (int i = 0; i < 20; i++) {
            sb = new StringBuilder();
            sb.append("Row ").append(i).append(" | ");
            for (LocalDateTime date = start; date.isBefore(end); date = date.plusMinutes(60)) {
                sb.append(1).append(" | ");
            }
            log.info("{}", sb);
        }
    }

    private static void createWorkbook(LocalDateTime start, LocalDateTime end) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Schedule");

        int r = 0;
        // header
        {
            Row row = sheet.createRow(r++);

            int column = 0;
            row.createCell(column++);
            for (LocalDateTime date = start; date.isBefore(end); date = date.plusMinutes(60)) {
                Cell cell = row.createCell(column++);
                cell.setCellValue(HH_MM.format(date));
            }
        }

        // data
        for (int i = 0; i < 20; i++) {
            Row row = sheet.createRow(r++);

            int column = 0;
            Cell name = row.createCell(column++);
            name.setCellValue("Row " + i);
            for (LocalDateTime date = start; date.isBefore(end); date = date.plusMinutes(60)) {
                Cell cell = row.createCell(column++);
                cell.setCellValue(1);
            }
        }

        FileOutputStream outputStream = new FileOutputStream("house-power/build/house-power.xlsx");
        workbook.write(outputStream);
        workbook.close();
    }
}
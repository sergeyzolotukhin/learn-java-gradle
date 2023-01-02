package ua.in.sz.home.power;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

// https://javadevblog.com/polnoe-rukovodstvo-po-java-8-date-time-api-primery-localdate-instant-localdatetime-parse-i-format.html
// https://jcp.org/aboutJava/communityprocess/pfd/jsr310/JSR-310-guide.html
// https://www.baeldung.com/java-zoneddatetime-offsetdatetime
@Slf4j
public class Main {
    private static final DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DD_MM_YY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) throws IOException {
        ZonedDateTime start = ZonedDateTime.of(2023, 3, 26,
                0,  0, 0, 0,
                TimeZone.getDefault().toZoneId());
        ZonedDateTime end = start.plusDays(1);

//        LocalDateTime start = LocalDate.of(2023, 3, 26).atStartOfDay();
//        LocalDateTime end = start.plusDays(1);

        log.info("[{}]", TimeZone.getDefault().getID());;
//        createWorkbook(start, end);
        print(start, end);
    }

    private static void print(ZonedDateTime start, ZonedDateTime end) {
        StringBuilder sb = new StringBuilder();
        sb.append(DD_MM_YY.format(start)).append(" | ");
        for (ZonedDateTime date = start; date.isBefore(end); date = date.plusMinutes(60)) {
//            sb.append(date).append(" | ");
            sb.append(HH_MM.format(date)).append(" | ");
        }
        log.info("{}", sb);

        for (int i = 0; i < 0; i++) {
            sb = new StringBuilder();
            sb.append("Row ").append(i).append(" | ");
            for (ZonedDateTime date = start; date.isBefore(end); date = date.plusMinutes(Duration.ofHours(1).toMinutes())) {
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
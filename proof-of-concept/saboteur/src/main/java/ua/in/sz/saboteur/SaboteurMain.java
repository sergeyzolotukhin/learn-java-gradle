package ua.in.sz.saboteur;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Slf4j
public class SaboteurMain {
    @SneakyThrows
    public static void main(String[] args) {
        Path path = Path.of("d:/projects-java/_learn-java-gradle/proof-of-concept/saboteur/data/saboteur2_128k.trd");
        byte[] bytes = Files.readAllBytes(path);

        for (int nameIndex = 0; nameIndex < (8 * 256); nameIndex += 16) {
            String filename = new String(Arrays.copyOfRange(bytes, nameIndex, nameIndex + 8), StandardCharsets.UTF_8);
            String type = new String(Arrays.copyOfRange(bytes, nameIndex + 8, nameIndex + 9), StandardCharsets.UTF_8);

            if ("B".equals(type)) {
                int fileSize = ((bytes[nameIndex + 10] & 0xff) << 8) | (bytes[nameIndex + 9] & 0xff);
                int variablesAreaStart = ((bytes[nameIndex + 12] & 0xff) << 8) | (bytes[nameIndex + 11] & 0xff);

                int startSector = bytes[nameIndex + 14] & 0xff;
                int sectors = bytes[nameIndex + 13] & 0xff;
                int track = bytes[nameIndex + 15] & 0xff;
                log.info("Name: [{}.{}] | {} | {} | sector from: {} | {} | track {}", filename, type, fileSize, variablesAreaStart,
                        startSector, sectors, track);

            } else if ("C".equals(type)) {
                int loadAddress = ((bytes[nameIndex + 10] & 0xff) << 8) | (bytes[nameIndex + 9] & 0xff);
                int fileSize = ((bytes[nameIndex + 12] & 0xff) << 8) | (bytes[nameIndex + 11] & 0xff);

                int startSector = bytes[nameIndex + 13] & 0xff;
                int sectors = bytes[nameIndex + 14] & 0xff;
                int track = bytes[nameIndex + 15] & 0xff;

                log.info("Name: [{}.{}] | address {} | {} | sector from: {} | {} | track {}", filename, type,
                        loadAddress, fileSize, sectors, startSector, track);

                if ("s2scr".equals(filename.trim())) {
                    int s = 16 * 256 + 256 * sectors;
                    int e = s + 256 * startSector;

                    byte[] bytes1 = Arrays.copyOfRange(bytes, s, e);
                    try (FileOutputStream fos = new FileOutputStream("d:/projects-java/_learn-java-gradle/proof-of-concept/saboteur/data/s2scr.tmp")) {
                        fos.write(bytes1);
                        //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
                    }
                }

            } else {
                log.trace("Type: [{}], {}", type, nameIndex);
            }

        }

    }
}

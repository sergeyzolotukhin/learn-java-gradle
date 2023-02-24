package ua.in.sz.business.days;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        Dto source = new Dto("Name 1");
        source.setDescription("Description 1");

        byte[] buf;

        try (
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(buffer)
        ) {
            out.writeObject(source);
            buf = buffer.toByteArray();
        }

        Dto target;
        try (
                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf))
        ) {
            target = (Dto) in.readObject();
        }

        log.info("DTO source: {}", source);
        log.info("DTO target: {}", target);
    }

    @Setter
    @Getter
    private static class Dto implements Serializable {
        private String name;
        private transient String description;

        public Dto(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Dto{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Serial
        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeUTF(name + " from serialization");
        }

        @Serial
        private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
            name = in.readUTF();
            description = "Description from serialization";
        }
    }
}

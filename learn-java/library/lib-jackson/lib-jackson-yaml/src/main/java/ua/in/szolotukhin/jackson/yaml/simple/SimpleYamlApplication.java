package ua.in.szolotukhin.jackson.yaml.simple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.yaml.simple.model.Order;
import ua.in.szolotukhin.jackson.yaml.simple.model.OrderLine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SimpleYamlApplication {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = createMapper();

        List<OrderLine> lines = new ArrayList<>();
        lines.add(new OrderLine("Copper Wire (200ft)", 1, new BigDecimal("50.67")));
        lines.add(new OrderLine("Washers (1/4)", 24, new BigDecimal(".15")));
        Order order = new Order(
                "B-9910",
                LocalDate.parse("2019-04-18", DateTimeFormatter.ISO_DATE),
                "Customer, Jane",
                lines);
        mapper.writeValue(System.out, order);
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory()
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        mapper.findAndRegisterModules();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}


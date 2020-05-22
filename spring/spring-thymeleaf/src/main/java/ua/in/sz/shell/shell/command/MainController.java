package ua.in.sz.shell.shell.command;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.size;

@Slf4j
@Controller
public class MainController {
    @Value("classpath:resource.csv")
    private Resource resourceCsv;
    @Value("classpath:resource.json")
    private Resource resourceJson;

    @GetMapping("/index")
    public String greeting(Model model) {
        List<ResourceDto> resources = loadResourcesFromJson();

        model.addAttribute("resources", resources);

        return "index";
    }

    private List<ResourceDto> loadResourcesFromCsv() {
        try (Reader reader = new BufferedReader(new InputStreamReader(resourceCsv.getInputStream()))) {
            List<ResourceDto> resources = new CsvToBeanBuilder<ResourceDto>(reader)
                    .withType(ResourceDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            log.debug("Resources loaded. count {}", size(resources));

            return resources;
        } catch (Exception ex) {
            log.error("Can't load resources", ex);

            return Collections.emptyList();
        }
    }

    private List<ResourceDto> loadResourcesFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (JsonParser parser = mapper.createParser(resourceJson.getInputStream())) {
            List<ResourceDto> resources = Lists.newArrayList(mapper.readValue(parser, ResourceDto[].class));
            log.debug("Resources loaded. count {}", size(resources));
            return resources;
        } catch (Exception ex) {
            log.error("Can't load resources", ex);

            return Collections.emptyList();
        }
    }
}
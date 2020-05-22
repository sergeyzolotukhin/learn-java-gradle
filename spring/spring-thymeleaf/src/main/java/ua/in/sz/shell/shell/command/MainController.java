package ua.in.sz.shell.shell.command;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.size;

@Slf4j
@Controller
public class MainController {
    @Value("classpath:resource.json")
    private Resource resourceJson;

    @GetMapping("/index")
    public String greeting(Model model) {
        List<ResourceDto> resources = loadResourcesFromJson();

        model.addAttribute("resources", resources);

        return "index";
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
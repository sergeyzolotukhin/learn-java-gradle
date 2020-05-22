package ua.in.sz.factorio.ui;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.in.sz.factorio.domain.Material;
import ua.in.sz.factorio.domain.RequiredMaterial;

import java.util.ArrayList;
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
        List<Material> materials = loadResourcesFromJson();

        List<MaterialDto> result = new ArrayList<>();
        for (Material material : materials) {

            List<MaterialDto.RequiredDto> requiredDtos = new ArrayList<>();
            for (RequiredMaterial required : material.getRequired()) {
                String icon = materials.stream().filter(m -> required.getId().equals(m.getId())).map(Material::getIcon).findFirst().orElse("");

                requiredDtos.add(MaterialDto.RequiredDto.builder()
                        .icon(icon)
                        .amount(required.getAmount())
                        .build());
            }

            result.add(MaterialDto.builder()
                    .id(material.getId())
                    .icon(material.getIcon())
                    .time(material.getTime())
                    .required(requiredDtos)
                    .build());
        }

        model.addAttribute("materials", result);

        return "index";
    }

    private List<Material> loadResourcesFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (JsonParser parser = mapper.createParser(resourceJson.getInputStream())) {
            List<Material> materials = Lists.newArrayList(mapper.readValue(parser, Material[].class));
            log.debug("Resources loaded. count {}", size(materials));
            return materials;
        } catch (Exception ex) {
            log.error("Can't load resources", ex);

            return Collections.emptyList();
        }
    }
}
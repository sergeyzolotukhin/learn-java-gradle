package ua.in.sz.factorio.ui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.in.sz.factorio.domain.Material;
import ua.in.sz.factorio.store.MaterialDao;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private MaterialDao materialDao;

    @GetMapping("/")
    public String greeting(Model model) {
        List<Material> materials = materialDao.findAll();

        List<MaterialDto> result = materials.stream()
                .map(material -> MaterialDto.builder()
                        .id(material.getId())
                        .icon(material.getIcon())
                        .time(material.getTime())
                        .required(material.getRequired().stream()
                                .map(required -> MaterialDto.RequiredDto.builder()
                                        .icon(findMaterialIcon(materials, required.getId()))
                                        .amount(required.getAmount())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());


        model.addAttribute("materials", result);

        return "index";
    }

    private String findMaterialIcon(List<Material> materials, String id) {
        return materials.stream()
                .filter(m -> id.equals(m.getId()))
                .map(Material::getIcon)
                .findFirst()
                .orElse(null);
    }
}
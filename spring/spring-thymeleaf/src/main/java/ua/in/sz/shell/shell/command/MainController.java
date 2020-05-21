package ua.in.sz.shell.shell.command;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
    private Resource resource;

    @GetMapping("/index")
    public String greeting(Model model) {
        log.info("Get faster");

        try (Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            CsvToBean<ResourceDto> csvToBean = new CsvToBeanBuilder<ResourceDto>(reader)
                    .withType(ResourceDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<ResourceDto> result = csvToBean.parse();

            log.info("Resources loaded. count {}", size(result));

            model.addAttribute("resources", result);
        } catch (Exception ex) {
            model.addAttribute("resources", Collections.<ResourceDto>emptyList());
        }

        return "index";
    }
}
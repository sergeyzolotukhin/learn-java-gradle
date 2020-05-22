package ua.in.sz.factorio.store;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ua.in.sz.factorio.domain.Material;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.size;

@Slf4j
@Repository
public class MaterialDao {
    @Value("classpath:resource.json")
    private Resource resourceJson;

    public List<Material> findAll() {
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

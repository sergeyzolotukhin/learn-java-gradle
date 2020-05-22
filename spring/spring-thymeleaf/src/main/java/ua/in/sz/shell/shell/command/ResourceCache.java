package ua.in.sz.shell.shell.command;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResourceCache {
    private Map<String, ResourceDto> resourceCache;

    public ResourceCache(List<ResourceDto> resources) {
        resourceCache = resources.stream()
                .collect(Collectors.toMap(ResourceDto::getId, Function.identity()));
    }

    public String getIcon(String resourceId) {
        return resourceCache.containsKey(resourceId) ? resourceCache.get(resourceId).getIcon() : "";
    }
}

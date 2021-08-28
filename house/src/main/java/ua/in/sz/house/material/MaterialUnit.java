package ua.in.sz.house.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MaterialUnit {
    KG("kg"),
    PIECE("piece"),
    M3("m3"),
    ;

    private final String name;
}

package ua.in.sz.house.material;

public enum MaterialCode implements HasMaterialCode {
    BRICK,
    CEMENT,
    CEMENT_MORTAR,
    SANG;

    @Override
    public String code() {
        return name();
    }
}

package ua.in.sz.house.material;

public class MaterialReport {
    public static String report(Material material) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nMaterials:");

        for (Material component : material.allComponents()) {
            sb.append(String.format("\n\t%-15.15s quantity %7.0f %7.7s",
                    component.getMaterialType().getName(), component.getQuantity(), component.getUnit().getName()));
        }

        return sb.toString();
    }
}

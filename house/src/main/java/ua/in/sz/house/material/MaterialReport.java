package ua.in.sz.house.material;

import org.apache.commons.lang3.StringUtils;

public class MaterialReport {
    public static String report(Material material) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nMaterials:");
        sb.append(String.format("\n\t%-15.15s %7.7s %7.7s", "name", "quantity", "unit"));
        sb.append(StringUtils.rightPad("\n\t=", 33, "="));

        for (Material component : material.allComponents()) {
            sb.append(String.format("\n\t%-15.15s %7.0f %7.7s",
                    component.getMaterialType().getName(), component.getQuantity(), component.getUnit().getName()));
        }

        return sb.toString();
    }
}

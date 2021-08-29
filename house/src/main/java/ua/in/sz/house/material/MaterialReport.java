package ua.in.sz.house.material;

import org.apache.commons.lang3.StringUtils;

public class MaterialReport {
    public static String report(Material material) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\tMaterials");

        String title = String.format("\n\t%-15.15s %7.7s %7.7s %10.10s", "name", "quantity", "unit", "weight kg");
        int size = title.length();

        sb.append(StringUtils.rightPad("\n\t=", size, "="));
        sb.append(title);
        sb.append(StringUtils.rightPad("\n\t=", size, "="));

        double totalWeight = 0;
        for (Material component : material.allComponents()) {
            sb.append(String.format("\n\t%-15.15s %7.0f %7.7s %10.0f",
                    component.getMaterialType().getName(),
                    Math.ceil(component.getQuantity()),
                    component.getUnit().getName(),
                    Math.ceil(component.getWeight())));

            totalWeight += component.getWeight();
        }

        sb.append(StringUtils.rightPad("\n\t=", size, "="));
        sb.append(String.format("\n\t%-15.15s %7.7s %7.7s %10.0f",
                StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Math.ceil(totalWeight)));

        return sb.toString();
    }
}

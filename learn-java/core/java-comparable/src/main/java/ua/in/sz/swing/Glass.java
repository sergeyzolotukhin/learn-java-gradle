package ua.in.sz.swing;

public class Glass implements Comparable<Glass> {
    private String material;
    private Size size;

    public Glass(Size size, String material) {
        this.size = size;
        this.material = material;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((material == null) ? 0 : material.hashCode());
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Glass other = (Glass) obj;
        if (material == null) {
            if (other.material != null)
                return false;
        } else if (!material.equals(other.material))
            return false;
        if (size != other.size)
            return false;
        return true;
    }

    @Override
    public int compareTo(Glass o) {
        if (this.size.getSize() == o.size.getSize()) {
            return 0;
        } else if (this.size.getSize() > o.size.getSize()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Glass [material=" + material + ", size=" + size + "]";
    }

    // ================================================================================================================

    public enum Size {
        BIG(3), MEDIUM(2), SMALL(1);
        private int size;

        Size(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}


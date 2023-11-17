public class Software {
    private String name;
    private String version;
    private int quantity;
    private int price;
    
    public Software(String name, String version, int quantity, int price) {
        this.name = name;
        this.version = version;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return String.format("%-25s %-10s %-10s %-10s", name, version, quantity, price);
    }

    public int compareTo(Software software) {
        return this.name.compareTo(software.name);
    }
}

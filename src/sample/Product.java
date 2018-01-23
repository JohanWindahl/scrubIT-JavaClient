package sample;

public class Product {
    String Name;
    Integer Stock;
    Integer Price;

    public Product(String n, Integer s, Integer p) {
        Name = n;
        Stock = s;
        Price = p;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Name='" + Name + '\'' +
                ", Stock=" + Stock +
                ", Price=" + Price +
                '}';
    }

    public static void main(String[] args) {

        Product cola = new Product("Cola",120,12);
        Product bebzi = new Product("Pepsi Max",99,12);

        System.out.println(cola);
        System.out.println(bebzi);

        System.out.println(cola.getName());
        System.out.println(bebzi.getName());

        System.out.println(cola.getStock());
        System.out.println(bebzi.getStock());

        System.out.println(cola.getPrice());
        System.out.println(bebzi.getPrice());


        cola.setName("Cola Zero");
        cola.setPrice(9);
        cola.setStock(0);

        bebzi.setName("Pepsi");
        bebzi.setPrice(13);
        bebzi.setStock(0);

        System.out.println(cola);
        System.out.println(bebzi);

    }


}



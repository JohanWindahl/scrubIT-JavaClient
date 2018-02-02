package sample;

public class Product {
    Integer Id;
    String Name;
    Integer Stock;
    Integer Price;

    public Product(Integer i, String n, Integer s, Integer p) {
        this.Id = i;
        this.Name = n;
        this.Stock = s;
        this.Price = p;
    }

    public Integer getId() {return Id;}

    public void setId(Integer id) {Id = id;}

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
                "Id='" + Id +
                "Name='" + Name + '\'' +
                ", Stock=" + Stock +
                ", Price=" + Price +
                '}';
    }

    public static void main(String[] args) {

        Product cola = new Product(1,"Cola",120,12);
        Product bebzi = new Product(2,"Pepsi Max",99,12);

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



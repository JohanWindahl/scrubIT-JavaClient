package sample;

public class Product {
    String Id;
    String Name;
    String Stock;
    String Price;

    public Product(String i, String n, String s, String p) {
        this.Id = i;
        this.Name = n;
        this.Stock = s;
        this.Price = p;
    }

    public String getId() {return Id;}

    public void setId(String id) {Id = id;}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
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

        Product cola = new Product("1","Cola","120","12");
        Product bebzi = new Product("2","Pepsi Max","99","12");

        System.out.println(cola);
        System.out.println(bebzi);

        System.out.println(cola.getName());
        System.out.println(bebzi.getName());

        System.out.println(cola.getStock());
        System.out.println(bebzi.getStock());

        System.out.println(cola.getPrice());
        System.out.println(bebzi.getPrice());


        cola.setName("Cola Zero");
        cola.setPrice("2");
        cola.setStock("3");

        bebzi.setName("Pepsi");
        bebzi.setPrice("13");
        bebzi.setStock("2");

        System.out.println(cola);
        System.out.println(bebzi);
    }


}



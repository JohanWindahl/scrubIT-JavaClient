package sample;

public class Product {
    String Id;
    String Name;
    String Quantity;
    String Price;
    String QR;
    String Url;
    String Desc;

    Product(String i, String n, String s, String p, String q, String u, String d) {
        this.Id = i;
        this.Name = n;
        this.Quantity = s;
        this.Price = p;
        this.QR = q;
        this.Url = u;
        this.Desc = d;
    }

    Product(String n, String s, String p, String q, String u, String d) {
        this.Name = n;
        this.Quantity = s;
        this.Price = p;
        this.QR = q;
        this.Url = u;
        this.Desc = d;
    }


    public String getQr() {
        return QR;
    }

    public void setQr(String qr) {
        this.QR = qr;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        this.Desc = desc;
    }

    public String getId() {return Id;}

    public void setId(String id) {Id = id;}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
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
                ", Quantity=" + Quantity +
                ", Price=" + Price +
                ", QR=" + QR +
                ", url=" + Desc +
                ", Desc=" + Url +
                '}';
    }

    public static void main(String[] args) {

        Product cola = new Product("Cola","120","12", "123","can of coke","www.google.se");
        Product bebzi = new Product("Pepsi Max","99","12", "123","can of bebzi","www.google.se");

        System.out.println(cola);
        System.out.println(bebzi);

        System.out.println(cola.getName());
        System.out.println(bebzi.getName());

        System.out.println(cola.getQuantity());
        System.out.println(bebzi.getQuantity());

        System.out.println(cola.getPrice());
        System.out.println(bebzi.getPrice());


        cola.setName("Cola Zero");
        cola.setPrice("2");
        cola.setQuantity("3");

        bebzi.setName("Pepsi");
        bebzi.setPrice("13");
        bebzi.setQuantity("2");

        System.out.println(cola);
        System.out.println(bebzi);
    }


}



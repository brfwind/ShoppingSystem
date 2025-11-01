package product;

public class Product {
      private String name;
      private double price;
      private int stock;

      public Product(String name, double price, int stock){
            this.name = name;
            this.price = price;
            this.stock = stock;
      }

      public String getName() {
            return name;
      }

      public double getPrice() {
            return price;
      }

      public int getStock() {
            return stock;
      }

      public void setStock(int stock) {
            this.stock = stock;
      }

      public void setPrice(double price) {
            this.price = price;
      }

      public void reduceStock(int num){
            if(stock >= num){
                  stock -= num;
            }else{
                  System.out.println("库存不足");
            }
      }

}

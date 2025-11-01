package product;

import java.util.Map;
import java.util.HashMap;

public class ProductManager {
      private static Map<String, Product> productDatabase = new HashMap<>();

      public static void addProduct(Product product){
            productDatabase.put(product.getName(), product);
      }

      public static Map<String, Product> getAvailableProducts() {
            return productDatabase;
      }
}

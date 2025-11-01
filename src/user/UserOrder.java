package user;

import product.Product;

import java.util.HashMap;
import java.util.Map;

public class UserOrder {
      private  Map<Product, Integer> cart;

      public UserOrder(){
            this.cart = new HashMap<>();
      }

      public  void addProduct(Product product, int num){
            cart.put(product, cart.getOrDefault(product, 0) + num);
      }

      public void displayOrder() {
            if (cart.isEmpty()) {
                  System.out.println("您的购物车为空！");
            } else {
                  //Map.Entry 接口，它代表了 Map 中的一个键值对（Key-Value）
                  //.entrySet() 是 Java 中 Map 接口的一个方法，它返回一个 Set<Map.Entry<K, V>> 集合
                  for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                        //得到键
                        Product product = entry.getKey();
                        //得到值
                        int num = entry.getValue();
                        System.out.println(product.getName() + "\n价格：" + product.getPrice() + "\n数量：" + num);
                  }
            }
      }

}

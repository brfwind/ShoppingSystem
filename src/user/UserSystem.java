package user;

import product.Product;
import product.ProductManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserSystem {
      private static String loggedInUser = null;
      public static Map<String, UserOrder> userOrder= new HashMap<>();

      public static void userRegister() {
            Scanner scanner = new Scanner(System.in);

            String username = getValidUsername(scanner);
            String password = getValidPassword(scanner);

            UserManager.addUser(username, password);
            System.out.println("注册成功！");
      }

      private static String getValidUsername(Scanner scanner) {
            String username;
            while (true) {
                  System.out.print("请输入用户名：");
                  username = scanner.nextLine();
                  if (username.length() >= 3) {
                        return username;
                  } else {
                        System.out.print("用户名长度不能小于3位！请重新输入\n");
                  }
            }
      }

      private static String getValidPassword(Scanner scanner) {
            String password;
            while (true) {
                  System.out.print("请输入密码：");
                  password = scanner.nextLine();
                  if (password.length() >= 6 && (password.matches(".*[a-zA-Z].*")) && password.matches(".*\\d.*")) {
                        return password;
                  } else {
                        System.out.print("密码不符合要求，请重新输入：");
                  }
            }
      }


      public static void userLogin(){
            Scanner scanner = new Scanner(System.in);

            boolean judge = true;
            do {
                  if(loggedInUser != null){
                        System.out.println("您已登录，" + loggedInUser);
                        return;
                  }
                  System.out.print("请输入用户名：");
                  String username = scanner.nextLine();
                  System.out.print("请输入密码：");
                  String password = scanner.nextLine();

                  if (UserManager.userExists(username) && UserManager.getPassword(username).equals(password)) {
                        System.out.println("登陆成功！欢迎回来，" + username);
                        loggedInUser = username;
                        //若该用户第一次买，给一个筐子装
                        if(!userOrder.containsKey(loggedInUser)){
                              userOrder.put(loggedInUser, new UserOrder());
                        }
                        return; // 登录成功后直接返回，结束方法
                  } else if(!UserManager.userExists(username)){
                        System.out.println("登录失败，没有该用户！");
                        return;
                  }else{
                        System.out.println("登陆失败，密码错误！");
                  }
            } while (true); // 一直循环直到成功登录
      }

      public static void viewAndShop(UserOrder cart, Scanner scanner){
            Map<String, Product> products = ProductManager.getAvailableProducts();
            if(products.isEmpty()){
                  System.out.println("商城暂无商品，稍后再来~");
                  return;
            }else{
                  System.out.println("以下是商品列表：");
                  int index = 1;
                  for(Product product : products.values()){
                        System.out.println(index++ + ". 商品名称: " + product.getName() + ", 价格: " + product.getPrice() + "元, 库存: " + product.getStock());
                  }
            }

            System.out.print("请输入您要购买的商品编号：");
            int productChoice = scanner.nextInt();
            scanner.nextLine();  // 清除输入缓存

            if(productChoice < 1 || productChoice > products.size()){
                  System.out.println("输入无效！");
                  return;
            }

            String productChoiceName = (String)products.keySet().toArray()[productChoice-1];
            Product choiceProduct = products.get(productChoiceName);

            System.out.print("请输入您要购买的商品数量：");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (quantity <= choiceProduct.getStock()) {
                  choiceProduct.reduceStock(quantity);  // 减少库存
                  cart.addProduct(choiceProduct, quantity);  // 将商品加入购物车
                  System.out.println("已将 " + productChoiceName + " 添加到购物车！");
            } else {
                  System.out.println("库存不足，无法购买该数量的商品！");
                  return;
            }
      }

      public static void viewPay(){
            if(userOrder.containsKey(loggedInUser)){
                  UserOrder cart = userOrder.get(loggedInUser);
                  cart.displayOrder();
            }else{
                  System.out.println("您的购物车为空！");
            }
      }

      public static boolean userLogged(){
            if(loggedInUser != null)
                  return true;
            else return false;
      }

      public static String getLoggedInUser(){
            return loggedInUser;
      }
}

package admin;

import product.Product;
import product.ProductManager;

import java.util.Map;
import java.util.Scanner;

public class AdminSystem {
      public static boolean adminLogged(){
            Scanner scanner = new Scanner(System.in);
            boolean judge = true;

            while(judge){
                  System.out.print("请输入管理员用户名：");
                  String adminname = scanner.nextLine();
                  System.out.print("请输入管理员密码：");
                  String password = scanner.nextLine();

                  //java里，==比较引用，.equals比较内容
                  if(adminname.equals("admin") && password.equals("admin")){
                        judge = false;
                  }else{
                        System.out.println("请重新输入管理员用户名及密码！");
                  }
            }
            return !judge;
      }

      public static void manageProducts(Scanner scanner){
            int choice;
            do {
                  System.out.println("********************");
                  System.out.println("1. 添加商品");
                  System.out.println("2. 查看所有商品");
                  System.out.println("3. 退出登录");
                  System.out.println("********************");
                  System.out.print("请输入您的选择：");

                  choice = scanner.nextInt();
                  //scanner的nextInt和nextDouble只会读取数字部分，输入数字后的换行符Enter会存入缓存区，被下一个nextLine误读取
                  scanner.nextLine();  // 清除输入缓存

                  switch (choice) {
                        case 1:
                              boolean judge = true;
                              do {
                                    addProduct(scanner);
                                    System.out.println("是否继续添加商品");
                                    System.out.println("1.是" + "   " + "2.否");
                                    int choice2 = scanner.nextInt();
                                    scanner.nextLine();// 清除输入缓存

                                    switch (choice2) {
                                          case 1:
                                                break;
                                          case 2:
                                                judge = false;
                                                break;
                                          default:
                                                System.out.println("输入无效，重新输入！");
                                    }
                              }while (judge) ;
                              break;
                        case 2:
                              viewAllProducts();
                              break;
                        case 3:
                              System.out.println("已退出登录！");
                              break;
                        default:
                              System.out.println("无效输入，请重新输入！");
                  }
            } while (choice != 3);
      }

      private static void addProduct(Scanner scanner){
            System.out.print("请输入商品名称：");
            String name = scanner.nextLine();

            double price;
            do {
                  System.out.print("请输入商品价格：");
                  price = scanner.nextDouble();
                  scanner.nextLine();  // 清除输入缓存
                  if (price <= 0) {
                        System.out.println("价格必须大于0，请重新输入");
                  }
            } while (price <= 0);


            int stock;
            do {
                  System.out.print("请输入商品库存：");
                  stock = scanner.nextInt();
                  scanner.nextLine();  // 清除输入缓存
                  if (stock <= 0) {
                        System.out.println("库存必须大于0，请重新输入。");
                  }
            } while (stock <= 0);

            Product product = new Product(name, price, stock);
            ProductManager.addProduct(product);
            System.out.println("商品添加成功！");
      }

      private static void viewAllProducts(){
            Map<String, Product> products = ProductManager.getAvailableProducts();
            if (products.isEmpty()) {
                  System.out.println("当前没有商品！");
            } else {
                  for (String name : products.keySet()) {
                        Product product = products.get(name);
                        System.out.println("商品名称: " + product.getName() + ", 价格: " + product.getPrice() + "元, 库存: " + product.getStock());
                  }
            }
      }

      public static void removeProduct(Scanner scanner){

      }
}

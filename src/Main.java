import admin.AdminSystem;
import user.UserOrder;
import user.UserSystem;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int choice;

                while(true){
                        System.out.println("*****欢迎进入电子商城******");
                        System.out.println("1.注册");
                        System.out.println("2.登录");
                        System.out.println("3.查看商城");
                        System.out.println("4.查看我购买的商品");
                        System.out.println("5.管理员登陆");
                        System.out.println("6.退出系统");
                        System.out.println("****************************");

                        choice = scanner.nextInt();

                        switch(choice){
                                case 1:
                                        UserSystem.userRegister();
                                        System.out.println("注册成功");
                                        break;
                                case 2:
                                        UserSystem.userLogin();
                                        break;
                                case 3:
                                        if(UserSystem.userLogged()){
                                                UserOrder cart = UserSystem.userOrder.get(UserSystem.getLoggedInUser());
                                                UserSystem.viewAndShop(cart, scanner);
                                        }else{
                                                System.out.println("用户未登录！");
                                        }
                                        break;
                                case 4:
                                        if(UserSystem.userLogged()){
                                                UserSystem.viewPay();
                                        }else{
                                                System.out.println("用户未登录！");
                                        }
                                        break;
                                case 5:
                                        if(AdminSystem.adminLogged()){
                                                AdminSystem.manageProducts(scanner);
                                        }
                                        break;
                                case 6:
                                        System.out.println("感谢使用，程序已退出");
                                        return;
                                default:
                                        System.out.println("输入无效，重新输入！");
                        }
                }
        }

        //1.用户购买功能
        //2.商品数为0，管理员端可见，用户端不可见
        //3.管理员可删除商品
}

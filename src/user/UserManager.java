package user;

import java.util.Map;
import java.util.HashMap;

public class UserManager {
      private static Map<String, User> userDatabase = new HashMap<>();

      //添加用户
      public static void addUser(String username, String password){
            User newUser = new User(username, password);
            userDatabase.put(username, newUser);
      }

      public static boolean userExists(String username){
            return userDatabase.containsKey(username);
      }

      public static String getPassword(String username){
            return userDatabase.get(username).getPassword();
      }
}

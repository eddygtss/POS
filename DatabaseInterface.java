package POS;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface DatabaseInterface {
    String dataBase = "src/POS/userDB/users.txt";

    Map<String, Employee> userDB = new LinkedHashMap<>();
    Map<Boolean, String> userLogged = new LinkedHashMap<>();

    List<Foods> foodItems = new LinkedList<>();
    List<Drinks> drinkItems = new LinkedList<>();

    String[] tempUser = new String[1];

}

package POS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PointOfSale extends Application implements DatabaseInterface {


    public static void main(String[] args) throws Exception {

        // We read the users database text file to be able to login.
        Scanner users = new Scanner(new File(dataBase));
        String newName, newPWD, newID, newAccess;

        while (users.hasNext()){
            users.nextLine();
            newName = users.nextLine();
            newPWD = users.nextLine();
            newID = users.nextLine();
            newAccess = users.nextLine();

            // Ingesting all the employees from the database text file.
            Employee employee = new Employee(newName,newPWD,newID,newAccess);

            // This adds the employee object to a Map so it's easier to lookup when logging in.
            userDB.put(employee.getUserID(),employee);
        }
        users.close();

        // Adding a few items to both food and drink lists.
        Foods cheeseBurger = new Foods("Cheeseburger","101",8.99);
        Foods chickenSandwich = new Foods("Chicken Sandwich","102",9.99);

        foodItems.add(cheeseBurger);
        foodItems.add(chickenSandwich);

        Drinks lemonade = new Drinks("Lemonade",false,"Non-Alcoholic",2.99);
        Drinks whiskey = new Drinks("Jack Daniels Shot",true,"40%",4.50);

        drinkItems.add(lemonade);
        drinkItems.add(whiskey);

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/MainPortal.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Eddy's Tavern POS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

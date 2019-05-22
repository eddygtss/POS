package POS;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;
import java.net.URL;
import java.util.*;

public class POScontroller implements Initializable,DatabaseInterface {

    // These are all variables that are used in the FXML.
    @FXML
    private VBox rootPane;
    public TextField name;
    public PasswordField passwordField;
    public TextField userID;
    public TextField accessLvl;
    public TextArea employeeArea;
    public Button submit;
    public Button cancelBtn;
    public Button employeeListBtn;
    public Button logon;
    public Label errorMSG;
    public Label currentUser;
    private Font messageFont = new Font("Arial", 15);
    private String mainPortal = "FXML/MainPortal.fxml";
    private String logOut = "FXML/Logout.fxml";
    private String managersPortal = "FXML/ManagersPortal.fxml";
    private String newHire = "FXML/NewHire.fxml";
    private String onlineOrders = "FXML/OnlineOrders.fxml";
    private String restrictedAccess = "FXML/Restricted.fxml";
    private String storeOrders = "FXML/StoreOrders.fxml";




    // This method appends a new user to the user database text file.
    private static void newUserSave(String typeAcc, String name, String userPWD, String uID, String position){
        try {
            BufferedWriter save = new BufferedWriter(new FileWriter(dataBase,true));
            save.newLine();
            save.write(typeAcc);
            save.newLine();
            save.write(name);
            save.newLine();
            save.write(userPWD);
            save.newLine();
            save.write(uID);
            save.newLine();
            save.write(position);
            save.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while creating new account!");
        }

        try {
            Scanner users = new Scanner(new File(dataBase));
            String newName, newPWD, newID, newAccess;

            while (users.hasNext()) {
                users.nextLine();
                newName = users.nextLine();
                newPWD = users.nextLine();
                newID = users.nextLine();
                newAccess = users.nextLine();

                // Ingesting all the new employees we made.
                Employee employee = new Employee(newName, newPWD, newID, newAccess);

                // This adds the employee object to a Map so it's easier to lookup when logging in.
                if (!userDB.containsKey(employee.getUserID())) {
                    userDB.put(employee.getUserID(), employee);
                }
            }
            users.close();
        } catch (FileNotFoundException e){
            System.out.println("The database did not ingest properly, please check the database text file.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        if(userLogged.containsKey(true)) {
            currentUser.setFont(Font.font("Arial", 10));
            currentUser.setTextFill(Color.BLUE);
            currentUser.setText("Logged in as: " + userLogged.get(true));
        } else {
            currentUser.setText("Logged out");
        }
    }

    @FXML
    private void loginUser() throws IOException {
        tempUser[0] = name.getText();

        if (tempUser[0].equals("") && passwordField.getText().equals("") || tempUser[0].equals("") || passwordField.getText().equals("")){
            errorMSG.setFont(messageFont);
            errorMSG.setTextFill(Color.RED);
            errorMSG.setText("Please enter User ID and Password!");
        } else {

            // Checks if the name is present in the Map and checks the password with the value of the Map key(name).
            if (userDB.containsKey(tempUser[0]) && userDB.get(tempUser[0]).getUserPWD().equals(passwordField.getText())) {
                String loggedInUser = userDB.get(tempUser[0]).getName();
                errorMSG.setFont(messageFont);
                errorMSG.setTextFill(Color.BLUE);
                errorMSG.setText("Logged in successfully as " + loggedInUser);
                userLogged.put(true, userDB.get(tempUser[0]).getName());
            } else {
                errorMSG.setFont(messageFont);
                errorMSG.setTextFill(Color.RED);
                errorMSG.setText("The User ID # or Password is incorrect");
            }

            // Here we open the manager portal if it is a manager.
            if (userLogged.containsKey(true) && userDB.get(tempUser[0]).getAccessLevel().equals("GM") || userLogged.containsKey(true) && userDB.get(tempUser[0]).getAccessLevel().equals("TM")) {
                changeWindow(managersPortal);
            }
            if (userLogged.containsKey(true) && userDB.get(tempUser[0]).getAccessLevel().equals("AB") || userLogged.containsKey(true) && userDB.get(tempUser[0]).getAccessLevel().equals("FS")) {
                storeOrders();
            }
        }
    }

    @FXML
    private void storeOrders() throws IOException {
        if(userLogged.containsKey(true)) {
            changeWindow(storeOrders);
        } else {
            changeWindow(restrictedAccess);
        }
    }

    @FXML
    private void onlineOrders() throws IOException {
        if(userLogged.containsKey(true)) {
            changeWindow(onlineOrders);
        } else {
            changeWindow(restrictedAccess);
        }
    }

    @FXML
    private void newHire() throws Exception {
        changeWindow(newHire);
    }

    @FXML
    private void createNewUser(){

        // This String array is just to help break up the database and give an indication of what the user is.
        String[] title = new String[4];
        title[0] = "[general manager]";
        title[1] = "[team manager]";
        title[2] = "[alcohol bartender]";
        title[3] = "[food server]";


        // User ID is our unique key. (There could be multiple "Mike" users but only one unique UserID.)
        if (userDB.containsKey(userID.getText())){
            errorMSG.setFont(messageFont);
            errorMSG.setTextFill(Color.RED);
            errorMSG.setText("Error: The User ID already exists, please try another.");

            userID.clear();
        } else {

            // This switch case prompts the access level to properly assign the user the proper access depending on title.
            switch (accessLvl.getText()) {
                case ("GM"):
                    newUserSave(title[0], name.getText(), passwordField.getText(), userID.getText(), accessLvl.getText());
                    break;
                case ("TM"):
                    newUserSave(title[1], name.getText(), passwordField.getText(), userID.getText(), accessLvl.getText());
                    break;
                case ("AB"):
                    newUserSave(title[2], name.getText(), passwordField.getText(), userID.getText(), accessLvl.getText());
                    break;
                case ("FS"):
                    newUserSave(title[3], name.getText(), passwordField.getText(), userID.getText(), accessLvl.getText());
                    break;
            }

            errorMSG.setFont(messageFont);
            errorMSG.setTextFill(Color.GREEN);
            errorMSG.setText("Account #" + userID.getText() + " was successfully created.");
            name.clear();
            passwordField.clear();
            userID.clear();
            accessLvl.clear();
        }
    }

    @FXML
    private void cancelCreation() throws IOException{

        if (userLogged.containsKey(true) && userDB.get(tempUser[0]).getAccessLevel().equals("GM") || userDB.get(tempUser[0]).getAccessLevel().equals("TM")){
            changeWindow(managersPortal);
        } else {
            changeWindow(restrictedAccess);
        }
    }

    @FXML
    private void loginPage() throws IOException {
        if(!userLogged.containsKey(true)) {
            changeWindow(mainPortal);
        }
    }

    @FXML
    private void populateList(){
        // Here using PriorityQueue to store employee information sorted.
        PriorityQueue<String> employeeQueue = new PriorityQueue<>();
        String allEmployees = "";

        // Going through the Map to extract data from every object.
        for (Map.Entry<String,Employee> entry : userDB.entrySet()){
            employeeQueue.add(entry.getValue().getUserID() + "| " + entry.getValue().getAccessLevel() + ": " + entry.getValue().getName());
        }
        // Iterator goes through the Linked List to show the users in the text area.
        Iterator queue = employeeQueue.iterator();

        while (queue.hasNext()) {
            allEmployees += queue.next().toString() + "\n";
        }

        employeeArea.setText(allEmployees);

    }

    @FXML
    private void logout() throws IOException {
        if (userLogged.containsKey(true)) {
            userLogged.clear();
            changeWindow(logOut);
        }
    }


    private void changeWindow(String fxmlName) throws IOException{
        VBox mainWindow = FXMLLoader.load(POScontroller.class.getResource(fxmlName));
        rootPane.getChildren().setAll(mainWindow);
    }

}

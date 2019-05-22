package POS;

public class Employee implements DatabaseInterface {
    private String name, userPWD, userID, accessLevel;


    public Employee(){
        name = "";
        userPWD = "";
        userID = "";
        accessLevel = "";
    }
    public Employee(String newName, String newPWD, String newID, String newAccess){
        name = newName;
        userPWD = newPWD;
        userID = newID;
        accessLevel = newAccess;
    }

    public String getUserPWD() {
        return userPWD;
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}

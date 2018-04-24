package VR.login;

import VR.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import VR.controller.LoginController;

public class Login {

    private Stage stage;
    private Scene scene;
    private ImageView imageview;
    private Group root;


    public Login(Stage stage) {
        this.stage = stage;
        login();
    }

    public void login() {
        stage.setTitle("Login");
        stage.setResizable(false);        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/menu/Login.fxml"));
        } catch (Exception e) {
            Main.login.error();
        }
        Scene scene = new Scene(root, 600, 400);
        this.stage.setTitle("Login");
        this.stage.setScene(scene);
        stage.show();
    }
    
    public void menu() {
        stage.setTitle("Register");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/menu/Menu.fxml"));
        } catch (Exception e) {
            Main.login.error();
        }
        Scene scene = new Scene(root, 600, 400);
        this.stage.setScene(scene);
        stage.show();
    }
    
    public void options() {
        stage.setTitle("Options");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/menu/Options.fxml"));
        } catch (Exception e) {
            Main.login.error();
        }
        Scene scene = new Scene(root, 600, 400);
        this.stage.setScene(scene);
        stage.show();
        
    }
    
    public void error() {
        stage.setTitle("Error");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/menu/Error.fxml"));
        } catch (Exception e) {
            System.out.println("ERROR!");
            Main.login.error();
        }
        Scene scene = new Scene(root, 226, 105);
        this.stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }

}

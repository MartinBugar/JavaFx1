package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Database.DatabaseConnection;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private double xOffset;
    private double yOffset;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Button closeButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label passwordMatchLabel;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField usernameField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile = new File("IMAGES/LOGO.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);
    }

    public void registerButtonOnClick (ActionEvent event){

        if (passwordField.getText().equals(confirmPasswordField.getText())){
            System.out.println("hesla sa rovnaju");
            passwordMatchLabel.setText("Passwords matching");
            passwordMatchLabel.setStyle("-fx-text-fill: #00f20d");

        } else {
            System.out.println("hesla sa nerovnaju");
            passwordMatchLabel.setText("Passwords does not match");
            passwordMatchLabel.setStyle("-fx-text-fill:  #e6d100");
        }


        registerUser();
    }

    public void registerUser (){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        String insertFields = "INSERT INTO user_account(firstname, lastname, username, password) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessageLabel.setText("User has been registered.");
        } catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }

    }

    public void closeButtonOnAction (ActionEvent actionEvent){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    loginStage.setX(event.getScreenX() - xOffset);
                    loginStage.setY(event.getScreenY() - yOffset);
                }
            });

            Scene scene = new Scene(root, 600, 378);
            loginStage.setScene(scene);
            loginStage.show();

        } catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

}

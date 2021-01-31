package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import sample.Database.DatabaseConnection;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView brandingImageView;

    @FXML
    private ImageView lockImageView;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordTextField;

    @FXML
    public void buttonPressed()
    {
        enterPasswordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER){
                    System.out.println("ide to");
                    validateLogin();
                }
            }
        });

        usernameTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER){
                    System.out.println("ide to");
                    validateLogin();
                }
            }
        });

        loginButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER){
                    System.out.println("ide to");
                    validateLogin();
                }
            }
        });
    }


    public void loginButtonOnAction(ActionEvent event){

        if (!usernameTextField.getText().isBlank() && !enterPasswordTextField.getText().isBlank()) {
                System.out.println("Wait a moment for login");
             //   loginMessageLabel.setText("Wait a moment for login");
            validateLogin();
            }
            else if (!usernameTextField.getText().isBlank() && enterPasswordTextField.getText().isBlank()){
                System.out.println("Write also a password please");
                loginMessageLabel.setText("Write also a password please");
            }
            else if (usernameTextField.getText().isBlank() && !enterPasswordTextField.getText().isBlank()){
                System.out.println("Write also a username please");
                loginMessageLabel.setText("Write also a username please");
            }
            else loginMessageLabel.setText("Columns can not be EMPTY");

    }




    public void cancelButtonOnAction (ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){

        DatabaseConnection connectNow = new DatabaseConnection();  //vytvorena instancia triedy databaseConection
        Connection connectDb = connectNow.getConnection(); // nad instanciou si zavolam metodu na pripojenie

        String verifyLogin = "SELECT count(1) FROM user_account Where username = '" + usernameTextField.getText() + "'AND password = '" + enterPasswordTextField.getText() + "'";

        //kontrola prihlasovacich udajov
        try {
            Statement statement = connectDb.createStatement(); // , pouziva sa na vykonavanie SQL prikazov, pouziva sa len s resultsetom
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){//vracia 1 ked sa to podarilo a vracia 2 ked sa to nepodarilo
                    loginMessageLabel.setText("Account verified");
                } else {
                    loginMessageLabel.setText("Invalid login");
                }
            }

        } catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void createAccountForm(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);

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
                    registerStage.setX(event.getScreenX() - xOffset);
                    registerStage.setY(event.getScreenY() - yOffset);
                }
            });

            registerStage.setScene(new Scene(root, 600,623));
            registerStage.show();

            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();


        } catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("IMAGES/image1.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("IMAGES/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }
}

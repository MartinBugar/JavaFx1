package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.net.URL;
import java.sql.Struct;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;

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


    public void loginButtonOnAction(ActionEvent event){

        if (!usernameTextField.getText().isBlank() && !enterPasswordTextField.getText().isBlank()) {
                System.out.println("Wait a moment for login");
                loginMessageLabel.setText("Wait a moment for login");
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

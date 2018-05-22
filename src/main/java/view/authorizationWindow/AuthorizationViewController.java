package view.authorizationWindow;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import view.viewUtil.ViewUtil;


public class AuthorizationViewController {

    @FXML
    private TextField idField;

    @FXML
    private TextField passwordField;


    private MainAuthorization mainAuthorization;
    @FXML
    private void initialize(){

    }

    public void logInClick() {
        try {
            int id;
            try {
                id = Integer.parseInt(idField.getText());
            }catch (Exception e){
                ViewUtil.viewAlertWindow("Please, use only numeral");
                return;
            }
            String password = passwordField.getText();
            if (id == 0){
                if (password.equals("0")){
                    mainAuthorization.stop();
                    System.out.println("initDepTable");
                    mainAuthorization.getMainApp().initMainDepartmentTable();
                }else{
                    ViewUtil.viewAlertWindow("Wrong password");
                }
            }else{
                ViewUtil.viewAlertWindow("There is no employee with those ID(" + id +")");
            }
        }catch (Exception e){
            ViewUtil.viewAlertWindow(e.getMessage());
        }
    }

    public void setMainAuthorization(MainAuthorization mainAuthorization){
        this.mainAuthorization = mainAuthorization;
    }
}

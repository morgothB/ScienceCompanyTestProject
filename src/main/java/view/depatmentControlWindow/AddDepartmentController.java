package view.depatmentControlWindow;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class AddDepartmentController {
    @FXML
    private TextField textName;

    private DepartmentsEditController departmentsEditController;

    public void setDepartmentsEditController(DepartmentsEditController departmentsEditController){
        this.departmentsEditController = departmentsEditController;
    }
}

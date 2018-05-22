package view.workersControlWindow;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.DBEntities.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkersController;
import view.mainWindow.MainWindowController;

import java.util.ArrayList;


public class EmployeesEditController {
    @FXML
    private TableView<WorkerEntity> tableEmployee;

    @FXML
    private TableColumn<WorkerEntity, Integer> columnID;

    @FXML
    private TableColumn<WorkerEntity, String> columnFirstName;

    @FXML
    private TableColumn<WorkerEntity, String> columnSecondName;

    @FXML
    private TableColumn<WorkerEntity, String> columnOtherName;

    @FXML
    private TableColumn<WorkerEntity, Integer>  columnDepartmentID;

    @FXML
    private TableColumn<WorkerEntity, String> columnDepartmentName;

    @FXML
    private TextField textFind;

    @FXML
    private Button buttonAccess;

    @FXML
    private TextField fieldAccess;

    private MainWindowController personOverviewController;
    private Stage employeesEditStage;
    private Stage addEmployeeWindow;
    private Stage editEmployeeWindow;

    @FXML
    public void initialize() throws Exception{
        System.out.println("emp ini");
        columnID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        columnFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        columnSecondName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSecondName()));
        columnDepartmentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartment().getName()));

        columnDepartmentID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDepartment().getId()).asObject());
        columnOtherName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSecondName() + " " + cellData.getValue().getNobParticle()));

        tableEmployee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WorkerEntity>() {
            @Override
            public void changed(ObservableValue<? extends WorkerEntity> observable, WorkerEntity oldValue, WorkerEntity newValue) {
                if (newValue != null){
                    fieldAccess.setText(String.valueOf(-1));
                }
            }
        });
        /*if (Manager.getInstance().getAccess() != 0){
            buttonAccess.visibleProperty().setValue(false);
            fieldAccess.visibleProperty().setValue(false);
        }*/
        ObservableList<WorkerEntity> a = FXCollections.observableArrayList(WorkersController.getInstance().getEmployees());

        tableEmployee.setItems(a);
    }

    public void setPersonOverviewController(MainWindowController personOverviewController){
        this.personOverviewController = personOverviewController;
    }

    public void setStage(Stage employeesEditStage){
        this.employeesEditStage = employeesEditStage;
    }


    public void shutdown(){

    }



}

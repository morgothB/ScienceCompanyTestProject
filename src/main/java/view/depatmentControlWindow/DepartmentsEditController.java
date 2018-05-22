package view.depatmentControlWindow;


import model.DBEntities.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DBEntities.DepartmentEntity;
import model.DBEntities.WorkerEntity;
import model.DepartmentsController;
import view.MainApp;
import view.mainWindow.MainWindowApp;
import view.mainWindow.MainWindowController;

public class DepartmentsEditController {
    @FXML
    private ListView<DepartmentEntity> departmentList;

    @FXML
    private TextField departmentName;

    @FXML
    private TableView<WorkerEntity> tableEmployee;

    @FXML
    private TableColumn<WorkerEntity, Integer> columnID;

    @FXML
    private TableColumn<WorkerEntity, String> columnName;

    @FXML
    private TableColumn<WorkerEntity, String> columnSurname;

    @FXML
    private TableColumn<WorkerEntity, String> columnPosition;


    private DepartmentEntity selectedDepartment;
    private MainWindowController personOverviewController;
    private Stage departmentsEditStage;
    private Stage addEmployee;
    private Stage addDepartment;

    @FXML
    private void initialize() {
        departmentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DepartmentEntity>() {
            @Override
            public void changed(ObservableValue<? extends DepartmentEntity> observable, DepartmentEntity oldValue, DepartmentEntity newValue) {
                setDepartment(newValue);
            }
        });

        ObservableList<DepartmentEntity> a = FXCollections.observableArrayList(DepartmentsController.getInstance().getDepartments());
        departmentList.setItems(a);
        columnID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        columnSurname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSecondName()));
        columnPosition.setCellValueFactory(cellData -> new SimpleStringProperty("todo"));


    }

    public void setStage(Stage departmentsEditStage){
        this.departmentsEditStage = departmentsEditStage;
    }


    private void setDepartment(DepartmentEntity dep){
        if (dep != null) {
            this.selectedDepartment = dep;
            departmentName.setText(dep.getName());
            ObservableList<WorkerEntity> a = FXCollections.observableArrayList(dep.getWorkers());
            tableEmployee.setItems(a);
        }
    }

    public void setPersonOverviewController(MainWindowController personOverviewController){
        this.personOverviewController = personOverviewController;
    }

    public void shutdown() throws Exception{
        personOverviewController.refreshDepartments();
    }


    public void deleteDepartment(){
    }

    public void addDepartment(){
        try {
            this.addDepartment = new Stage();
            addDepartment.setTitle("Addition department");
            FXMLLoader loader = new FXMLLoader();
            //System.out.println();
            loader.setLocation(MainApp.class.getResource("/AddDepartmentOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            addDepartment.setScene(scene);
            addDepartment.initOwner(departmentsEditStage);
            addDepartment.initModality(Modality.WINDOW_MODAL);
            addDepartment.resizableProperty().setValue(false);
            addDepartment.show();
            AddDepartmentController controller = loader.getController();
            controller.setDepartmentsEditController(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void renameDepartment() {

    }

}

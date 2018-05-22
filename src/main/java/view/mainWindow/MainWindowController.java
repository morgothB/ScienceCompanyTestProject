package view.mainWindow;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DBEntities.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.DepartmentsController;
import view.MainApp;
import view.depatmentControlWindow.DepartmentsEditController;
import view.mainWindow.calendarTableView.TableOverviewController;
import view.workersControlWindow.EmployeesEditController;

import java.net.URL;

public class MainWindowController {
    @FXML
    private TabPane tabTable;

    @FXML
    private ListView<DepartmentEntity> departmentsListView;

    @FXML
    private BorderPane departmentsBorderPane;

    @FXML
    private ScrollPane tableScrollPane;

    private MainWindowApp mainDepartmentTable;
    private DepartmentEntity dep;
    private FXMLLoader loader;
    private URL uri;
    private TableOverviewController controller;
    private boolean destr = false;
    private AnchorPane personOverview;

    public MainWindowController() {

    }

    private void setTableOverview(Tab tb, DepartmentEntity dep){
        if (destr){
            destr = false;
            //((AnchorPane)tb.getContent()).getChildren().clear();
            return;
        }
        destr = true;
        System.out.println(controller == null);
        if (controller != null){
            try{
                ((AnchorPane)tb.getContent()).getChildren().setAll(new ScrollPane());
                System.out.println(tb.getText());
                tableScrollPane.setContent(personOverview);
                tableScrollPane.fitToHeightProperty().setValue(true);
                controller.setCurrentTab(tb.getText());
                controller.setDepartment(dep);
            }catch (Exception e){

            }
        }else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(uri);
                System.out.println("qq");
                personOverview = (AnchorPane) loader.load();
                ((AnchorPane)tb.getContent()).getChildren().setAll(new ScrollPane());
                System.out.println(tb.getText());
                tableScrollPane.setContent(personOverview);
                tableScrollPane.fitToHeightProperty().setValue(true);
                controller = loader.getController();
                controller.setCurrentTab(tb.getText());
                controller.setDepartment(dep);
                controller.setCurrentTab(tb.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void refreshDepartments() throws Exception{
        ObservableList<DepartmentEntity> a = FXCollections.observableArrayList(DepartmentsController.getInstance().getDepartments());
        departmentsListView.setItems(a);
        selectDepartment();
    }

    public void setTable(){
        if (dep != null) {
            setTableOverview(tabTable.getSelectionModel().getSelectedItem(), dep);
        }
    }

    public void selectDepartment(){
        dep = departmentsListView.getSelectionModel().getSelectedItem();
        System.out.println(dep);
        destr = false;
        setTable();
    }

    @FXML
    private void initialize() throws Exception{
        uri = MainWindowApp.class.getResource("/TableOverview.fxml");
        tabTable.getSelectionModel().selectFirst();
        ObservableList<DepartmentEntity> a = FXCollections.observableArrayList(DepartmentsController.getInstance().getDepartments());//Manager.getInstance().getDepartments());
        departmentsListView.setItems(a);
        departmentsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DepartmentEntity>() {
            @Override
            public void changed(ObservableValue<? extends DepartmentEntity> observable, DepartmentEntity oldValue, DepartmentEntity newValue) {
                selectDepartment();
            }
        });
    }


    public void setMainDepartmentTable(MainWindowApp mainWindowApp) {
        this.mainDepartmentTable = mainWindowApp;
    }


    public void editDepartments(){
        Stage editDepartmentWindow = new Stage();
        editDepartmentWindow.setTitle("Edit departments");
        editDepartmentWindow.initOwner(mainDepartmentTable.getPrimaryStage());
        editDepartmentWindow.initModality(Modality.WINDOW_MODAL);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/DepartmentsEditOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            DepartmentsEditController controller = loader.getController();
            controller.setPersonOverviewController(this);
            controller.setStage(editDepartmentWindow);
            Scene scene = new Scene(anchorPane);
            editDepartmentWindow.setScene(scene);
            editDepartmentWindow.setOnHidden(e -> {
                try {
                    controller.shutdown();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            editDepartmentWindow.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editEmployees(){
        Stage editEmployeeWindow = new Stage();
        editEmployeeWindow.setTitle("Edit employees");
        editEmployeeWindow.initOwner(mainDepartmentTable.getPrimaryStage());
        editEmployeeWindow.initModality(Modality.WINDOW_MODAL);
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/EmployeesEditOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            EmployeesEditController controller = loader.getController();
            controller.setPersonOverviewController(this);
            controller.setStage(editEmployeeWindow);
            Scene scene = new Scene(anchorPane);
            editEmployeeWindow.setScene(scene);
            editEmployeeWindow.setOnHidden(e -> {
                controller.shutdown();
            });
            editEmployeeWindow.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

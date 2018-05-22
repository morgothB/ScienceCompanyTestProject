package view.mainWindow.calendarTableView;

import javafx.beans.property.SimpleIntegerProperty;
import model.CalendarController;
import model.CalendarDep;
import model.DBEntities.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Pair;
import javafx.util.StringConverter;
import model.DepartmentEntityModel;
import view.viewUtil.ViewUtil;

public class TableOverviewController {
    private DepartmentEntity dep;
    private int monthId;
    private ObservableList<Pair<WorkerEntity, CalendarDep>> personData;
    @FXML
    private TableView<Pair<WorkerEntity, CalendarDep>> personTable;

    @FXML
    private TableColumn<Pair<WorkerEntity, CalendarDep>, String> nameID;

    @FXML
    private TableColumn<Pair<WorkerEntity, CalendarDep>, Integer> idID;

    @FXML
    private void initialize(){
        personTable.editableProperty().setValue(true);

        nameID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey().getFirstName().concat(" ").concat(cellData.getValue().getKey().getSecondName())));
        idID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getKey().getId()).asObject());
    }

    public void setCurrentTab(String month) {
        if (dep == null) return;
        monthId = -1;
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        switch (month){
            case ("January"):{
                monthId = 0;
                break;
            }
            case("February"):{
                monthId = 1;
                break;
            }
            case("March"):{
                monthId = 2;
                break;
            }
            case ("April"):{
                monthId = 3;
                break;
            }
            case ("May"):{
                monthId = 4;
                break;
            }
            case ("June"):{
                monthId = 5;
                break;
            }
            case ("July"):{
                monthId = 6;
                break;
            }
            case ("August"):{
                monthId = 7;
                break;
            }
            case ("September"):{
                monthId = 8;
                break;
            }
            case ("October"):{
                monthId = 9;
                break;
            }
            case ("November"): {
                monthId = 10;
                break;
            }
            case ("December"):{
                monthId = 11;
                break;
            }
        }
        if (monthDays[monthId] > (personTable.getColumns().size() - 2)){
            int n = personTable.getColumns().size() - 2;
            for (Integer i =  n + 1; i <= monthDays[monthId]; ++i){
                TableColumn<Pair<WorkerEntity, CalendarDep>, String> a = new TableColumn(String.valueOf(i+1));

                a.setMaxWidth(80);
                a.setMinWidth(45);
                personTable.getColumns().add(a);
            }
        }else{
            personTable.getColumns().remove(monthDays[monthId] + 2, (personTable.getColumns().size()));
        }
        for (int i = 2; i < personTable.getColumns().size(); ++i){
            String type = setTitle(i-2);
            personTable.getColumns().get(i).setText(String.valueOf(i-1).concat(type));
            final int num = i;
                ((TableColumn<Pair<WorkerEntity, CalendarDep>, String>) personTable.getColumns().get(i)).setCellFactory(col -> new TextFieldTableCell<>(new StringConverter<String>() {
                    @Override
                    public String toString(String object) {
                        try {
                            if (object != null){
                                return object.toString();
                            }else{
                                return "null";
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    public String fromString(String string) {
                        return string;
                    }
                }));
            ((TableColumn<Pair<WorkerEntity, CalendarDep>, String>) personTable.getColumns().get(i)).setCellValueFactory(cellData -> {
                final int j = num;
                return  new SimpleStringProperty(cellData.getValue().getValue().getMonth(monthId).get(j-2).getKey());
            });
        }
    }

    public void setDepartment(DepartmentEntity dep) {
        this.dep = dep;
        try {
            DepartmentEntityModel departmentModel = new DepartmentEntityModel(dep);
            personData = FXCollections.observableArrayList(departmentModel.getTableLine());
            personTable.setItems(personData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String setTitle(int i){
        return "";
    }
}

package model;

import javafx.util.Pair;
import model.DBEntities.DepartmentEntity;
import model.DBEntities.WorkerEntity;

import java.util.ArrayList;

public class DepartmentEntityModel {

    private DepartmentEntity department;
    private ArrayList<WorkerEntity> employees;
    private  ArrayList<CalendarDep> calendars;
    private ArrayList<Pair<WorkerEntity, CalendarDep>> tableLine;

    public DepartmentEntityModel(DepartmentEntity department){
        this.department = department;
        this.employees = new ArrayList<>(department.getWorkers());
        this.tableLine = new ArrayList<>();
        calendars = new ArrayList<>();
        for (int i = 0; i < employees.size(); ++i) {
            calendars.add(new CalendarDep(employees.get(i).getCalendarsId().get(0)));
            tableLine.add(new Pair<>(employees.get(i), calendars.get(i)));

        }
    }

    public ArrayList<Pair<WorkerEntity, CalendarDep>> getTableLine(){
        return tableLine;
    }
}

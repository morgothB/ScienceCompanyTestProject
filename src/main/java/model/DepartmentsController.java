package model;

import model.DBEntities.DepartmentEntity;
import model.util.HibernateUtil;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.HashMap;

public class DepartmentsController {

    private static DepartmentsController instance;
    private HashMap<String, DepartmentEntity> departments;
    private ArrayList<DepartmentEntity> departmentEntities;

    private DepartmentsController () {
        departments = new HashMap<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        departmentEntities = (ArrayList<DepartmentEntity>)session.createQuery("from DepartmentEntity").getResultList();
        session.close();
    }

    public static DepartmentsController getInstance(){
        if (instance == null){
            instance = new DepartmentsController();
        }
        return instance;
    }

    public ArrayList<DepartmentEntity> getDepartments(){
        ArrayList<DepartmentEntity> loc = new ArrayList<>();
        departments.forEach((name, dep) -> loc.add(dep));
        System.out.println(departmentEntities);
        return departmentEntities;
    }

}

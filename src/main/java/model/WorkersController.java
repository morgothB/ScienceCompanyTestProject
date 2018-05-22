package model;

import model.DBEntities.WorkerEntity;
import model.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
public class WorkersController {
    private static ArrayList<WorkerEntity> workers;
    private static WorkersController instance;

    public static WorkersController getInstance(){
        if (instance == null)
            instance = new WorkersController();
        return instance;
    }

    private WorkersController(){
        workers = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        workers = (ArrayList<WorkerEntity>)session.createQuery("from WorkerEntity ").getResultList();
        session.close();
    }

    public static ArrayList<WorkerEntity> getEmployees(){
        for (WorkerEntity w : workers){
            System.out.println(w);
        }
        return workers;
    }
}

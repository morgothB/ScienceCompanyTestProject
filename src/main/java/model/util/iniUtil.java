package model.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.DBEntities.*;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class iniUtil {

    private static double[] probList = {0.5, 0.55, 0.6, 0.65, 0.7, 0.75, 0.8, 0.85, 0.9, 0.95, 1.0};

    private static class InnerUtil {
        private static String[] depName = {"Strawberry eating department", "Department of lazy", "Maneging hate department", "TODO dep", "The most efficient department", "Low salary department"};

        public static DepartmentEntity[] deps = new DepartmentEntity[6];

        static {
            for (int j = 0; j < 6; j++) {
                deps[j] = new DepartmentEntity();
                deps[j].setName(depName[j]);
            }
        }

        private static int i = 0;

        public static DepartmentEntity getDep() {
            i++;
            if (i == depName.length) {  i=0; }
            return deps[i];
        }

    }

    public static void iniTable(Session session){
        Transaction transaction = null;
        /**
         * initialize "label" DB table
        */

        transaction = session.getTransaction();
        transaction.begin();

        String[] labels = {"Р", "Н", "В", "Рв", "Б", "К", "ОТ", "До", "Хд", "У", "Ож"};

        for (String s : labels){
            LabelEntity label = new LabelEntity();
            label.setLabelTag(s);
            session.save(label);
        }

        transaction.commit();

        /**
         * initialize "worker" and "department" DB tables
         */

        transaction = session.getTransaction();
        transaction.begin();

        String[] name = {"Ivan", "Konstantin", "Boris", "Michel", "Egor", "Dash", "Ilia"};

        String[] family = {"Ivanov", "Petrov", "Anisimov", "Dread", "Hashcode", "Spacey", "Miller", "Shin"};

        DepartmentEntity[] deps = InnerUtil.deps;

        ArrayList<WorkerEntity> workers = new ArrayList<>();



        for (String curName : name) {
            for (String curFam : family) {
                WorkerEntity worker = new WorkerEntity(curName, null, curFam, null, InnerUtil.getDep());

                workers.add(worker);
            }
        }

        for (DepartmentEntity dep : deps) {
            session.save(dep);
        }

        for (WorkerEntity worker : workers) {
            CalendarEntity calendarEntity = new CalendarEntity();
            calendarEntity.setYear(2018);
            calendarEntity.setWorker(worker);
            session.save(worker);
            session.save(calendarEntity);
        }

        transaction.commit();

        /**
         * ini "calendar" DB table
         */

        /**
         * ini "datelable" DB table
         */

        transaction = session.getTransaction();
        transaction.begin();

        /*org.hibernate.query.Query query = session.createQuery("delete DateLabelEntity ");
        query.executeUpdate();*/

        List<LabelEntity> labelsTags = (ArrayList<LabelEntity>)session.createQuery("FROM LabelEntity ").getResultList();


        for(LabelEntity l : labelsTags) {
            System.out.println(l.getId() + " | " + l.getLabelTag());
        }

        for (int i = 1; i <= 56; i++) {
            //WorkerEntity w = session.get(WorkerEntity.class, i);
            CalendarEntity calendarEntity = session.get(CalendarEntity.class, i);
            Date date = Date.valueOf(LocalDate.of(2018, 1, 1));
            for (int j = 0; j < 365; j++){
                DateLabelEntity dateLabelEntity = new DateLabelEntity();
                dateLabelEntity.setCalendar(calendarEntity);
                dateLabelEntity.setDate((Date)date.clone());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 1);
                date.setTime(calendar.getTimeInMillis());
                double rand = Math.random();
                for (int k = 0; k < 12; k++) {
                    if (k == 11) {
                        dateLabelEntity.setLabel(labelsTags.get(k-1));
                        break;
                    }
                    if (rand <= probList[k]) {
                        dateLabelEntity.setLabel(labelsTags.get(k));
                        break;
                    }
                }
                session.save(dateLabelEntity);
            }
        }

        transaction.commit();


    }

    public static void mainUtil() {
        File file = new File("src/main/java/hibernate.cfg.xml");
        Session session = null;
        Transaction transaction = null;
        try {
            /*ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure(file)
                    .build();
            SessionFactory sessionFactory = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Department.class)
                    .buildMetadata()
                    .buildSessionFactory();*/
            session = HibernateUtil.getSessionFactory().openSession();

            iniTable(session);


            session.close();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
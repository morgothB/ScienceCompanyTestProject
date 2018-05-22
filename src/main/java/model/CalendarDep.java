package model;


import javafx.util.Pair;
import model.DBEntities.CalendarEntity;
import model.DBEntities.DateLabelEntity;
import model.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.*;


public class CalendarDep {
    ArrayList<ArrayList<Pair<String, Integer>>> calendar;

    public CalendarDep(CalendarEntity res){
        calendar = new ArrayList<>();
        calendar.add(fill(31)); //january
        calendar.add(fill(28));
        calendar.add(fill(31));
        calendar.add(fill(30));
        calendar.add(fill(31));
        calendar.add(fill(30));
        calendar.add(fill(31));
        calendar.add(fill(31));
        calendar.add(fill(30));
        calendar.add(fill(31));
        calendar.add(fill(30));
        calendar.add(fill(31));
        if (!(res == null)) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from DateLabelEntity where calendar = :calendarParam");
            query.setParameter("calendarParam", res);
            List<DateLabelEntity> dateLabelTags = (ArrayList<DateLabelEntity>)((org.hibernate.query.Query) query).list();
            for (DateLabelEntity e : dateLabelTags){
                Calendar cal = Calendar.getInstance();
                cal.setTime(e.getDate());
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                calendar.get(month).set(day-1, new Pair<>(e.getLabel().getLabelTag(), day));
            }
            session.close();
        }
    }

    private ArrayList<Pair<String, Integer>> fill(int n){
        ArrayList<Pair<String, Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; ++i) a.add(new Pair<>(" ", 0));
        return a;
    }

    public ArrayList<Pair<String, Integer>> getMonth(int i) {return calendar.get(i);}

    @Override
    public String toString(){
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < calendar.size() - 1; ++i) {
            a.append(calendar.get(i).get(0).getKey());
            a.append("|");
            a.append(calendar.get(i).get(0).getValue());
            for (int j = 1; j < calendar.get(i).size(); ++j) {
                a.append("&");
                a.append(calendar.get(i).get(j).getKey());
                a.append("|");
                a.append(calendar.get(i).get(j).getValue());
            }
            a.append("&");
        }
        int i = calendar.size() - 1;
        a.append(calendar.get(i).get(0).getKey());
        a.append("|");
        a.append(calendar.get(i).get(0).getValue());
        for (int j = 1; j < calendar.get(i).size(); ++j) {
            a.append("&");
            a.append(calendar.get(i).get(j).getKey());
            a.append("|");
            a.append(calendar.get(i).get(j).getValue());
        }
        return a.toString();
    }
}

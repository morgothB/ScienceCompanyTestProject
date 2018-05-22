package model;

public class CalendarController {

    private static CalendarController instance;


    public static CalendarController getInstance(){
        if (instance == null) {
            instance = new CalendarController();
        }
        return instance;
    }


}

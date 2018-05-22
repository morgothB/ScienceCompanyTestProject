package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.DBEntities.CalendarEntity;
import model.DBEntities.DepartmentEntity;
import model.DBEntities.WorkerEntity;
import model.util.iniUtil;
import view.authorizationWindow.MainAuthorization;
import view.mainWindow.MainWindowApp;


public class MainApp extends Application {
    private static DepartmentEntity instanceDT;
    private static WorkerEntity instanceET;
    private static CalendarEntity instanceCal;
    private Stage mainStage;

    @Override
    public void start(Stage stage){
            iniUtil.mainUtil();

            this.mainStage = stage;
            mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });
            MainAuthorization authorization = new MainAuthorization();
            authorization.setMainApp(this);
            authorization.start(mainStage);
    }

    public void initMainDepartmentTable(){
        MainWindowApp departmentTable = new MainWindowApp();
        departmentTable.setMainApp(this);
        mainStage.close();
        mainStage = new Stage();
        System.out.println("qq");
        departmentTable.start(mainStage);
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }

}

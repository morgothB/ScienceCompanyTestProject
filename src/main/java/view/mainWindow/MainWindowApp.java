package view.mainWindow;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.MainApp;

import java.io.IOException;

public class MainWindowApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private MainWindowController controllerPersonOverview;
    private AnchorPane table;
    private MainApp mainApp;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        System.out.println("in start method");
        initRootLayout();
        showPersonOverview();

       // initMenuBarLayout();

    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/MainWindow.fxml"));
            System.out.println("load mainwindow");
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);
            controllerPersonOverview = loader.getController();
            controllerPersonOverview.setMainDepartmentTable(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWindowApp.class.getResource("/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

}

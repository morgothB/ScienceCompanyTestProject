package view.authorizationWindow;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.MainApp;

import java.net.URL;


public class MainAuthorization extends Application {
    private Stage authorizationStage;
    private MainApp mainApp;

    @Override
    public void start(Stage stage){
        try {
            this.authorizationStage = stage;
            authorizationStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });
            authorizationStage.setTitle("Authorization");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/AuthorizationView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            authorizationStage.setScene(scene);
            authorizationStage.resizableProperty().setValue(false);
            authorizationStage.show();
            AuthorizationViewController controller = loader.getController();
            controller.setMainAuthorization(this);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public MainApp getMainApp(){
        return mainApp;
    }

    public static void main(String[] args){
        launch(args);
    }
}

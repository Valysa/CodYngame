package V;

import V.views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        //Def initial constants
        final String MAIN_TITLE = "CodYingGame";
        final Integer MAIN_WIDTH = 1640;
        final Integer MAIN_HEIGHT = 780;

        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        Scene mainViewScene = new Scene(new MainView(20));

        stage.setScene(mainViewScene);
        stage.setTitle(MAIN_TITLE);
        stage.setWidth(MAIN_WIDTH);
        stage.setHeight(MAIN_HEIGHT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
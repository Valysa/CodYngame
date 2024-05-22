package V;

import M.Bdd;
import M.Exercise;
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
        final int MAIN_WIDTH = 1640;
        final int MAIN_HEIGHT = 780;


        Bdd.idBdd("3306", "root", "MyS3cur3P@sswOrd!");
        Bdd.create();
        Exercise[] exo = Exercise.allExo();

        Scene mainViewScene = new Scene(new MainView(exo));



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
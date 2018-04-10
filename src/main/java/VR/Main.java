package VR;

import VR.gui.Pause;
import VR.keyhandlers.Keylistener;
import VR.levels.Test1;
import VR.sections.CompanyFade;
import VR.sections.Intro;
import VR.sections.Menu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    public static Group root;
    public static Scene scene;    
    public static Keylistener keys;
    public static Canvas canvas;
    public static GraphicsContext gc;
    public static Canvas guiCanvas;
    public static GraphicsContext gui;
    public static Canvas pauseCanvas;
    public static GraphicsContext pause;

    public static int width;
    public static int height;
    public static double scale;

    public static Pause pauseMenu;
    public static CompanyFade comp;
    public static Intro intro;
    public static Menu menu;
    public static Test1 test;

//    private static Profile profile;

    /*Passes stage across all viewpoints! View classes then modify the stage there
    and contain their own methods. Public object like profile is kept here for sections to access it!*/
    public static void main(String[] args) {
        System.setProperty("quantum.multithreaded", "false");
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = new Group();
        width = 1024;
        height = 768;
        scale = 1;
        scene = new Scene(root, width, height, false, SceneAntialiasing.DISABLED);
        keys = new Keylistener(scene);

        Main.stage = stage;
        stage.setTitle("VR the Adventure");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();


        /*
*stage.initStyle(StageStyle.TRANSPARENT);
*scene.setFill(Color.TRANSPARENT);
         */
        Main.canvas = new Canvas(width, height);
        Main.guiCanvas = new Canvas(width, height);
        Main.pauseCanvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        root.getChildren().add(guiCanvas);
        root.getChildren().add(pauseCanvas);
        canvas.autosize();
        guiCanvas.autosize();
        pauseCanvas.autosize();
        
        gc = canvas.getGraphicsContext2D();
        gui = guiCanvas.getGraphicsContext2D();
        pause = pauseCanvas.getGraphicsContext2D();
        pauseMenu = new Pause();
        intro = new Intro();
        comp = new CompanyFade();
        menu = new Menu();
        test = new Test1();

        companyFade();
//        test.animate();
//        Login login = new Login(this.stage);
    }

    public static void companyFade() {
        comp.animate();
    }

    public static void menu() {
        menu.animate();
    }

}

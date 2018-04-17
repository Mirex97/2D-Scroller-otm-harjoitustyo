package VR;

import VR.audio.AudioPlayer;
import VR.database.Database;
import VR.database.OptionsDao;
import VR.database.ProfileDao;
import VR.database.SaveDao;
import VR.gui.Pause;
import VR.handlers.Imagehandler;
import VR.handlers.Keylistener;
import VR.levels.Credits;
import VR.levels.Test1;
import VR.login.Login;
import VR.profile.Options;
import VR.profile.Profile;
import VR.profile.Save;
import VR.sections.CompanyFade;
import VR.sections.Intro;
import VR.sections.Menu;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 *Main class to handle everything in the project.
 *Everything begins and ends here! (Almost everything).
 */
public class Main extends Application {

    public static Stage stage;
    public static Group root;
    public static Scene scene;
    public static Keylistener keys;
    public static Imagehandler images;
    public static Canvas canvas;
    public static GraphicsContext gc;
    public static Canvas guiCanvas;
    public static GraphicsContext gui;
    public static Canvas pauseCanvas;
    public static GraphicsContext pause;

    public static int width;
    public static int height;
    public static double scale;

    public static Login login;
    public static Pause pauseMenu;
    public static CompanyFade comp;
    public static Intro intro;
    public static Menu menu;
    public static Test1 test;
    public static AudioPlayer bgMusic;
    public static Credits credit;

    public static Profile profile;
    public static Options options;
    public static ArrayList<Save> saves;
    public static Database base;
    public static OptionsDao optionsdao;
    public static SaveDao savesdao;
    public static ProfileDao profilesdao;

//    private static Profile profile;

    /*Passes stage across all viewpoints! 
    View classes then modify the stage there
    and contain their own methods. 
    Public object like profile is kept here for sections to access it!*/
    public static void main(String[] args) {
        base = new Database("jdbc:sqlite:saves.db");
        base.getConnection();
        base.createNewTables();
        optionsdao = new OptionsDao(base);
        savesdao = new SaveDao(base);
        profilesdao = new ProfileDao(base);
        System.setProperty("quantum.multithreaded", "false");
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception { //Handles login!
        Main.stage = stage;
        this.stage.setResizable(false);
        this.login = new Login(stage);
    }

    public static void startGAME() throws Exception {
        root = new Group();
        width = 1024;
        height = 768;
        scale = 1;
        scene = new Scene(root, width, height, 
                false, SceneAntialiasing.DISABLED);
        keys = new Keylistener(scene);
        images = new Imagehandler();

        bgMusic = new AudioPlayer("/Music/MainTheme.ogg");
        bgMusic.setVolume(options.getVolume());

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
        credit = new Credits();

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

    public static void backToMenu() {
        gc.restore();
        gui.restore();
        pause.restore();
        menu = new Menu();
        try {
            test = new Test1();
        } catch (Exception e) {
            Main.login.error();
        }
        menu.animate();
    }

}

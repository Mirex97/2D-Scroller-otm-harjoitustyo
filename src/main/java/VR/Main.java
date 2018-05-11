package VR;

import VR.audio.AudioPlayer;
import VR.audio.MusicLoader;
import VR.database.Database;
import VR.database.OptionsDao;
import VR.database.ProfileDao;
import VR.database.SaveDao;
import VR.events.CutsceneHandler;
import VR.gui.Pause;
import VR.handlers.Imagehandler;
import VR.handlers.Keylistener;
import VR.levels.Credits;
import VR.levels.Test1;
import VR.login.Login;
import VR.profile.Options;
import VR.profile.Profile;
import VR.profile.Save;
import VR.sections.Background;
import VR.sections.CompanyFade;
import VR.sections.Intro;
import VR.sections.Menu;
import VR.util.DeltaTime;
import VR.util.FrameMaker;
import VR.util.XmlWriterUtil;
import VR.util.Zipper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Main class to handle everything in the project. Everything begins and ends
 * here! (Almost everything).
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
    public static Canvas backgroundCanvas;
    public static GraphicsContext background;

    public static double width;
    public static double height;
    public static double scale;

    public static Login login;
    public static Pause pauseMenu;
    public static CompanyFade comp;
    public static Intro intro;
    public static Menu menu;
    public static Test1 test;
    public static AudioPlayer bgMusic;
    public static AudioPlayer fx;
    public static AudioPlayer talk;
    public static Credits credit;

    public static CutsceneHandler cutscenes = new CutsceneHandler();

    public static Profile profile;
    public static Options options;
    public static ArrayList<Save> saves;
    public static Database base;
    public static OptionsDao optionsdao;
    public static SaveDao savesdao;
    public static ProfileDao profilesdao;
    public static Background backGround;
    public static MusicLoader musicloader;

    private static Zipper zipper;
    public static XmlWriterUtil xmlWriter;
    public static DeltaTime delta;
    public static FrameMaker maker;

    /*Passes stage across all viewpoints! 
    View classes then modify the stage there
    and contain their own methods. 
    Public object like profile is kept here for sections to access it!*/
    public static void main(String[] args) {

        throwZip();

        zipper = new Zipper();
        xmlWriter = new XmlWriterUtil();
        delta = new DeltaTime();
        maker = new FrameMaker();

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
    public void start(Stage stage) throws Exception {
        zipper.unZip("levelsandstuff.zip", "levels");
        zipper = null;
        Main.stage = stage;
        this.stage.setResizable(false);

        this.login = new Login(stage);
    }

    public static void startGAME() throws Exception {
        root = new Group();
        width = 1024;
        height = 768;

        double testheight = options.getResolution();
        double testwidth = testheight / 0.75;
        stage.setTitle("VR the Adventure");

        boolean full = options.isFullscreen();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        scale = 1;
        if (full) {
            scene = new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight(), false, SceneAntialiasing.DISABLED);

        } else {
            scene = new Scene(root, testwidth, testheight, false, SceneAntialiasing.DISABLED);
        }
        scene.setFill(Color.BLACK);
        keys = new Keylistener(scene);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(full);

        if (!stage.isFullScreen()) {

            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            stage.setWidth(testwidth);
            stage.setHeight(testheight);
        }

        images = new Imagehandler();

        musicloader = new MusicLoader();

        bgMusic = new AudioPlayer();
        bgMusic.setVolume(options.getVolume());
        bgMusic.changeClip(musicloader.getMusic("MainBegin"));
        fx = new AudioPlayer();
        fx.toggleLoop();
        fx.setVolume(options.getVolume());
        fx.changeClip(musicloader.getFx("SELECT"));
        talk = new AudioPlayer();
        talk.toggleLoop();
        talk.setVolume(options.getVolume());
        talk.changeClip(musicloader.getFx("TALK"));

        Main.canvas = new Canvas(width, height);
        Main.guiCanvas = new Canvas(width, height);
        Main.pauseCanvas = new Canvas(width, height);
        Main.backgroundCanvas = new Canvas(width, height);

        root.getChildren().add(backgroundCanvas);
        root.getChildren().add(canvas);
        root.getChildren().add(guiCanvas);
        root.getChildren().add(pauseCanvas);

        double normalWidth = root.getLayoutBounds().getWidth();
        double normalHeight = root.getLayoutBounds().getHeight();

        if (!full) {
            if (testheight < height && testwidth < width) {
                root.setScaleX(testwidth / width);
                root.setScaleY(testheight / height);
                root.setTranslateX((testwidth - width) / 2);
                root.setTranslateY((testheight - height) / 2);
            } else if (testheight > height && testwidth > width) {
                root.setScaleX(testheight / height);
                root.setScaleY(testheight / height);
                root.setTranslateX((testwidth - width) / 2);
                root.setTranslateY((testheight - height) / 2);
            }
        } else {

            root.setScaleX(testheight / height);
            root.setScaleY(testheight / height);

            root.resizeRelocate(0, 0, testwidth, testheight);
            root.setLayoutX((primScreenBounds.getWidth() / 2) - ((testwidth / root.getScaleX()) / 2));
            root.setLayoutY(((primScreenBounds.getHeight() + 40) / 2) - ((testheight / root.getScaleY()) / 2));

        }
        canvas.autosize();
        guiCanvas.autosize();
        pauseCanvas.autosize();
        backgroundCanvas.autosize();

        gc = canvas.getGraphicsContext2D();
        gui = guiCanvas.getGraphicsContext2D();
        pause = pauseCanvas.getGraphicsContext2D();
        background = backgroundCanvas.getGraphicsContext2D();

        backGround = new Background("/levels/Background.png");
        pauseMenu = new Pause();
        intro = new Intro();
        comp = new CompanyFade();
        menu = new Menu();
        test = new Test1("untitled.tmx");
        credit = new Credits();

        xmlWriter.setFixed();

        companyFade();
    }

    public static void companyFade() {
        comp.animate();
    }

    public static void menu() {
        menu.animate();
    }

    public static void throwZip() {
        String resourceName = "/levelsandstuff.zip";
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {

            stream = Main.class.getResourceAsStream(resourceName);
            if (stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(jarFolder + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            System.out.println("ZIP THROW ERROR, this is serious!");
            login.error();
        } finally {
            try {
                stream.close();
                resStreamOut.close();
            } catch (IOException ex) {
                System.out.println("Did not close");
                login.error();
            }

        }
    }

}

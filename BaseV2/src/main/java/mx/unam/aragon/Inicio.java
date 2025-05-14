package mx.unam.aragon;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mx.unam.aragon.extra.Musica;
import mx.unam.aragon.modelo.*;



import java.io.IOException;
import java.net.URISyntaxException;

public class Inicio extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas hoja;
    private Fondo fondo;
    private Raton barra;
    private Stage stage;
    private BotellaUno botella;
    private Thread hiloEfecto=null;

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        componentesIniciar();
        pintar();
        stage.setScene(escena);
        eventosTeclado();
        stage.setTitle("Bar Shooter");
        stage.show();
        ciclo();
        this.stage=stage;
        Musica musica=new Musica("Musica_Bar_Shooter");
        hiloEfecto=new Thread(musica);
        hiloEfecto.start();


    }
    @Override
    public void stop() throws  Exception{
        hiloEfecto.stop();
    }

    private void ciclo() {
        long tiempoInicio = System.nanoTime();
        AnimationTimer tiempo = new AnimationTimer() {
            @Override
            public void handle(long tiempoActual) {
                double t = (tiempoActual - tiempoInicio) / 1000000000.0;
                calculosLogica();
                pintar();
            }
        };
        tiempo.start();
    }
    private void calculosLogica() {
        this.fondo.logicaCalculos();
        this.barra.logicaCalculos();
        this.botella.logicaCalculos();

    }

    private void pintar() {
        fondo.pintar(graficos);
        barra.pintar(graficos);
        botella.pintar(graficos);
    }

    private void componentesIniciar() throws URISyntaxException {
        root = new Group();
        escena = new Scene(root, 600, 600);
        hoja = new Canvas(600, 600);
        root.getChildren().add(hoja);
        graficos = hoja.getGraphicsContext2D();
        fondo = new Fondo(0, 0, "Fondo2_BarShooter.jpg", 3);
        barra=new Raton(300, 500, "", 3);
        botella=new BotellaUno(-15, -15, "Botella_Cerveza.png",0);
        }

    private void eventosTeclado() {
        escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                barra.teclado(arg0, true);

            }
        });
        escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                barra.teclado(arg0, false);
            }

        });

    }

    public static void main(String[] args) {
        launch();
    }

}
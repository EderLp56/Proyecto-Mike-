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
import mx.unam.aragon.extra.MusicaCiclica;
import mx.unam.aragon.modelo.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class Inicio extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas hoja;
    private Fondo fondo;
    private ConstruccionBloques bloques ;
    private Raton barra;
    private Pelota pelota;
    private Colisiones colisiones;
    private Thread hiloEfecto=null;
    private Stage stage;
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
        MusicaCiclica musica=new MusicaCiclica("musica_entrada");
        hiloEfecto=new Thread(musica);
        hiloEfecto.start();

    }
    @SuppressWarnings("deprecation")
    @Override
    public void stop() throws Exception {
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
        this.pelota.logicaCalculos();
        this.colisiones.colisionesPelotaBarra();
        if(pelota.getFueraPelota()){

//                bloques=new ConstruccionBloques(0,0,"",1,1,10);
//                barra=new Barra(300, 500, "", 3);
//                pelota=new Pelota(300,200,"",3);
//                colisiones=new Colisiones(bloques,barra,pelota);

            stage.close();
        }
    }

    private void pintar() {
        fondo.pintar(graficos);
        bloques.pintar(graficos);
        barra.pintar(graficos);
        pelota.pintar(graficos);
    }

    private void componentesIniciar() throws URISyntaxException {
        root = new Group();
        escena = new Scene(root, 600, 600);
        hoja = new Canvas(600, 600);
        root.getChildren().add(hoja);
        graficos = hoja.getGraphicsContext2D();
        fondo = new Fondo(0, 0, "Fondo2_BarShooter.jpg", 3);
        bloques=new ConstruccionBloques(0,0,"",1,1,10);
        barra=new Raton(300, 500, "", 3);
        pelota=new Pelota(300,200,"",3);
        colisiones=new Colisiones(bloques,barra,pelota);
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
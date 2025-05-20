package mx.unam.aragon;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import mx.unam.aragon.extra.Musica;
import mx.unam.aragon.modelo.*;
import java.util.Random;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Inicio extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas hoja;
    private Fondo fondo;
    private Raton barra;
    private Stage stage;
    private BotellaUno botella;
    private BotellaDos botellaDos;
    private Thread hiloEfecto = null;
    private BotellaTres botella3;
    private Colisiones colisiones;

    private ArrayList<Bala> balas = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        componentesIniciar();
        pintar();
        stage.setScene(escena);
        eventosTeclado();
        stage.setTitle("Bar Shooter");
        stage.show();
        ciclo();
        this.stage = stage;
        Musica musica = new Musica("Musica_Bar_Shooter");
        hiloEfecto = new Thread(musica);
        hiloEfecto.start();
    }

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
        fondo.logicaCalculos();
        barra.logicaCalculos();
        botella.logicaCalculos();
        botellaDos.logicaCalculos();
        botella3.logicaCalculos();


        Random rand = new Random();
        int anchoMaximo = 450; // Ancho máximo para la posición X (ajusta según tu escena)

        for (int i = balas.size() - 1; i >= 0; i--) {
            Bala bala = balas.get(i);
            bala.logicaCalculos();
            Rectangle rBala = new Rectangle(bala.getX(), bala.getY(), 16, 32);

            // Colisión con BotellaUno
            if (rBala.intersects(botella.getBotella().getBoundsInLocal())) {
                balas.remove(i);
                int randomX = rand.nextInt(anchoMaximo);
                botella.setX(randomX);
                botella.setY(-150);
            }

            // Colisión con BotellaDos
            if (rBala.intersects(botellaDos.getBotella().getBoundsInLocal())) {
                balas.remove(i);
                int randomX = rand.nextInt(anchoMaximo);
                botellaDos.setX(randomX);
                botellaDos.setY(-150);

            }

            // Colisión con BotellaTres
            if (rBala.intersects(botella3.getBotella().getBoundsInLocal())) {
                balas.remove(i);
                int randomX = rand.nextInt(anchoMaximo);
                botella3.setX(randomX);
                botella3.setY(-150);
            }

            // Si la bala está fuera de pantalla la eliminamos
            if (bala.isFueraPantalla()) {
                balas.remove(i);
            }
            if (botella.getFueraPantalla()||botellaDos.getFueraPantalla()||botella3.getFueraPantalla()){
                stage.close();
            }
        }
    }

    private void pintar() {
        fondo.pintar(graficos);
        barra.pintar(graficos);
        botella.pintar(graficos);
        botellaDos.pintar(graficos);
        botella3.pintar(graficos);
        for (Bala bala : balas) {
            bala.pintar(graficos);
        }
    }

    private void componentesIniciar() throws URISyntaxException {
        root = new Group();
        escena = new Scene(root, 500, 600);//y , x
        hoja = new Canvas(500, 600);
        root.getChildren().add(hoja);
        graficos = hoja.getGraphicsContext2D();
        fondo = new Fondo(0, 0, "Fondo2_BarShooter.jpg", 3);
        barra = new Raton(300, 500, "Raton_BarShooter.png", 3);
        botella = new BotellaUno(0, -150, "Botella_Cerveza.png", 0);
        botellaDos = new BotellaDos(90, -150, "Botella_Dos_BarShooter.png", 0);
        botella3=new BotellaTres(200, -150, "Botella3_Bar_Shooter.png", 0);

    }

    private void eventosTeclado() {
        escena.setOnKeyPressed(evento -> {
            barra.teclado(evento, true);

            if (evento.getCode() == javafx.scene.input.KeyCode.SPACE) {
                Bala nuevaBala = new Bala(
                        (int) barra.getX() + (int) barra.getBarra().getWidth() / 2 - 8,
                        (int) barra.getY(),
                        "Bala_Raton.png",
                        10
                );
                balas.add(nuevaBala);
            }
        });

        escena.setOnKeyReleased(evento -> barra.teclado(evento, false));
    }

    public static void main(String[] args) {
        launch();
    }
}


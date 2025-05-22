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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class Inicio extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas hoja;
    private Fondo fondo;
    private Raton barra;
    private Stage stage;
    private Thread hiloEfecto = null;
    private Contador contador;
    private boolean espacioPresionado = false;
    private ArrayList<BotellaBase> botellas = new ArrayList<>();
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
                double t = (tiempoActual - tiempoInicio) / 1_000_000_000.0;
                calculosLogica();
                pintar();
            }
        };
        tiempo.start();
    }

    private void calculosLogica() {
        fondo.logicaCalculos();
        barra.logicaCalculos();

        Random rand = new Random();
        int anchoMaximo = 450;

        for (int i = balas.size() - 1; i >= 0; i--) {
            Bala bala = balas.get(i);
            bala.logicaCalculos();
            Rectangle rBala = new Rectangle(bala.getX(), bala.getY(), 16, 32);

            for (BotellaBase botella : botellas) {
                if (rBala.intersects(botella.getBotella().getBoundsInLocal())) {
                    balas.remove(i);
                    botella.setX(rand.nextInt(anchoMaximo));
                    botella.setY(-150);
                    contador.aumentarPuntaje(botella.getValor());
                    break;
                }
            }

            if (bala.isFueraPantalla()) {
                balas.remove(i);
            }
        }

        for (BotellaBase botella : botellas) {
            botella.logicaCalculos();
            if (botella.getFueraPantalla()) {
                stage.close();
            }
        }
    }

    private void pintar() {
        fondo.pintar(graficos);
        barra.pintar(graficos);
        graficos.setFont(javafx.scene.text.Font.font("Verdana", 18));
        graficos.setFill(javafx.scene.paint.Color.WHITE);
        graficos.fillText("Puntaje: " + contador.getPuntaje(),  10,25);
        for (BotellaBase botella : botellas) {
            botella.pintar(graficos);
        }
        for (Bala bala : balas) {
            bala.pintar(graficos);
        }
    }

    private void componentesIniciar() throws URISyntaxException {
        root = new Group();
        escena = new Scene(root, 500, 600);
        hoja = new Canvas(500, 600);
        root.getChildren().add(hoja);
        graficos = hoja.getGraphicsContext2D();


        fondo = new Fondo(0, 0, "Fondo2_BarShooter.jpg", 3);
        barra = new Raton(300, 500, "Raton_BarShooter.png", 3);
        contador=new Contador();

        // Agregar múltiples botellas base
        botellas.add(new BotellaBase(0, -150, "Botella_Cerveza.png", 0, 6));
        botellas.add(new BotellaBase(90, -150, "Botella_Dos_BarShooter.png", 0, 9));
        botellas.add(new BotellaBase(200, -150, "Botella3_Bar_Shooter.png", 0, 10));
        botellas.add(new BotellaBase(400, -150, "Botella_Cuatro.png", 0, 3));
    }
    /*private void eventosTeclado() {
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
    }*/
    private void eventosTeclado() {
        escena.setOnKeyPressed(evento -> {
            barra.teclado(evento, true);

            if (evento.getCode() == javafx.scene.input.KeyCode.SPACE && !espacioPresionado) {
                espacioPresionado = true;

                Bala nuevaBala = new Bala(
                        (int) barra.getX() + (int) barra.getBarra().getWidth() / 2 - 8,
                        (int) barra.getY(),
                        "Bala_Raton.png",
                        10
                );
                balas.add(nuevaBala);
            }
        });

        escena.setOnKeyReleased(evento -> {
            barra.teclado(evento, false);

            if (evento.getCode() == javafx.scene.input.KeyCode.SPACE) {
                espacioPresionado = false;
            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}


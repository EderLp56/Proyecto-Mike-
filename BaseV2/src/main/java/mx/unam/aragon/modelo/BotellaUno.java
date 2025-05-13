package mx.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;
import java.util.Random;
/*
public class BotellaUno extends ComponentesJuego{
    private Rectangle botella;
    private Image imagen;


    private void iniciarComponente() {
        botella=new Rectangle(x, y, 80, 80);
        InputStream ruta= Raton.class.getResourceAsStream("Botella_Cerveza.png");
        imagen=new Image(ruta);
    }


    public BotellaUno(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        iniciarComponente();
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        graficos.strokeRect(botella.getX(), botella.getY(), botella.getWidth(),
                botella.getHeight());
        graficos.drawImage (imagen,botella.getX(), botella.getY(), botella.getWidth(),
                botella.getHeight());
    }

    @Override
    public void teclado(KeyEvent evento, boolean presiona) {

    }

    @Override
    public void raton(KeyEvent evento) {

    }

    @Override
    public void logicaCalculos() {

    }
}
*/


public class BotellaUno extends ComponentesJuego {
    private Rectangle botella;
    private Image imagen;
    private Random random;
    private int frameCounter = 0;
    private final int cambioIntervalo = 20; // Cambia de posición cada 100 frames

    private void iniciarComponente() {
        random = new Random();
        x = random.nextInt(600 - 80); // Posición aleatoria inicial
        y = random.nextInt(600 - 80);

        botella = new Rectangle(x, y, 80, 80);
        InputStream ruta = Raton.class.getResourceAsStream("Botella_Cerveza.png");
        imagen = new Image(ruta);
    }

    public BotellaUno(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        iniciarComponente();
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        graficos.strokeRect(botella.getX(), botella.getY(), botella.getWidth(), botella.getHeight());
        graficos.drawImage(imagen, botella.getX(), botella.getY(), botella.getWidth(), botella.getHeight());
    }

    @Override
    public void teclado(KeyEvent evento, boolean presiona) {
        // No se usa por ahora
    }

    @Override
    public void raton(KeyEvent evento) {
        // No se usa por ahora
    }

    @Override
    public void logicaCalculos() {
        frameCounter++;

        if (frameCounter >= cambioIntervalo) {
            x = random.nextInt(800 - 80);
            y = random.nextInt(600 - 80);
            botella.setX(x);
            botella.setY(y);
            frameCounter = 0;
        }
    }
}

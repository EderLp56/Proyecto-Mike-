package mx.unam.aragon.modelo;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import java.io.InputStream;

public class BotellaBase extends ComponentesJuego {
    private Rectangle botella;
    private Image imagenBotella;
    private double velocidadY = 0;
    private double gravedad = 0.2;
    private double velocidadMaxima = 2;
    private boolean fueraPantalla = false;
    private int valor;

    public BotellaBase(int x, int y, String rutaImagen, int velocidad, int valor) {
        super(x, y, rutaImagen, velocidad);
        this.valor=valor;
        iniciarComponente(rutaImagen);
    }

    private void iniciarComponente(String rutaImagen) {
        botella = new Rectangle(x, y, 50, 50);
        InputStream ruta = getClass().getResourceAsStream(rutaImagen);
        imagenBotella = new Image(ruta);
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        //graficos.strokeRect(botella.getX(), botella.getY(), botella.getWidth(), botella.getHeight());
        graficos.drawImage(imagenBotella, botella.getX(), botella.getY(), botella.getWidth(), botella.getHeight());
    }

    @Override
    public void teclado(KeyEvent evento, boolean presiona) {
        // No implementado
    }

    @Override
    public void raton(KeyEvent evento) {
        // No implementado
    }

    @Override
    public void logicaCalculos() {
        velocidadY += gravedad;
        if (velocidadY > velocidadMaxima) velocidadY = velocidadMaxima;
        y += velocidadY;
        botella.setY(y);

        if (y > 600) {
            fueraPantalla = true;
            Platform.exit();
        }
    }

    public Rectangle getBotella() {
        return botella;
    }

    public boolean getFueraPantalla() {
        return fueraPantalla;
    }

    public int getValor() {
        return valor;
    }

    public void setX(int nuevoX) {
        this.x = nuevoX;
        botella.setX(nuevoX);
    }

    public void setY(int nuevoY) {
        this.y = nuevoY;
        botella.setY(nuevoY);
    }
}
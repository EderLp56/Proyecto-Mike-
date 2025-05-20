package mx.unam.aragon.modelo;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import java.io.InputStream;
import java.util.Random;

public class BotellaDos extends ComponentesJuego {
    private Rectangle botella;
    private Image imagen;
    private double velocidadY = 0;  // Velocidad inicial de caída (empieza en 0)
    private double gravedad = 0.2;  // Gravedad más suave para caída lenta
    private double velocidadMaxima = 2; // Velocidad máxima de caída
    private Boolean fueraPantalla = false;

    public BotellaDos(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        iniciarComponente();
    }

    private void iniciarComponente() {
        botella = new Rectangle(x, y, 80, 80);
        InputStream ruta = BotellaDos.class.getResourceAsStream("Botella_Dos_BarShooter.png");
        imagen = new Image(ruta);
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        graficos.strokeRect(botella.getX(), botella.getY(), botella.getWidth(), botella.getHeight());
        graficos.drawImage(imagen, botella.getX(), botella.getY(), botella.getWidth(), botella.getHeight());
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

        if (velocidadY > velocidadMaxima) {
            velocidadY = velocidadMaxima;
        }

        y += velocidadY;
        botella.setY(y);

        if (y > 600) {
            fueraPantalla = true;
            y = 600;
            velocidadY = 0;
            Platform.exit();
        }
    }

    public void modificarVelocidad(double nuevaVelocidadY) {
        this.velocidadY = nuevaVelocidadY;
    }

    public void modificarGravedad(double nuevaGravedad) {
        this.gravedad = nuevaGravedad;
    }

    // Método para actualizar la posición X
    public void setX(int nuevoX) {
        this.x = nuevoX;
        botella.setX(nuevoX);
    }

    // Método para actualizar la posición Y
    public void setY(int nuevoY) {
        this.y = nuevoY;
        botella.setY(nuevoY);
    }

    public Rectangle getBotella() {
        return botella;
    }

    public Boolean getFueraPantalla() {
        return fueraPantalla;
    }
}





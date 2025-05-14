package mx.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import java.io.InputStream;

public class BotellaUno extends ComponentesJuego {
    private Rectangle botella;
    private Image imagen;
    private double velocidadY = 9;  // Velocidad inicial de caída
    private double gravedad = 2;  // Aceleración por gravedad más suave (ajustado a 0.2 para caída lenta)
    private double velocidadMaxima = 2; // Velocidad máxima para evitar caída demasiado rápida
    private Boolean fueraPantalla = false;

    public BotellaUno(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        iniciarComponente();
    }

    private void iniciarComponente() {
        botella = new Rectangle(x, y, 80, 80);
        InputStream ruta = BotellaUno.class.getResourceAsStream("Botella_Cerveza.png");
        imagen = new Image(ruta);
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        graficos.strokeRect(botella.getX(), botella.getY(), botella.getWidth(),
                botella.getHeight());
        graficos.drawImage(imagen, botella.getX(), botella.getY(), botella.getWidth(),
                botella.getHeight());
    }

    @Override
    public void teclado(KeyEvent evento, boolean presiona) {
        // Implementación de teclado si es necesaria
    }

    @Override
    public void raton(KeyEvent evento) {
        // Implementación de ratón si es necesaria
    }

    @Override
    public void logicaCalculos() {
        // Simula la caída por gravedad
        velocidadY += gravedad;  // Aceleración hacia abajo (gravedad)

        // Evitar que la velocidad de caída sea mayor que el límite
        if (velocidadY > velocidadMaxima) {
            velocidadY = velocidadMaxima;
        }

        y += velocidadY;         // Actualiza la posición en el eje Y
        botella.setY(y);         // Actualiza la posición del objeto en pantalla

        // Límites de pantalla (para evitar que la botella se salga)
        if (y > 600) {           // Si se pasa de la pantalla
            fueraPantalla = true;
            y = 600;             // Se ajusta a la base de la pantalla
            velocidadY = 0;      // Se detiene la caída
        }
    }

    // Métodos para modificar la velocidad y gravedad en tiempo real
    public void modificarVelocidad(double nuevaVelocidadY) {
        this.velocidadY = nuevaVelocidadY;
    }

    public void modificarGravedad(double nuevaGravedad) {
        this.gravedad = nuevaGravedad;
    }

    // Getters y Setters
    public Rectangle getBotella() {
        return botella;
    }

    public Boolean getFueraPantalla() {
        return fueraPantalla;
    }
}






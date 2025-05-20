package mx.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import java.io.InputStream;

public class Bala extends ComponentesJuego {
    private Rectangle bala;
    private boolean fueraPantalla = false;
    private int velocidad;
    private Image imagen;

    public Bala(int x, int y, String rutaImagen, int velocidad) {
        super(x, y, rutaImagen, velocidad);
        this.velocidad = velocidad;
        cargarImagen(rutaImagen);
        iniciarComponente();
    }

    private void cargarImagen(String rutaImagen) {
        InputStream is = getClass().getResourceAsStream(rutaImagen);
        if (is != null) {
            imagen = new Image(is);
        } else {
            System.err.println("No se pudo cargar la imagen de la bala: " + rutaImagen);
        }
    }

    private void iniciarComponente() {
        bala = new Rectangle(x, y, 10, 20); // Tamaño de la bala
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        if (imagen != null) {
            graficos.drawImage(imagen, bala.getX(), bala.getY(), bala.getWidth(), bala.getHeight());
        }
        // Si imagen es null, no pinta nada
    }

    @Override
    public void teclado(javafx.scene.input.KeyEvent evento, boolean presiona) {
        // No se usa en Bala
    }

    @Override
    public void raton(javafx.scene.input.KeyEvent evento) {
        // No se usa en Bala
    }

    @Override
    public void logicaCalculos() {
        y -= velocidad;  // Movimiento hacia arriba
        bala.setY(y);

        if (y + bala.getHeight() < 0) {
            fueraPantalla = true;
        }
    }

    public boolean isFueraPantalla() {
        return fueraPantalla;
    }

    public Rectangle getBala() {
        return bala;
    }
}


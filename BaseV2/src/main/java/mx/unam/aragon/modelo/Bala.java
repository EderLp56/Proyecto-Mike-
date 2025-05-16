package mx.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Bala extends ComponentesJuego {
    private Rectangle bala;
    private boolean fueraPantalla = false;
    private int velocidad;

    public Bala(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        this.velocidad = velocidad;
        iniciarComponente();
    }

    private void iniciarComponente() {
        bala = new Rectangle(x, y, 10, 20); // Bala pequeña y alargada
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        graficos.fillRect(bala.getX(), bala.getY(), bala.getWidth(), bala.getHeight());
    }

    @Override
    public void teclado(javafx.scene.input.KeyEvent evento, boolean presiona) {
        // No se usa en bala
    }

    @Override
    public void raton(javafx.scene.input.KeyEvent evento) {
        // No se usa en bala
    }

    @Override
    public void logicaCalculos() {
        y -= velocidad; // Mover hacia arriba
        bala.setY(y);

        // Cuando la bala sale por arriba, marcar para eliminar
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

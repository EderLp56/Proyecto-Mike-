package mx.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Pelota extends ComponentesJuego {
    private Rectangle pelota;
    private Boolean arriba = false;
    private Boolean abajo = false;
    private Boolean fueraPelota = false;
    private int grados;
    private int radio;

    public Pelota(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        this.radio = velocidad;
        iniciarComponente();
    }

    private void iniciarComponente() {
        pelota = new Rectangle(x, y, 20, 20);
        abajo = true;
        calcularGrados(320, 220);
    }

    public void calcularGrados(int maximo, int minimo) {
        int range = (maximo - minimo) + 1;
        grados = (int) ((range * Math.random()) + minimo);
    }


    @Override
    public void pintar(GraphicsContext graficos) {
        graficos.strokeRect(pelota.getX(), pelota.getY(), pelota.getWidth(),
                pelota.getHeight());
        graficos.fillOval(pelota.getX(), pelota.getY(), pelota.getWidth(),
                pelota.getHeight());
    }

    @Override
    public void teclado(KeyEvent evento, boolean presiona) {

    }

    @Override
    public void raton(KeyEvent evento) {

    }

    @Override
    public void logicaCalculos() {
        double radianes = (Math.PI / 180);
        x += (int) (radio * Math.cos(radianes * grados));
        y -= (int) (radio * Math.sin(radianes * grados));
        pelota.setX(x);
        pelota.setY(y);

        //limite de pantalla izquierda
        if (x <= 1) {
            if (arriba) {
                calcularGrados(90, 1);
            }
            if (abajo) {
                calcularGrados(360, 270);
            }
        }
        if (x >= 590) {
            if (arriba) {
                calcularGrados(180, 90);
            }
            if (abajo) {
                calcularGrados(270, 180);
            }
        }
        if (y > 600) {
            fueraPelota = true;
        }
    }

    public Rectangle getPelota() {
        return pelota;
    }

    public Boolean getAbajo() {
        return abajo;
    }

    public Boolean getArriba() {
        return arriba;
    }

    public void setArriba(Boolean arriba) {
        this.arriba = arriba;
    }

    public void setAbajo(Boolean abajo) {
        this.abajo = abajo;
    }

    public Boolean getFueraPelota() {
        return fueraPelota;
    }
}

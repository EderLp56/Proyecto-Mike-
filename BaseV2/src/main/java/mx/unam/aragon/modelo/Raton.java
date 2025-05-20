package mx.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;

public class Raton extends ComponentesJuego{
    private Rectangle barra;
    private boolean izquierda=false;
    private boolean derecha=false;
    private Image imagen;

    public Raton(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        iniciarComponente();
    }

    private void iniciarComponente() {
        barra=new Rectangle(x, y, 90, 96);
        InputStream ruta= Raton.class.getResourceAsStream("Raton_BarShooter.png");
        imagen=new Image(ruta);
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        graficos.strokeRect(barra.getX(), barra.getY(), barra.getWidth(),
                barra.getHeight());
        graficos.drawImage (imagen,barra.getX(), barra.getY(), barra.getWidth(),
                barra.getHeight());

    }

    @Override
    public void teclado(KeyEvent evento, boolean presiona) {
        if(presiona){

            switch (evento.getCode().toString()){
                case "RIGHT":
                    derecha = true;

                    break;
                case "LEFT":
                    izquierda = true;

                    break;

            }
        }else {
            derecha=false;
            izquierda=false;
        }

    }

    @Override
    public void raton(KeyEvent evento) {

    }

    @Override
    public void logicaCalculos() {
        if (izquierda) {
            x -= velocidad;
            if (x < 0) {
                x = 0;
            }
        }
        if (derecha) {
            x += velocidad;
            if (x + barra.getWidth() > 500) {
                x = (int) (500 - barra.getWidth());
            }
        }
        barra.setX(x);
    }

    public Rectangle getBarra() {
        return barra;
    }
}

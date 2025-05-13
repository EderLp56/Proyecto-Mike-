package mx.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;
import java.util.ArrayList;

public class ConstruccionBloques extends ComponentesJuego{
    private ArrayList<Bloque> bloques=new ArrayList<>();
    private int columnas;
    private int filas;

    public ConstruccionBloques(int x, int y, String imagen, int velocidad,int filas, int columnas ) {
        super(x, y, imagen, velocidad);
        this.filas=filas;
        this.columnas=columnas;
        construccion();
    }

    private void construccion() {
        int ancho=600/columnas;
        int alto=30;
        InputStream ruta=null;
        Image imagen=null;
        //arreglar el código, se debe dibujar las filas
        for (int y = 0; y <=60; y+=alto ) {
            switch(y){
                case 0:
                    ruta= Raton.class.getResourceAsStream("Botella_Cerveza.png");
                    imagen=new Image(ruta);
                    break;
                case 30:
                    ruta= Raton.class.getResourceAsStream("bloque2.png");
                    imagen=new Image(ruta);
                    break;
                case 60:
                    ruta= Raton.class.getResourceAsStream("bloque3.png");
                    imagen=new Image(ruta);
                    break;
            }
            for (int x = 0; x <= 600; x += ancho) {

                bloques.add(new Bloque(new Rectangle(x, y, ancho, alto), imagen));

            }
        }
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        for (Bloque bloque:bloques){
            graficos.strokeRect(bloque.getRectangulo().getX(),
                    bloque.getRectangulo().getY(),
                    bloque.getRectangulo().getWidth(),
                    bloque.getRectangulo().getHeight());
            graficos.drawImage(bloque.getImagen(),bloque.getRectangulo().getX(),
                    bloque.getRectangulo().getY(),
                    bloque.getRectangulo().getWidth(),
                    bloque.getRectangulo().getHeight());
        }
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

    public ArrayList<Bloque> getBloques() {
        return bloques;
    }
}

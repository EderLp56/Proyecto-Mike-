package mx.unam.aragon.modelo;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Bloque {
    private Rectangle rectangulo;
    private Image imagen;

    public Bloque(Rectangle rectangulo, Image imagen) {
        this.rectangulo = rectangulo;
        this.imagen = imagen;

    }

    public Rectangle getRectangulo() {
        return rectangulo;
    }

    public void setRectangulo(Rectangle rectangulo) {
        this.rectangulo = rectangulo;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}

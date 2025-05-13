package mx.unam.aragon.modelo;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Circulo {
    private Circle circulo;
    private Image imagen;

    public Circulo(Circle circulo, Image imagen) {
        this.circulo = circulo;
        this.imagen = imagen;
    }

    public Circle getCirculo() {
        return circulo;
    }

    public void setCirculo(Circle circulo) {
        this.circulo = circulo;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}

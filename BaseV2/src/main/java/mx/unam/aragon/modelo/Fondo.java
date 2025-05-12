package mx.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.io.InputStream;
import java.net.URISyntaxException;

public class Fondo extends ComponentesJuego {
	private int xx = -600;
	private Image imagen;
	private Image imagenDos;

	public Fondo(int x, int y, String imagen, int velocidad) throws URISyntaxException {
		super(x, y, imagen, velocidad);
		InputStream ruta=Fondo.class.getResourceAsStream(imagen);
		this.imagen=new Image(ruta);
		ruta=Fondo.class.getResourceAsStream("Fondo2_BarShooter.jpg");
		this.imagenDos=new Image(ruta);
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		
		graficos.drawImage(imagen, x, y);
		graficos.drawImage(imagenDos, xx, y);

	}

	@Override
	public void teclado(KeyEvent evento,boolean presiona) {
		// TODO Auto-generated method stub

	}

	@Override
	public void raton(KeyEvent evento) {

	}

	@Override
	public void logicaCalculos() {
		x += velocidad;
		xx += velocidad;
		if (x >= 600) {
			x = -600;
		}
		if (xx >= 600) {
			xx = -600;
		}

	}

}

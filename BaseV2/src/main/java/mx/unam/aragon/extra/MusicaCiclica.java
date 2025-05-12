/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.aragon.extra;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.net.URISyntaxException;

/**
 *
 * @author Administrador
 */
public class MusicaCiclica implements Runnable {

	private BufferedInputStream buffer = null;
	private Player player = null;
	private InputStream archivo;
	private String nombreArchivo;

	public MusicaCiclica(String archivo) {
		this.nombreArchivo = archivo;
        InputStream ruta= MusicaCiclica.class.getResourceAsStream(archivo+".mp3");

        this.archivo = ruta;
        //this.getClass().getResource("/unam/aragon/mx/basevideojuego/modelo/" + nombreArchivo + ".mp3").toURI().getPath());
    }

	@Override
	public void run() {
		try {
			buffer = new BufferedInputStream(archivo);
			player = new Player(buffer);
			player.play();
			while (true) {
				if (player.isComplete()) {
					archivo.close();
                    InputStream ruta= MusicaCiclica.class.getResourceAsStream(archivo+".mp3");
                    this.archivo = ruta;
                    buffer = new BufferedInputStream(archivo);
					player = new Player(buffer);
					player.play();

				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

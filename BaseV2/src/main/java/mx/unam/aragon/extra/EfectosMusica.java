/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.aragon.extra;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author Administrador
 */
public class EfectosMusica implements Runnable {
	private BufferedInputStream buffer = null;
	private InputStream archivo;
	private Player player = null;

	public EfectosMusica(String archivo) {
        InputStream ruta= EfectosMusica.class.getResourceAsStream(archivo+".mp3");
        this.archivo = ruta;


    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			buffer = new BufferedInputStream(this.archivo);
			player = new Player(buffer);
			player.play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

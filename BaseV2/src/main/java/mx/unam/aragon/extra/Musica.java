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
public class Musica implements Runnable {

    private BufferedInputStream buffer = null;
    private Player player = null;
    private InputStream archivo;
    private String nombreArchivo;

    public Musica(String archivo) {
        this.nombreArchivo = archivo;
        InputStream ruta=
                Musica.class.getResourceAsStream(archivo+".mp3");

        this.archivo = ruta;
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
                    InputStream ruta=
                            Musica.class.getResourceAsStream(archivo+".mp3");
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
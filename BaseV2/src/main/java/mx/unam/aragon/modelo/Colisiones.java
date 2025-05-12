package mx.unam.aragon.modelo;

import mx.unam.aragon.extra.EfectosMusica;

public class Colisiones {
    private ConstruccionBloques bloques;
    private Raton barra;
    private Pelota pelota;

    public Colisiones(ConstruccionBloques bloques, Raton barra, Pelota pelota) {
        this.bloques = bloques;
        this.barra = barra;
        this.pelota=pelota;
    }
    public void colisionesPelotaBarra(){
        if(pelota.getPelota().getBoundsInLocal().intersects(
                barra.getBarra().getBoundsInLocal()
        )){
            if(pelota.getAbajo()){
                pelota.setAbajo(false);
                pelota.setArriba(true);
                pelota.calcularGrados(180,1);
            }
        }
        Bloque quitarBloque=null;
        for(Bloque bloque:bloques.getBloques()){
            if(bloque.getRectangulo().getBoundsInLocal().intersects(
                    pelota.getPelota().getBoundsInLocal()
            )){
                quitarBloque=bloque;
                pelota.setAbajo(true);
                pelota.setArriba(false);
                pelota.calcularGrados(360,180);
                EfectosMusica efectosMusica=new EfectosMusica("disparo");
                Thread hiloEfecto=new Thread(efectosMusica);
                hiloEfecto.start();
                break;

            }
        }
        bloques.getBloques().remove(quitarBloque);

    }

}

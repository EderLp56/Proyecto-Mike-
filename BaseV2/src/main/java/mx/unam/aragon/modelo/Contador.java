package mx.unam.aragon.modelo;

public class Contador {
    private int puntaje;

    public Contador() {
        this.puntaje=0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    public void reiniciar(){
        this. puntaje =0;
    }

    @Override
    public String toString() {
        return "Puntaje actual:" +puntaje;
    }

    public void aumentarPuntaje(int puntos) {
        this.puntaje += puntos;
    }
}


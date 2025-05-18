package mx.unam.aragon.modelo;

import java.util.Iterator;
import java.util.List;

public class Colisiones {
    private List<Bala> balas;
    private List<BotellaUno> botellas;

    public Colisiones(List<Bala> balas, List<BotellaUno> botellas) {
        this.balas = balas;
        this.botellas = botellas;
    }

    public void detectarColisiones() {
        Iterator<Bala> iterBalas = balas.iterator();

        while (iterBalas.hasNext()) {
            Bala bala = iterBalas.next();
            boolean impacto = false;

            Iterator<BotellaUno> iterBotellas = botellas.iterator();
            while (iterBotellas.hasNext()) {
                BotellaUno botella = iterBotellas.next();

                if (bala.getBala().getBoundsInLocal().intersects(botella.getBotella().getBoundsInLocal())) {
                    iterBotellas.remove();  // Eliminar botella
                    impacto = true;
                    break;
                }
            }

            if (impacto) {
                iterBalas.remove();  // Eliminar bala
            }
        }
    }
}


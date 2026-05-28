package ferrogest.core;

import ferrogest.exceptions.CapacidadExcedidaException;
import ferrogest.exceptions.MercanciaInvalidaException;
import ferrogest.domain.TrenMercancias;
import ferrogest.domain.TrenPasajeros;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorFerroviarioTest {

    @Test
    void isIdUnique() {
        // 1. Arrange (Preparar)
        GestorFerroviario gf = new GestorFerroviario();
        TrenPasajeros tren = new TrenPasajeros("T001", 100.0, 50.0, 5);
        gf.registrarTren(tren); // Metemos el tren en el sistema

        // 2. Act (Ejecutar)
        boolean existe = gf.isIdUnique("T001"); // Preguntamos por el que acabamos de meter
        boolean noExiste = gf.isIdUnique("T002"); // Preguntamos por uno inventado

        // 3. Assert (Comprobar)
        assertFalse(existe);   // Exigimos que T001 NO sea único (porque ya está dentro)
        assertTrue(noExiste);  // Exigimos que T002 SÍ sea único
    }

    @Test
    void procesarCarga() throws MercanciaInvalidaException {
        GestorFerroviario gf = new GestorFerroviario();
        TrenMercancias tren = new TrenMercancias("T001",100.0,"Peligrosa");
        gf.registrarTren(tren);
        assertThrows(CapacidadExcedidaException.class, () ->{
            gf.procesarCarga("T001", 150.0);
        });
    }

    @Test
    void calcularCosteFlota(){
        GestorFerroviario gf = new GestorFerroviario();
        TrenPasajeros t1 = new TrenPasajeros("T001", 100.0, 50.0, 5);
        TrenPasajeros t2 = new TrenPasajeros("T002", 100.0, 50.0, 5);
        gf.registrarTren(t1);
        gf.registrarTren(t2);
        double costeEsperado = 100.0;
        double costeReal = gf.calcularCosteFlota(100.0);
        assertEquals(costeEsperado, costeReal,0.01);
    }
}
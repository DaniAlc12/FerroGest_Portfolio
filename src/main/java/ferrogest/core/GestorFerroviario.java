package ferrogest.core;

import ferrogest.dao.TrenDAO;
import ferrogest.exceptions.CapacidadExcedidaException;
import ferrogest.domain.Tren;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorFerroviario {
    private Map<String, Tren> listaTrenes = new HashMap<>();
    private TrenDAO trenDAO =  new TrenDAO();

    public GestorFerroviario() {

        List<Tren> trenesGuardados = trenDAO.leerTrenes();

        for(Tren t : trenesGuardados){
            listaTrenes.put(t.getId(), t);
        }
    }

    public boolean isIdUnique(String id) {
        boolean idOk = true;
        if (listaTrenes.containsKey(id)) {
            idOk = false;
        }
        return idOk;
    }

    public void registrarTren(Tren t) {
        listaTrenes.put(t.getId(), t);
        trenDAO.insertarTren(t);
    }

    public void procesarCarga(String idTren, double cantidad) throws CapacidadExcedidaException {
        Tren t = listaTrenes.get(idTren);
        t.cargar(cantidad);
    }

    public String generarInformeAuditoria() {
        StringBuilder sb = new StringBuilder();
        for (Tren t : listaTrenes.values()) {
            sb.append(t.generarRegistro());
            sb.append("\n");
        }
        String informeAuditoria = sb.toString();
        return informeAuditoria;
    }

    public double calcularCosteFlota(double kilometros){
        return listaTrenes.values().stream().mapToDouble(t -> t.calcularCosteRuta(kilometros)).sum();
    }

    public void guardarAuditoriaTexto(String rutaFichero) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero));) {
            String auditoriaTexto = generarInformeAuditoria();
            bw.write(auditoriaTexto);
        }
    }

}

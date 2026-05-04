package ferrogest.controller;

import ferrogest.exceptions.CapacidadExcedidaException;
import ferrogest.models.Tren;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GestorFerroviario {
    private Map<String, Tren> listaTrenes;

    public GestorFerroviario() {
        listaTrenes = new HashMap<>();
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

    public void guardarEstadoSistema(String rutaFichero) throws IOException {
        try (ObjectOutputStream escribiendo_estado = new ObjectOutputStream(new FileOutputStream(rutaFichero));) {
            escribiendo_estado.writeObject(listaTrenes);
        }
    }

    public void cargarEstadoSistema(String rutaFichero) throws ClassNotFoundException, IOException {
        try (ObjectInputStream recuperando_estado = new ObjectInputStream(new FileInputStream(rutaFichero));) {
            listaTrenes = (Map<String,Tren>) recuperando_estado.readObject();
        }
    }

}

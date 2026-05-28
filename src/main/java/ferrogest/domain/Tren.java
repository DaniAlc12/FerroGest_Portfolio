package ferrogest.domain;

import ferrogest.exceptions.CapacidadExcedidaException;
import java.io.Serializable;

public abstract class Tren implements Serializable,Auditable{
    private String id;
    private double capacidadMaxima;
    private double pesoActual;

    public Tren(String id, double capacidadMaxima) {
        this.id = id;
        this.capacidadMaxima = capacidadMaxima;
        this.pesoActual = 0.0;
    }

    public boolean cargar(double cantidad) throws CapacidadExcedidaException {
        boolean isPesoOk = true;
        if ((getPesoActual() + cantidad) > getCapacidadMaxima()){
            double capacidadExcedida = ((getPesoActual() + cantidad) - getCapacidadMaxima());
            String mensajeError=">[CAPACIDAD MAXIMA EXCEDIDA.\nID TREN: " + id
                    + "\nCAPACIDAD MAXIMA:" + getCapacidadMaxima() +"]"
                    + "\nCAPACIDAD EXCEDIDA:" + capacidadExcedida +"]";
            throw new CapacidadExcedidaException(mensajeError , getId());
        }
        setPesoActual(getPesoActual() + cantidad);
        return isPesoOk;
    }

    public abstract double calcularCosteRuta(double kilometros);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(double capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
}

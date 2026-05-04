package ferrogest.exceptions;

public class CapacidadExcedidaException extends Exception {
    private String id;
    private double capacidadExcedida;

    public CapacidadExcedidaException(String message, String id) {
        super(message);
        this.id = id;
    }

    public double getCapacidadExcedida() {
        return capacidadExcedida;
    }

    public void setCapacidadExcedida(double capacidadExcedida) {
        this.capacidadExcedida = capacidadExcedida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

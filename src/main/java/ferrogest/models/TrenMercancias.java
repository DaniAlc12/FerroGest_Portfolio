package ferrogest.models;

import ferrogest.exceptions.MercanciaInvalidaException;

public class TrenMercancias extends Tren{
    private String tipoMercancia;

    public TrenMercancias(String id, double capacidadMaxima, String tipoMercancia) throws MercanciaInvalidaException {
        super(id, capacidadMaxima);
        if (tipoMercancia.equals("Peligrosa") ||
                tipoMercancia.equals("Perecedera") ||
                        tipoMercancia.equals("Estandar")) {
            this.tipoMercancia = tipoMercancia;
        }else{
            throw new MercanciaInvalidaException(">[ TIPO DE MERCANCIA INTRODUCIDA NO APTA]");
        }
    }

    @Override
    public double calcularCosteRuta(double kilometros) {
        double costeRuta;
        if(this.tipoMercancia.equals("Peligrosa")){
            costeRuta = (kilometros * getPesoActual()) * 2.5;
        }else if(this.tipoMercancia.equals("Perecedera")){
            costeRuta = (kilometros * getPesoActual()) * 1.5;
        }else{
            costeRuta = (kilometros * getPesoActual()) * 1;
        }

        return costeRuta;
    }

    @Override
    public String generarRegistro() {
        return "|TREN CON ID:" + this.getId() +
                "\nCAPACIDAD MAXIMA DE:" + this.getCapacidadMaxima() +
                "\nTIPO DE MERCANCIA:" + this.tipoMercancia;
    }

    public String getTipoMercancia() {
        return tipoMercancia;
    }

    public void setTipoMercancia(String tipoMercancia) {
        this.tipoMercancia = tipoMercancia;
    }
}

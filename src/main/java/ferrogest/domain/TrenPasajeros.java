package ferrogest.domain;

public class TrenPasajeros extends Tren {
    private int numVagones;
    private double precioBilleteBase;

    public TrenPasajeros(String id, double capacidadMaxima, double precioBilleteBase, int numVagones) {
        super(id, capacidadMaxima);
        this.precioBilleteBase = precioBilleteBase;
        this.numVagones = numVagones;
    }

    @Override
    public double calcularCosteRuta(double kilometros) {
        double costeDeRuta=(kilometros * 0.5) + (getPesoActual() * getPrecioBilleteBase());
        return costeDeRuta;
    }

    @Override
    public String generarRegistro() {
        return "|TREN CON ID:" + this.getId() +
                "\nCAPACIDAD MAXIMA DE:" + this.getCapacidadMaxima() +
                "\nPRECIO BILLETE BASE DE:" + this.precioBilleteBase +
                "\nNUMERO DE VAGONES:" + this.numVagones;
    }

    public int getNumVagones() {
        return numVagones;
    }

    public void setNumVagones(int numVagones) {
        this.numVagones = numVagones;
    }

    public double getPrecioBilleteBase() {
        return precioBilleteBase;
    }

    public void setPrecioBilleteBase(double precioBilleteBase) {
        this.precioBilleteBase = precioBilleteBase;
    }
}

package ferrogest.dao;
import ferrogest.exceptions.MercanciaInvalidaException;
import ferrogest.domain.Tren;
import ferrogest.domain.TrenMercancias;
import ferrogest.domain.TrenPasajeros;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TrenDAO {

    public void insertarTren(Tren tren){
        String sql = "INSERT INTO trenes (id, tipo, capacidad_maxima, peso_actual, precio_billete, num_vagones, tipo_mercancia) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = ConexionBD.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tren.getId());
            pstmt.setDouble(3, tren.getCapacidadMaxima());
            pstmt.setDouble(4, tren.getPesoActual());
            if(tren instanceof TrenMercancias){
                TrenMercancias m = (TrenMercancias)tren;

                pstmt.setString(2, "Mercancias");
                pstmt.setNull(5, Types.DOUBLE);
                pstmt.setNull(6, Types.INTEGER);
                pstmt.setString(7, m.getTipoMercancia());
            }else if(tren instanceof TrenPasajeros){
                TrenPasajeros p = (TrenPasajeros)tren;

                pstmt.setString(2, "Pasajeros");
                pstmt.setDouble(5, p.getPrecioBilleteBase());
                pstmt.setInt(6, p.getNumVagones());
                pstmt.setNull(7, java.sql.Types.VARCHAR);
            }

            pstmt.executeUpdate();
            System.out.println("Registro guardado exitosamente");

        }catch(SQLException e){
            System.out.println("[!] Error al guardar en base de datos: " + e.getMessage());
        }
    }

    public List<Tren> leerTrenes(){
        List<Tren> trenes = new ArrayList<>();
        String sql = "SELECT * FROM trenes";

        try (Connection conn = ConexionBD.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()){
                String id = rs.getString("id");
                String tipo = rs.getString("tipo");
                double capacidad = rs.getDouble("capacidad_maxima");

                if(tipo.equals("Mercancias")){

                    String tipoMercancia = rs.getString("tipo_mercancia");
                    TrenMercancias tm = new TrenMercancias(id,capacidad,tipoMercancia);
                    trenes.add(tm);
                }else if(tipo.equals("Pasajeros")){
                    double precio = rs.getDouble("precio_billete");
                    int vagones = rs.getInt("num_vagones");

                    TrenPasajeros tp = new TrenPasajeros(id, capacidad, precio, vagones);
                    trenes.add(tp);
                }
            }

        } catch(SQLException e){
            System.out.println("[!] Error al leer de la base de datos: " + e.getMessage());
        } catch (MercanciaInvalidaException e) {
            throw new RuntimeException(e);
        }

        return trenes;
    }
}

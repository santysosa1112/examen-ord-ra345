package org.lapaloma.examen.aaee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.lapaloma.examen.aaee.dao.IMinisterioDAO;
import org.lapaloma.examen.aaee.vo.Ministerio;
import org.springframework.stereotype.Repository;

@Repository
public class MinisterioDaoJDBC implements IMinisterioDAO {
    private final DataSource dataSource;

    // Spring inyecta el DataSource configurado automáticamente
    public MinisterioDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Ministerio> obtenerListaMinisterios() {

        List<Ministerio> lista = new ArrayList<>();

        String sentenciaSQL = """
                SELECT * FROM ministerio
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            System.out.println(sentenciaJDBCPreparada);

            ResultSet resultadoSentencia = sentenciaJDBCPreparada.executeQuery();

            while (resultadoSentencia.next()) {
                lista.add(getLineaFromResultSet(resultadoSentencia));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


    
    private Ministerio getLineaFromResultSet(ResultSet resultadoSentencia) throws SQLException {

        Ministerio ministerio = new Ministerio();

        ministerio.setIdentificador(resultadoSentencia.getInt("codMinisterio"));
        ministerio.setNombre(resultadoSentencia.getString("nombre"));
        ministerio.setPresupuesto(resultadoSentencia.getDouble("presupuesto"));
        ministerio.setGastos(resultadoSentencia.getDouble("gastos"));

        return ministerio;
    }

    private Ministerio obtenerMinisterio(String sentenciaSQL, Object parametro) {
        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            sentenciaJDBCPreparada.setObject(1, parametro);
            System.out.println(sentenciaJDBCPreparada);

            try (ResultSet resultadoSentencia = sentenciaJDBCPreparada.executeQuery()) {
                if (resultadoSentencia.next()) {
                    return getLineaFromResultSet(resultadoSentencia);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Ministerio obtenerMinisterioPorNombre(String nombre) {
        String sentenciaSQL = "SELECT * FROM ministerio WHERE nombre = ?";
        return obtenerMinisterio(sentenciaSQL, nombre);
    }

    @Override
    public Ministerio obtenerMinisterioPorIdentificador(int identificador) {
        String sentenciaSQL = "SELECT * FROM ministerio WHERE codMinisterio = ?";
        return obtenerMinisterio(sentenciaSQL, identificador);
    }
}

package org.lapaloma.examen.aaee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.lapaloma.examen.aaee.dao.IMiembroDAO;
import org.lapaloma.examen.aaee.vo.Miembro;
import org.lapaloma.examen.aaee.vo.Ministerio;
import org.springframework.stereotype.Repository;

@Repository
public class MiembroDaoJDBC implements IMiembroDAO {
    private final DataSource dataSource;

    // Spring inyecta el DataSource configurado automáticamente
    public MiembroDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Miembro> obtenerListaMiembros() {

        List<Miembro> lista = new ArrayList<>();

        String sentenciaSQL = """
                SELECT * FROM miembro
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

    @Override
    public Miembro obtenerMiembroPorNombre(String nombre) {

        String sentenciaSQL = """
                SELECT * FROM miembro WHERE nombre = ?
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            sentenciaJDBCPreparada.setString(1, nombre);

            System.out.println(sentenciaJDBCPreparada);

            ResultSet resultadoSentencia = sentenciaJDBCPreparada.executeQuery();

            if (resultadoSentencia.next()) {
                return getLineaFromResultSet(resultadoSentencia);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Miembro obtenerMiembroPorAlias(String alias) {

        String sentenciaSQL = """
                SELECT * FROM miembro WHERE alias = ?
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            sentenciaJDBCPreparada.setString(1, alias);

            System.out.println(sentenciaJDBCPreparada);

            ResultSet resultadoSentencia = sentenciaJDBCPreparada.executeQuery();

            if (resultadoSentencia.next()) {
                return getLineaFromResultSet(resultadoSentencia);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Miembro getLineaFromResultSet(ResultSet resultadoSentencia) throws SQLException {

        Miembro miembro = new Miembro();

        miembro.setIdentificador(resultadoSentencia.getInt("codMiembro"));
        miembro.setNif(resultadoSentencia.getString("nif"));
        miembro.setNombre(resultadoSentencia.getString("nombre"));
        miembro.setApellido1(resultadoSentencia.getString("apellido1"));
        miembro.setAlias(resultadoSentencia.getString("alias"));

        // Obtener el código del ministerio
        int codigoMinisterio = resultadoSentencia.getInt("codigoMinisterio");
        
        // Si codigoMinisterio es válido (no es 0), crear objeto Ministerio
        if (codigoMinisterio != 0) {
            Ministerio ministerio = new Ministerio();
            ministerio.setIdentificador(codigoMinisterio);
            miembro.setMinisterio(ministerio);
        }

        return miembro;
    }
}

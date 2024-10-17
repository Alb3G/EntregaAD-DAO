package com.entrega.dao;


import com.entrega.model.Movie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de película DAO donde implementamos la interfaz para gestionar
 * el crud de peliculas.
 * @author Alberto Guzman Moreno
 */
public class MovieDAO implements DAO<Movie> {
    private static Connection connection = null;
    private static final String INSERT_MOVIE = "INSERT INTO pelicula (titulo,año,genero) values (?,?,?);";


    public MovieDAO(Connection con) {connection = con;}


    @Override
    public List<Movie> findAll() {
        return null;
    }

    @Override
    public Movie findById(Integer id) {
        return null;
    }

    /**
     * Método para guardar película en la base de datos.
     * con Statement.RETURN_GENERATED_KEYS controlamos que los ids de las
     * películas guardadas sean las correctas.
     * @param movie Película a guardar.
     */
    @Override
    public void save(Movie movie) {
        try(var ps = connection.prepareStatement(INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getYear());
            ps.setString(3, movie.getGenre());
            if(ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                movie.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Movie movie) {
        //Todo
    }

    @Override
    public void delete(Movie movie) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    /**
     * Método que devuelve el total de peliculas que hay en la tabla.
     * @return Integer representación numérica del número de peliculas.
     */
    public Integer movieCount() {
        int res;
        try(var st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM pelicula;");
            if(rs.next())
                res = rs.getInt(1);
            else
                res = 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * Método para filtrar películas por el género pasado por parámetro.
     * @param genre argumento para filtrar las películas.
     * @return List<Movie> películas filtradas.
     */
    public List<Movie> filterMovieByGenre(String genre) {
        List<Movie> res = new ArrayList<>();
        try(var ps = connection.prepareStatement("Select * from pelicula where genero = ?;")) {
            ps.setString(1, genre);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                res.add(new Movie(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}

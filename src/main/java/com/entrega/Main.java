package com.entrega;

import com.entrega.dao.MovieDAO;
import com.entrega.model.Movie;

public class Main {
    public static void main(String[] args) {
        MovieDAO dao = new MovieDAO(Db.getConn());

        Movie m = new Movie(null,"Prueba5", 2003, "drama");
        dao.save(m);

        dao.filterMovieByGenre("drama").forEach(System.out::println);
        System.out.println(dao.movieCount());
    }
}

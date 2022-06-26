package com.example.filmsearch.repository

import com.example.filmsearch.model.Movie

class RepositoryImpl: Repository {

    override fun getMoviesFromServer(): List<Movie> {
        return listOf(
            Movie("dsfsdgds",
                "Star Wars: The Rise of Skywalker",
                "dgsgsd",
                "dfdafa",
                "94",
                "dfasfa",
                "gdgsd",
                "fgdsfgd",
                "fgdfadf"),
            Movie("dsfsdgds",
                "Home Alone",
                "dgsgsd",
                "dfdafa",
                "45",
                "dfasfa",
                "gdgsd",
                "fgdsfgd",
                "fgdfadf"),
            Movie("dsfsdgds",
                "Star Wars: The Rise",
                "dgsgsd",
                "dfdafa",
                "94",
                "dfasfa",
                "gdgsd",
                "fgdsfgd",
                "fgdfadf"),
            Movie("dsfsdgds",
                "Star wars: The Rise",
                "dgsgsd",
                "dfdafa",
                "61",
                "dfasfa",
                "gdgsd",
                "fgdsfgd",
                "fgdfadf")
        )
    }
}
package com.example.day11.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.day11.model.Movie;
import com.example.day11.service.MovieService;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService service;
    // public MovieController(MovieService service) {
    //     this.service = service;
    // }   

    @GetMapping("/movies")
    public List<Movie> getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ) {
        return service.getMovies(page, size);
    }


    //url: http://localhost:8080/movies?page=0&size=10
    //200: OK
    //404: Not Found
    //500: Internal Server Error
    //400: Bad Request
    //201: Created
    //401: Unauthorized

}
package com.bookmyshow.demo.controller;

import com.bookmyshow.demo.dtos.TheatreDTO;
import com.bookmyshow.demo.entities.Theatre;
import com.bookmyshow.demo.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping("/addTheatre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> addTheatre(@RequestBody TheatreDTO theatreDTO){
        return ResponseEntity.ok(theatreService.addTheatre(theatreDTO));
    }

    @GetMapping("/getTheatreByLocation")
    public ResponseEntity<List<Theatre>> getTheatreByLocation(@RequestParam String location){
        return ResponseEntity.ok(theatreService.getTheatreByLocation(location));
    }

    @PutMapping("/updateTheatre/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theatre> updateMovie(@PathVariable Long id, @RequestBody TheatreDTO theatreDTO){
        return ResponseEntity.ok(theatreService.updateTheatre(id,theatreDTO));
    }

    @DeleteMapping("/deleteTheatre/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        theatreService.deleteMovie(id);

        return ResponseEntity.ok().build();
    }
}

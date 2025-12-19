package com.bookmyshow.demo.controller;

import com.bookmyshow.demo.dtos.ShowDTO;
import com.bookmyshow.demo.entities.Show;
import com.bookmyshow.demo.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> addShow(@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.addShow(showDTO));
    }

    @DeleteMapping("/deleteShow/{id}")
    @PreAuthorize("hasRole('ADMIN)")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id){
        showService.deleteMovie(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getShowByMovie/{id}")
    public ResponseEntity<List<Show>> getShowByMovie(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowByMovie(id));
    }

    @GetMapping("/getShowByTheatre/{id}")
    public ResponseEntity<List<Show>> getShowByTheatre(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowByTheatre(id));
    }

    @PutMapping("/updateShow/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Show> updateShow(@PathVariable Long id,@RequestBody ShowDTO showDTO){
        return ResponseEntity.ok(showService.updateShow(id,showDTO));
    }

    @GetMapping("/getAllShows")
    public ResponseEntity<List<Show>> getALlShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }

}

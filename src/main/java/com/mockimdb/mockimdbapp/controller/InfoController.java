package com.mockimdb.mockimdbapp.controller;

import com.mockimdb.mockimdbapp.entity.Movie;
import com.mockimdb.mockimdbapp.request.CoincidenceRequest;
import com.mockimdb.mockimdbapp.service.InfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/info")
public class InfoController {

    private InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/typecasted/{name}")
    public boolean isTypeCasted(@PathVariable String name) {
        return infoService.isTypeCasted(name);
    }

    @PostMapping("/find-coincidence")
    public List<Movie> findCoincidence(@RequestBody CoincidenceRequest coincidenceRequest){
        return infoService.findCoincidences(coincidenceRequest);
    }

    @PostMapping("/degree-of-kevin-bacon/{name}")
    public Long degreeOfKevinBacon(@PathVariable String name){
        return infoService.findDegreeOfKevinBacon(name);
    }
}

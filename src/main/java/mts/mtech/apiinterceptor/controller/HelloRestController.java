package mts.mtech.apiinterceptor.controller;

import mts.mtech.apiinterceptor.dto.sample.SearchCriteria;
import mts.mtech.apiinterceptor.services.sample.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloRestController {
    private final SampleService sampleService;

    public HelloRestController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name){
        return String.format("Hello, %s", name);
    }

    @GetMapping(value = "/teams")
    public ResponseEntity<List<SearchCriteria>> getTeams() {
        var result = sampleService.getSoccerTeams();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

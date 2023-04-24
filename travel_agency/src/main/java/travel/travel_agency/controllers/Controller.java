package travel.travel_agency.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
@RequiredArgsConstructor
public class Controller {
    @GetMapping
    public ResponseEntity<String> sayOk() {
        return ResponseEntity.ok("Hello!");
    }
}
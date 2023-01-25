package de.claudioaltamura.springboot.ib;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperheroController {

    @GetMapping("/")
    public String get() {
        return "a superhero";
    }

}

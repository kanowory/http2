package pl.edu.wszib.http2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test(Model model, @RequestParam(required = false) String imie) {
        model.addAttribute("imie", imie);

        List<Integer> cyfry = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("cyfry", cyfry);

        return "test";
    }
}

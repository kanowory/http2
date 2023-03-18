package pl.edu.wszib.http2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/kolorki")
public class KolorkiController {

    enum Kolor {
        CZERWONY,
        NIEBIESKI,
        ZIELONY,
        CZARNY,
        ZOLTY,
        BRAZOWY
    }

    @GetMapping
    public String test(Model model,@RequestParam(required = false) Kolor kolor) {
        if (kolor == null){
            kolor = Kolor.CZARNY;
        }

        switch (kolor) {
            case CZERWONY:
                model.addAttribute("kolor", "#ff0000");
                break;
            case ZIELONY:
                model.addAttribute("kolor", "#00ff00");
                break;
            case NIEBIESKI:
                model.addAttribute("kolor", "#0000ff");
                break;
            case ZOLTY:
                model.addAttribute("kolor", "#FFD64D");
                break;
            case BRAZOWY:
                model.addAttribute("kolor", "#6E4030");
                break;
            case CZARNY:
            default:
                model.addAttribute("kolor", "#000000");
        }

        return "kolory";
    }
}

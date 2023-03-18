package pl.edu.wszib.http2;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/galeria")
public class GaleriaController {

    public static class Obraz{
        public byte[] zawartosc;
        public String nazwa;
        public String mimeType;
    }

    private List<Obraz> obrazy = new ArrayList<>();

    @GetMapping
    public String galeria(Model model) {
        model.addAttribute("obrazy", obrazy);
        return "galeria";
    }

    @PostMapping
    public String wrzuc(@RequestParam("obraz")MultipartFile file) throws IOException {
       if(file.isEmpty()){
           return "redirect:/galeria";
       }
        Obraz nowy = new Obraz();
        nowy.nazwa = file.getOriginalFilename();
        nowy.zawartosc = file.getBytes();
        nowy.mimeType = file.getContentType();
        obrazy.add(nowy);
        return "redirect:/galeria";
    }

    @GetMapping("/{nazwa}")
    @ResponseBody
    public ResponseEntity<byte[]> zawartosc(@PathVariable String nazwa) {
        for (Obraz o : obrazy) {
            if (o.nazwa.equals(nazwa)) {
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.parseMediaType(o.mimeType))
                        .body(o.zawartosc);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new byte[0]);

    }


}

package br.com.fiap.consultafacil.consulta;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("consultas")
public class ConsultaController {

    @GetMapping
    public String index(Model model) {
        return "consulta/index";
    }
    
}

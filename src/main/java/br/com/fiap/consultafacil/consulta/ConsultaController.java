package br.com.fiap.consultafacil.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    ConsultaRepository repository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("consultas", repository.findAll());
        return "consulta/index";
    }

    @GetMapping("delete/{id}")           
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {

        var consulta = repository.findById(id);

        if (consulta.isEmpty()) {
            redirect.addFlashAttribute("message", "Erro ao apagar a consulta");
            return "redirect:/consultas";
        }

        redirect.addFlashAttribute("message", "Consulta, apagada com sucesso");
        repository.deleteById(id);
        return "redirect:/consultas";
    }

    @GetMapping("new")
    public String form() {
        return "consulta/form";
    }

    @PostMapping
    public String create(Consulta consulta, RedirectAttributes redirect) {
        repository.save(consulta);
        redirect.addFlashAttribute("message", "Consulta, cadastrada com sucesso");
        return "redirect:/consultas";
    }
    
    
}

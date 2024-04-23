package br.com.fiap.consultafacil.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.consultafacil.user.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("consultas")
@Slf4j
public class ConsultaController {

    @Autowired
    ConsultaRepository repository;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal DefaultOAuth2User user) {
        User myuser = (User) user;
        log.info("usuario carregado: "+ myuser);

        model.addAttribute("consultas", repository.findAll());
        model.addAttribute("user", user.getAttribute("name"));
        model.addAttribute("avatar", user.getAttribute("avatar_url"));
        return "consulta/index";
    }

    @GetMapping("{id}")           
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {

        var consulta = repository.findById(id);

        if (consulta.isEmpty()) {
            redirect.addFlashAttribute("message", "Erro ao apagar a consulta");
            return "redirect:/consultas";
        }

        repository.deleteById(id);
        redirect.addFlashAttribute("message", messageSource.getMessage("consulta.delete", null, LocaleContextHolder.getLocale()));
        return "redirect:/consultas";
    }


    @GetMapping("new")
    public String form(Consulta consulta) {
        return "consulta/form";
    }

    @PostMapping
    public String create(@Valid Consulta consulta,BindingResult result, RedirectAttributes redirect) {

        if (result.hasErrors()) return "consulta/form";

        repository.save(consulta);
        redirect.addFlashAttribute("message", "Consulta, cadastrada com sucesso");
        return "redirect:/consultas";
    }
    
    
}

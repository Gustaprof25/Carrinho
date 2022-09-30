package webII.aula8.segundo_controller.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import webII.aula8.segundo_controller.model.entity.Produto;
import webII.aula8.segundo_controller.model.entity.Venda;
import webII.aula8.segundo_controller.model.repository.VendaRepository;


@Transactional
@Controller
@RequestMapping("vendas")
public class VendasController {

    @Autowired
    VendaRepository repository;


    /**
     * @param produto
     * @param venda
     * @return
     */
    @GetMapping("/form")
    public String form(Produto produto){
        return "/vendas/form";
    }

    @GetMapping("list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", repository.Vendas());
        return new ModelAndView("/vendas/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Venda venda){
        repository.save(venda);
        return new ModelAndView("redirect:/vendas/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/vendas/list");
    }

    /**
     * @param id
     * @param model
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/vendas/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        repository.update(venda);
        return new ModelAndView("redirect:/vendas/list");
    }
 

/**
 * @Configuration para indicar ao Spring que essa é uma classe de configuração.
 * Em seguida, é preciso estender a classe WebMvcConfigurerAdapter.
 * @author fagno
 */
@Configuration
public class ConfiguracaoSpringMvc implements WebMvcConfigurer{

    
    /**
     * Com a chamada a registry.addViewController(), estamos registrando um controller automático,
     * definido pelo próprio Spring, para atender a requisições direcionadas à URL / e /home. E com a chamada 
     * a setViewName(), sempre que a aplicação receber uma requisição para um desses endereços, a view home, 
     * criada na última aula, será exibida.
     * @param registry 
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");

    }

}

}


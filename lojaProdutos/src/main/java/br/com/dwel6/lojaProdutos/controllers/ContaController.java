package br.com.dwel6.lojaProdutos.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.com.dwel6.lojaProdutos.models.AppUser;
import br.com.dwel6.lojaProdutos.models.RegisterDTO;
import br.com.dwel6.lojaProdutos.services.UserRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ContaController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute(registerDTO);
        model.addAttribute("success", false);
        System.out.println("Passei no GET");
        return "cadastroUser";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDTO registerDTO, BindingResult result) {
       
        if(!registerDTO.getPassword().equals(registerDTO.getConfirmPassword()))
        {
            result.addError(new FieldError("registerDTO","confirmPassword","Senha e confirmar senha não estão compatíveis."));
        }

        if(result.hasErrors())
        {
            return "cadastroUser";
        }


        try {
            //cria nova conta
            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser novoUser = new AppUser();
            novoUser.setPrimeiroNome(registerDTO.getPrimeiroNome());
            novoUser.setSobrenome(registerDTO.getSobrenome());
            novoUser.setEmail(registerDTO.getEmail());
            novoUser.setPhone(registerDTO.getPhone());
            novoUser.setAdress(registerDTO.getAddress());
            novoUser.setRole("operador");
            novoUser.setCreatedAt(new Date());
            novoUser.setPassword(bCryptEncoder.encode(registerDTO.getPassword()));

            userRepository.save(novoUser);

            model.addAttribute("registerDTO", new RegisterDTO());
            model.addAttribute("success", true);

        } catch (Exception ex) {
         result.addError(
            new FieldError("registerDTO","primeiroNome", ex.getMessage())
         );   
        }
       
        return "cadastroUser";
    }
    
}
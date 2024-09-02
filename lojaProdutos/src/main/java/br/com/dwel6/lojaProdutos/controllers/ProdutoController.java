package br.com.dwel6.lojaProdutos.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.dwel6.lojaProdutos.models.Produto;
import br.com.dwel6.lojaProdutos.models.ProdutoDTO;
import br.com.dwel6.lojaProdutos.services.ProdutoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping({"","/"})
    public String showProdutctList (Model model)
    {
        List<Produto> produtos = produtoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("produtos", produtos);
        return "produtos/produtos";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model)
    {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        model.addAttribute("produtoDTO", produtoDTO);
        return "produtos/criarProduto";
    }

    @PostMapping("/create")
    public String createProduto(@Valid @ModelAttribute ProdutoDTO produtoDTO, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "produtos/criarProduto";
        }

        Produto produto = new Produto();
        Date createdAt = new Date();
        
        produto.setNome(produtoDTO.getNome());
        produto.setMarca(produtoDTO.getMarca());
        produto.setCategoria(produtoDTO.getMarca());
        produto.setPreco(produtoDTO.getPreco());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setCreatedAt(createdAt);

        produtoRepository.save(produto);

        return "redirect:/produtos";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id)
    {
        try {
            Produto produto = produtoRepository.findById(id).get();
            model.addAttribute("produto", produto);

            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setNome(produto.getNome());
            produtoDTO.setMarca(produto.getMarca());
            produtoDTO.setCategoria(produto.getCategoria());
            produtoDTO.setPreco(produto.getPreco());
            produtoDTO.setDescricao(produto.getDescricao());

            model.addAttribute("produtoDTO", produtoDTO);
        }
        catch(Exception ex)
        {
            System.out.println("Exception:" + ex.getMessage());
            return "redirect:/produtos";
        }
        return "produtos/editarProduto";
    }

    @PostMapping("/edit")
    public String updateProduto(Model model, @RequestParam int id, @Valid @ModelAttribute ProdutoDTO produtoDTO,BindingResult result)
    {
        try {
            Produto produto = produtoRepository.findById(id).get();
            model.addAttribute("produto", produto);

            if(result.hasErrors())
            {
                return "produtos/editarProduto";
            }

            produto.setNome(produtoDTO.getNome());
            produto.setMarca(produtoDTO.getMarca());
            produto.setCategoria(produtoDTO.getCategoria());
            produto.setPreco(produtoDTO.getPreco());
            produto.setDescricao(produtoDTO.getDescricao());
            produtoRepository.save(produto);
            System.out.println("Passei no update!");

        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
        }
        return "redirect:/produtos";
    }

    @GetMapping ("/delete")
    public String deleteProduto(@RequestParam int id)
    {
        try {
            Produto produto = produtoRepository.findById(id).get();

            produtoRepository.delete(produto);
        } catch (Exception ex) {
            // TODO: handle exception
            System.out.println("Exception: " + ex.getMessage());
        }

        return ("redirect:/produtos");
    }
}

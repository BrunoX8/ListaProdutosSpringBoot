package br.com.dwel6.lojaProdutos.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProdutoDTO {
    
    @NotEmpty(message = "O nome é obrigatório")
    private String nome;

    @NotEmpty(message = "A marca é obrigatória")
    private String marca;

    @NotEmpty(message = "A categoria é obrigatória")
    private String categoria;

    @Min(0)
    private double preco;

    @Size(min = 10, message = "A descrição deve ter ao menos 10 caracteres!")
    @Size(max = 2000, message = "A descrição não pode exceder 2000 caracteres!")
    private String descricao;

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public double getPreco()
    {
        return preco;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }
}

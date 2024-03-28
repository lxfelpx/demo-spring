package ifpr.luis.controller;


import ifpr.luis.model.Categoria;
import ifpr.luis.model.Produto;
import ifpr.luis.repository.CategoriaRepository;
import ifpr.luis.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
//caminho
@RequestMapping(path = "/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping(path="/add")
    public String add(
            @RequestParam String nome,
            @RequestParam String descricao,
            @RequestParam int quantidadeEstoque,
            @RequestParam Long fkIdCategoria
    ){
        Optional<Categoria> categoria = categoriaRepository.findById(fkIdCategoria);
        if(categoria.isPresent()){
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setQuantidadeEstoque(quantidadeEstoque);
            produto.setCategoria(categoria.get());
            produtoRepository.save(produto);
            return produto + "[Salvo]";
        }else{
            return "[Erro] - Categoria não encontrada";
        }

    }

    @GetMapping(path="/all")
    public Iterable<Produto> findAll(){
        return produtoRepository.findAll();
    }

    @PostMapping(path="/update")
    public String update(
            @RequestParam Long id,
            @RequestParam int quantidadeEstoque
    ){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()){
            produto.get().setQuantidadeEstoque(quantidadeEstoque);
            produtoRepository.save(produto.get());
            return "Atualizado";
        }else{
            return "Produto não encontrado";
        }
    }

    @GetMapping(path="/alterados")
    public Iterable<Produto> findAlterados(){
        Date lastDay = new Date(System.currentTimeMillis() - (3600 * 1000 * 24));
        return produtoRepository.findByUpdatedAtAfter(lastDay);
    }

    @GetMapping(path="/pegarporcategoria")
    public Iterable<Produto> pegarPorCategoria(
            @RequestParam Long fkIdCategoria
    ){
        Optional<Categoria> categoria = categoriaRepository.findById(fkIdCategoria);
        if(categoria.isPresent()) {
            return produtoRepository.findByCategoria(categoria.get());
        } else{
            return null;
        }
    }
}

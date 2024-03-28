package ifpr.luis.controller;

import ifpr.luis.model.Categoria; // Importe sua classe de modelo Categoria
import ifpr.luis.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// caminho
@RequestMapping(path = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping(path = "/add")
    public String addCategoria(
            @RequestParam String nome
    ){
        // Crie uma nova instância de Categoria com o nome fornecido
        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        // Salve a nova categoria no repositório
        categoriaRepository.save(categoria);

        // Retorne uma mensagem
        return "Categoria adicionada com sucesso!";
    }
}

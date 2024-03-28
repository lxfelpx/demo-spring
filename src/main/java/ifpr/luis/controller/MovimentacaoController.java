package ifpr.luis.controller;

import ifpr.luis.model.Movimentacao;
import ifpr.luis.model.Produto;
import ifpr.luis.model.Usuario;
import ifpr.luis.repository.MovimentacaoRepository;
import ifpr.luis.repository.ProdutoRepository;
import ifpr.luis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping(path = "/add")
    public String addMovimentacao(
            @RequestParam String acao,
            @RequestParam int quantidade,
            @RequestParam Long fkIdUsuario,
            @RequestParam Long fkIdProduto
    ) {
        Optional<Usuario> usuario = usuarioRepository.findById(fkIdUsuario);
        Optional<Produto> produto = produtoRepository.findById(fkIdProduto);

        if (usuario.isPresent() && produto.isPresent()) {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setAcao(acao);
            movimentacao.setQuantidade(quantidade);
            movimentacao.setUsuario(usuario.get());
            movimentacao.setProduto(produto.get());
            movimentacaoRepository.save(movimentacao);

            // Verifica se a ação é uma compra e atualiza a quantidade em estoque do produto
            if (acao.equalsIgnoreCase("compra")) {
                Produto produtoAtualizado = produto.get();
                produtoAtualizado.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque() - quantidade);
                produtoRepository.save(produtoAtualizado);
            }

            return "Movimentação salva com sucesso";
        } else {
            return "[Erro] - Usuário ou Produto não encontrado";
        }
    }
}

package ifpr.luis.repository;

import ifpr.luis.model.Categoria;
import ifpr.luis.model.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

//Contrato de métodos sem implementação específica.
public interface ProdutoRepository extends CrudRepository <Produto, Long> {
    Produto findProdutoById(Long id);

    Iterable<Produto> findByUpdatedAtAfter(Date lastDay);

    Iterable<Produto> findByCategoria(Categoria categoria);
}

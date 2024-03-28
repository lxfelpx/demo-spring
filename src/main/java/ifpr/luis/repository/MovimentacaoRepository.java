package ifpr.luis.repository;

import ifpr.luis.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Long> {
    // Se necessário, adicione métodos personalizados para consultas específicas
}

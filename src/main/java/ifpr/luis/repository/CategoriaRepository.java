package ifpr.luis.repository;
import ifpr.luis.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    Categoria findCategoriaById(Long id);
}

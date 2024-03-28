package ifpr.luis.repository;

import ifpr.luis.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

//Contrato de métodos sem implementação específica.
public interface UsuarioRepository extends CrudRepository <Usuario, Long> {

    Usuario findByEmail(String email);
    boolean existsByEmail(String email);

    Usuario findByEmailAndSenha(String email, String senha);

    Iterable<Usuario> findByDataNascimentoIsBetween(Date start, Date end);

    default Iterable<Usuario> findByUltimoLoginAfter(Date ultimoLogin) {
        return null;
    }
}

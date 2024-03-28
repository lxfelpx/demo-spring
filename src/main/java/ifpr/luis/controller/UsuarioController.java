package ifpr.luis.controller;

import ifpr.luis.model.Usuario;
import ifpr.luis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
//caminho
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(path="/add")
    public String addNewUser(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date dataNascimento
    ){
        if (usuarioRepository.findByEmail(email) != null){
            return "Email já existe";
        }else {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setDataNascimento(dataNascimento);
            usuarioRepository.save(usuario);
            return usuario + " salvo";
        }
    }

    @GetMapping(path="/all")
    public Iterable<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }

    @GetMapping(path="/pegarporemail")
    public Usuario pegarPorEmail(
            @RequestParam String email
    ){
        return usuarioRepository.findByEmail(email);
    }

    @PostMapping(path="/verificalogin")
    public String verificaLogin(
            @RequestParam String email,
            @RequestParam String senha
    ){
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        if(usuario != null){
            usuario.setUltimoLogin(new Date(System.currentTimeMillis()));
            usuarioRepository.save(usuario);
            return "Logou";
        }else{
            return "Não Logou";
        }
    }

    @GetMapping(path="/nascimento")
    public Iterable<Usuario> pegarAniversariantes(
            @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date dataInicio,
            @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date dataFim
    ){
        return usuarioRepository.findByDataNascimentoIsBetween(dataInicio, dataFim);
    }

    @GetMapping(path="/{id}")
    public Optional<Usuario> pegarPorId(
            @PathVariable Long id
    ){
        return usuarioRepository.findById(id);
    }

    @PostMapping(path="/atualizardatanascimento")
    public String atualizarDataNascimento(
            @RequestParam Long id,
            @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy") Date dataNascimento
    ){
        Optional <Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            usuario.get().setDataNascimento(dataNascimento);
            usuarioRepository.save(usuario.get());
            return "Atualizou";
        }else{
            return "Não Encontrou";
        }
    }

    @GetMapping(path="/pegarlogados")
    public Iterable<Usuario> getLogados(){
        Date lastDay = new Date(System.currentTimeMillis() - (1000*3600*24));
        return usuarioRepository.findByUltimoLoginAfter(lastDay);
    }
}
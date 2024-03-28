package ifpr.luis.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acao")
    private String acao;

    @Column(name = "quantidade")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "fk_id_produto", nullable = false)
    private Produto produto;

    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movimentacao that)) return false;
        return quantidade == that.quantidade && Objects.equals(id, that.id) && Objects.equals(acao, that.acao) && Objects.equals(usuario, that.usuario) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, acao, quantidade, usuario, produto);
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", acao='" + acao + '\'' +
                ", quantidade=" + quantidade +
                ", usuario=" + usuario +
                ", produto=" + produto +
                '}';
    }
}

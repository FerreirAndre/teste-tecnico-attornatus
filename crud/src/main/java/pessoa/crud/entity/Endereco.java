package pessoa.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "numeroCasa")
    private Integer numeroCasa;
    @Column(name="cep")
    private String cep;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "principal")
    private Boolean principal;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}

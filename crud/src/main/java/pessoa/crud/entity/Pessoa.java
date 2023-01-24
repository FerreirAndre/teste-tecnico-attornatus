package pessoa.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco", referencedColumnName = "id")
    private List<Endereco> endereco;
}
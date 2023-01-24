package pessoa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pessoa.crud.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}

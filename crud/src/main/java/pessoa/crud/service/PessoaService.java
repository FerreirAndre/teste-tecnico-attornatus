package pessoa.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pessoa.crud.entity.Pessoa;
import pessoa.crud.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listaPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscaPorId(Long id) {
        return pessoaRepository.findById(id.longValue());
    }

    public void deletaPessoa(Long id) {
        pessoaRepository.deleteById(id.longValue());
    }

}

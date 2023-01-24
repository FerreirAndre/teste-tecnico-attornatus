package pessoa.crud.http.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pessoa.crud.entity.Endereco;
import pessoa.crud.entity.Pessoa;
import pessoa.crud.service.PessoaService;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar(@RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> listaPessoas() {
        return pessoaService.listaPessoas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa buscaPorId(@PathVariable("id") Long id) {
        return pessoaService.buscaPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaPessoa(@PathVariable("id") Long id) {
        pessoaService.buscaPorId(id)
                .map(pessoa -> {
                    pessoaService.deletaPessoa(pessoa.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado, impossível deletar."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editarPessoa(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {
        pessoaService.buscaPorId(id).map(pessoaBase -> {
            verificaPessoa(pessoaBase, pessoa);
            pessoaService.salvar(pessoa);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada, impossível editar"));
    }

    public Pessoa verificaPessoa(Pessoa pessoaBase, Pessoa pessoaNova) {
        pessoaNova.setId(pessoaBase.getId());

        int count = 0;
        for (Endereco endereco : pessoaNova.getEndereco()) {
            pessoaNova.getEndereco().get(count).setId(pessoaBase.getEndereco().get(count).getId());
            count++;
        }
        return pessoaNova;
    }
}

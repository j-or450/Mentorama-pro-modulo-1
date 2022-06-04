package br.com.mentorama.cadastro;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cadastro")
public class AlunoController {

    private List<Aluno> alunos = new ArrayList<>();

    @GetMapping
    public List<Aluno> findAll(@RequestParam(required = false) Integer id, String nome, Integer idade) {
        List<Aluno> alunosFilter = alunos;

        if(id != null){
            alunosFilter = alunosFilter.stream()
                    .filter(alunoId -> alunoId.getId().equals(id))
                    .collect(Collectors.toList());
        }
        if(nome != null){
            alunosFilter = alunosFilter.stream()
                    .filter(alunoNome -> alunoNome.getNome().contains(nome))
                    .collect(Collectors.toList());
        }
        if(idade != null){
            alunosFilter = alunosFilter.stream()
                    .filter(alunoIdade -> alunoIdade.getIdade().equals(idade))
                    .collect(Collectors.toList());
        }
        return alunosFilter;
    }

    @GetMapping("/{id}")
    public Aluno aluno(@PathVariable("id") Integer id){
        Optional<Aluno> alunoFinal = alunos.stream().filter(estudante -> estudante.getId().equals(id)).findFirst();

        if(alunoFinal.isPresent()){
            return alunoFinal.get();
        }
        return null;
    }

    @GetMapping("/list")
    public List<Aluno> list(){
        return alunos;
    }

    @PostMapping
    public Aluno aluno(@RequestBody Aluno aluno){
        if(aluno.getId() == null){
            aluno.setId(alunos.size() + 1);
        }
        alunos.add(aluno);
        return aluno;
    }

    @PutMapping
    public void update(@RequestBody Aluno aluno){
        alunos.stream()
                .filter(estudanteNome -> estudanteNome.getId().equals(aluno.getId()))
                .forEach(estudanteNome -> estudanteNome.setNome(aluno.getNome()));
        alunos.stream()
                .filter(estudanteIdade -> estudanteIdade.getId().equals(aluno.getId()))
                .forEach(estudanteIdade -> estudanteIdade.setIdade(aluno.getIdade()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        alunos.removeIf(deleteId -> deleteId.getId().equals(id));
    }
}

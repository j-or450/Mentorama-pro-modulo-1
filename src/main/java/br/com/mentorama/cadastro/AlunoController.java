package br.com.mentorama.cadastro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cadastro")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> findAll(@RequestParam(required = false) Integer id, String nome, Integer idade) {
        return alunoService.findAll(id,nome,idade);
    }

    @GetMapping("/{id}")
    public Aluno finById(@PathVariable("id") Integer id){
        return alunoService.finById(id);
    }

    @PostMapping
    public Aluno save(@RequestBody Aluno aluno){
       return alunoService.save(aluno);
    }

    @PutMapping
    public void update(@RequestBody Aluno aluno){
         alunoService.update(aluno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        alunoService.delete(id);
    }
}

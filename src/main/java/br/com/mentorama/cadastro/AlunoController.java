package br.com.mentorama.cadastro;

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

    @GetMapping("/get1")
    public ResponseEntity<String> get1(){
        throw new RecursoInexistenteException();
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(@RequestParam(required = false) Integer id, String nome, Integer idade){
        List<Aluno> list = alunoService.findAll(id,nome,idade);
            try {
                return ResponseEntity.ok().body(list);
            }catch (RuntimeException e){
                throw new RecursoInexistenteException();
            }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> finById(@PathVariable("id") Integer id) {
        Aluno al = alunoService.finById(id);
        return ResponseEntity.ok().body(al);

    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno){
       Aluno al = alunoService.save(aluno);
       return ResponseEntity.ok().body(al);
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

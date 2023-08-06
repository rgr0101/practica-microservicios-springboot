//Desarrollo de Microservicios con Java
//Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import crud.roger.crud.dto.PacienteDTO;
import crud.roger.crud.services.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //Metodo para crear un paciente
    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteGuardado = pacienteService.createPaciente(pacienteDTO);
        return ResponseEntity.ok(pacienteGuardado);
    }

    //Metodo para buscar un pacinete por su id
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.getPacienteById(id);
        if (pacienteDTO != null) {
            return ResponseEntity.ok(pacienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Actualizar un paciente por su id
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO pacienteActualizado = pacienteService.updatePaciente(id, pacienteDTO);
        if (pacienteActualizado != null) {
            return ResponseEntity.ok(pacienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    //Eliminar paciente por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.noContent().build();
    }

    //Obtener todos los paciente
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        List<PacienteDTO> pacientes = pacienteService.getAllPacientes();
        if (!pacientes.isEmpty()) {
            return ResponseEntity.ok(pacientes);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
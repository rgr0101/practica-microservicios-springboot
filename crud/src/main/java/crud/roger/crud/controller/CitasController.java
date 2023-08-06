// Desarrollo de Microservicios con Java
// Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import crud.roger.crud.dto.CitasDTO;
import crud.roger.crud.services.CitaService;

@RestController
@RequestMapping("/citas")
public class CitasController {
    private final CitaService citaService;

    @Autowired
    public CitasController(CitaService citaService) {
        this.citaService = citaService;
    }

    //Crear una cita con un paciente existente
    @PostMapping
    public ResponseEntity<CitasDTO> createCita(@RequestBody CitasDTO citaDTO) {
        CitasDTO nuevaCita = citaService.createCitaWithExistingPaciente(citaDTO);
        return ResponseEntity.ok(nuevaCita);
    }

    //Obtener la cita por id
    @GetMapping("/{id}")
    public ResponseEntity<CitasDTO> getCitaById(@PathVariable Long id) {
        CitasDTO citaDTO = citaService.getCitaById(id);
        if (citaDTO != null) {
            return ResponseEntity.ok(citaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Actualizar una cita
    @PutMapping("/{id}")
    public ResponseEntity<CitasDTO> updateCita(@PathVariable Long id, @RequestBody CitasDTO citaDTO) {
        CitasDTO citaActualizada = citaService.updateCita(id, citaDTO);
        return ResponseEntity.ok(citaActualizada);
    }

    //Eliminar cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }
}
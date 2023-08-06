// Desarrollo de Microservicios con Java
// Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crud.roger.crud.dto.CitasDTO;
import crud.roger.crud.entity.CitaEntity;
import crud.roger.crud.entity.PacienteEntity;
import crud.roger.crud.repository.CitaRepository;
import crud.roger.crud.repository.PacienteRepository;
import java.util.Optional;

@Service
public class CitaService {
    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository, PacienteRepository pacienteRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public CitasDTO createCitaWithExistingPaciente(CitasDTO citaDTO) {
        Long pacienteId = citaDTO.getPacienteId();
        Optional<PacienteEntity> pacienteOptional = pacienteRepository.findById(pacienteId);
        if (pacienteOptional.isPresent()) {
            CitaEntity nuevaCita = new CitaEntity();
            BeanUtils.copyProperties(citaDTO, nuevaCita); //Copiar citaDTO a nuevaCita

            //Paciente existente a la nueva cita
            nuevaCita.setPaciente(pacienteOptional.get());

            //Guardar nueva cita en la base de datos
            CitaEntity citaGuardada = citaRepository.save(nuevaCita);

            //Mapeamos la entidad de la cita guardada a un DTO y lo devolvemos
            CitasDTO citaCreadaDTO = new CitasDTO();
            BeanUtils.copyProperties(citaGuardada, citaCreadaDTO);
            citaCreadaDTO.setPacienteId(pacienteId); // Asignar el ID del paciente al DTO de la cita

            return citaCreadaDTO;
        } else {
            throw new RuntimeException("El paciente con ID " + pacienteId + " no existe.");
        }
    }

    public CitasDTO getCitaById(Long id) {
        CitaEntity cita = citaRepository.findById(id).orElse(null);
        CitasDTO citaDTO = new CitasDTO();
        if (cita != null) {
            BeanUtils.copyProperties(cita, citaDTO);
            citaDTO.setPacienteId(cita.getPaciente().getId()); //Asigna el ID del paciente al DTO de la cita
        }
        return citaDTO;
    }

    public CitasDTO updateCita(Long id, CitasDTO citaDTO) {
        CitaEntity citaExistente = citaRepository.findById(id).orElse(null);
        if (citaExistente != null) {
            //Copiar los datos, excepto el ID del DTO a la entidad
            BeanUtils.copyProperties(citaDTO, citaExistente, "id");
    
            //Mantener la relación con el paciente existente
            Long pacienteId = citaDTO.getPacienteId();
            Optional<PacienteEntity> pacienteOptional = pacienteRepository.findById(pacienteId);
            if (pacienteOptional.isPresent()) {
                citaExistente.setPaciente(pacienteOptional.get());
            } else {
                throw new RuntimeException("El paciente con ID " + pacienteId + " no existe.");
            }
    
            citaRepository.save(citaExistente);
            return citaDTO;
        } else {
            throw new RuntimeException("La cita con ID " + id + " no existe.");
        }
    }

    public void deleteCita(Long id) {
        citaRepository.deleteById(id);
    }
}
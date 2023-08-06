//Desarrollo de Microservicios con Java
//Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crud.roger.crud.dto.PacienteDTO;
import crud.roger.crud.entity.PacienteEntity;
import crud.roger.crud.repository.PacienteRepository;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteDTO createPaciente(PacienteDTO pacienteDTO) {
        PacienteEntity paciente = new PacienteEntity();
        BeanUtils.copyProperties(pacienteDTO, paciente);

        PacienteEntity pacienteGuardado = pacienteRepository.save(paciente);
        PacienteDTO pacienteGuardadoDTO = new PacienteDTO();
        BeanUtils.copyProperties(pacienteGuardado, pacienteGuardadoDTO);

        return pacienteGuardadoDTO;
    }

    public PacienteDTO getPacienteById(Long id) {
        PacienteEntity paciente = pacienteRepository.findById(id).orElse(null);
        PacienteDTO pacienteDTO = new PacienteDTO();
        if (paciente != null) {
            BeanUtils.copyProperties(paciente, pacienteDTO);
        }
        return pacienteDTO;
    }

    public PacienteDTO updatePaciente(Long id, PacienteDTO pacienteDTO) {
        PacienteEntity pacienteExistente = pacienteRepository.findById(id).orElse(null);

        if (pacienteExistente != null) {
            BeanUtils.copyProperties(pacienteDTO, pacienteExistente);
            pacienteRepository.save(pacienteExistente);
            return pacienteDTO;
        } else {
            return null;
        }
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public List<PacienteDTO> getAllPacientes() {
        List<PacienteEntity> pacientes = pacienteRepository.findAll();
        List<PacienteDTO> pacientesDTO = new ArrayList<>();

        for (PacienteEntity paciente : pacientes) {
            PacienteDTO pacienteDTO = new PacienteDTO();
            BeanUtils.copyProperties(paciente, pacienteDTO);
            pacientesDTO.add(pacienteDTO);
        }

        return pacientesDTO;
    }
}
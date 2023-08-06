// Desarrollo de Microservicios con Java
// Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitasDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String tipoPaciente;
    private String estatusCita;
    private int numeroSesion;
    private double costoTerapia;
    private Long pacienteId;
}

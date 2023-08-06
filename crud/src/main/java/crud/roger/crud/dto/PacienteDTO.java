//Desarrollo de Microservicios con Java
//Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.dto;

import java.sql.Date;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private Long id;
    private String noExpediente;
    private Date fechaCreacionExpediente;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private String estadoCivil;
    private String escolaridad;
    private String ocupacion;
}

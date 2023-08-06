//Desarrollo de Microservicios con Java
//Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.entity;

import java.sql.Date;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pacientes")
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Con esto la base de datos se encarga de asignar automáticamente un valor único a la columna 
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

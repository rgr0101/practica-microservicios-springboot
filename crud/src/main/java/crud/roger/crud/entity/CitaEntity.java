// Desarrollo de Microservicios con Java
// Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.entity;

import java.time.LocalDateTime;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "citas")
public class CitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Con esto la base de datos se encarga de asignar automáticamente un valor único a la columna s
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private String tipoPaciente;

    @Column(nullable = false)
    private String estatusCita;

    @Column(nullable = false)
    private int numeroSesion;

    @Column(nullable = false)
    private double costoTerapia;

    @ManyToOne //Un paciente puede tener varias citas
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;
}
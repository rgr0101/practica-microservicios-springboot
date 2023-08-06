//Desarrollo de Microservicios con Java
//Hecho por Roger Alejandro Pachco Yama
package crud.roger.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import crud.roger.crud.entity.PacienteEntity;

@Repository //Esta anotación marca la interfaz como un componente de repositorio para que Spring lo detecte automáticamente y pueda acceder a las operaciones de la base de datos 
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
}

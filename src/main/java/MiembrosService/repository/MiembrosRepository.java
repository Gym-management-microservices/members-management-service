package MiembrosService.repository;

import MiembrosService.model.Miembro;
import MiembrosService.model.MiembroId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiembrosRepository extends JpaRepository<Miembro, MiembroId> {
}

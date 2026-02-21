package MiembrosService;

import MiembrosService.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import MiembrosService.repository.MiembrosRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MiembrosRepository miembroRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear algunos préstamos de ejemplo
        Miembro miembro1 = new Miembro();
        miembro1.setMiembroid(new MiembroId("1"));
        miembro1.setNombre("Juan Pérez");
        miembro1.setEmail(new Email("juan@email.com"));
        miembro1.setFechaInscripcion(new FechaInscripcion(LocalDate.now().minusDays(30)));
        miembro1.setSuscripcion(new Suscripcion(LocalDate.now(),LocalDate.now().plusDays(30)));
        miembroRepository.save(miembro1);

        Miembro miembro2 = new Miembro();
        miembro2.setMiembroid(new MiembroId("2"));
        miembro2.setNombre("María López");
        miembro2.setEmail(new Email("maria@email.com"));
        miembro2.setFechaInscripcion(new FechaInscripcion(LocalDate.now().minusDays(30)));
        miembro2.setSuscripcion(new Suscripcion(LocalDate.now(),LocalDate.now().plusDays(30)));
        miembroRepository.save(miembro2);

        System.out.println("Datos de préstamos de ejemplo cargados exitosamente.");
    }
}
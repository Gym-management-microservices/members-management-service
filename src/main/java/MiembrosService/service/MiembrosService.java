package MiembrosService.service;

import MiembrosService.model.Miembro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import MiembrosService.repository.MiembrosRepository;

import java.util.List;


@Service
public class MiembrosService {
    @Autowired
    private MiembrosRepository miembrosRepository;

    public void registrarMiembro(Miembro miembro) {
        miembrosRepository.save(miembro);
        System.out.println("Miembro" + miembro.getNombre() + "registrado correctamente");
    }
    public List<Miembro> getMiembros(){
        return miembrosRepository.findAll();
    }
}

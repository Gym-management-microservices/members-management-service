package MiembrosService.controller;

import MiembrosService.model.Miembro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import MiembrosService.service.MiembrosService;

import java.util.List;

@RestController
@RequestMapping("/miembro")
public class MiembrosController {
    @Autowired
    private MiembrosService miembrosService;

    @PostMapping("/registrar")
    public void registrarMiembro(@RequestBody Miembro miembro) {
        miembrosService.registrarMiembro(miembro);
    }
    @GetMapping("/miembros")
    public List<Miembro> getMiembros() {
        return miembrosService.getMiembros();
    }
}

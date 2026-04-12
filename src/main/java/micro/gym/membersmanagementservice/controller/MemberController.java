package micro.gym.membersmanagementservice.controller;

import micro.gym.membersmanagementservice.model.DatosEntrenamiento;
import micro.gym.membersmanagementservice.model.Member;
import micro.gym.membersmanagementservice.service.EntrenamientoProducer;
import micro.gym.membersmanagementservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/members")
@Tag(name = "Members", description = "Gestión de miembros del gimnasio")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private EntrenamientoProducer trainerProducer;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Registrar un nuevo miembro", description = "Crea un nuevo registro de miembro en el gimnasio. Solo ADMIN puede registrar")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Miembro registrado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Member.class),
                examples = @ExampleObject(value = "{ \"id\": \"M001\", \"name\": \"Juan Pérez\", \"email\": \"juan@example.com\", \"phone\": \"123456789\", \"membershipDate\": \"2026-03-11\" }"))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos")
    })
    public Member createMember(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del miembro a registrar",
            required = true,
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{ \"name\": \"Juan Pérez\", \"email\": \"juan@example.com\", \"phone\": \"123456789\" }")))
        @RequestBody Member member) {
        return memberService.registerMember(member);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TRAINER')")
    @Operation(summary = "Obtener todos los miembros", description = "Obtiene el listado completo de todos los miembros registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Miembros obtenidos exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Member.class))),
        @ApiResponse(responseCode = "401", description = "No autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos")
    })
    public Iterable<Member> getAllMembers() {
        return memberService.findAll();
    }

    @RequestMapping("/train/register")
    @Operation(
            summary = "Registrar entrenamiento",
            description = "Registra los datos de entrenamiento de un miembro y los envía a Kafka para procesamiento en tiempo real"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entrenamiento registrado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DatosEntrenamiento.class),
                            examples = @ExampleObject(
                                    value = "{ \"memberId\": \"M001\", \"trainerId\": \"T001\", \"tipoEntrenamiento\": \"Fuerza\", \"duracionMinutos\": 60 }"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "No tiene permisos")
    })
    public void registrarEntrenamiento(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del entrenamiento realizado por el miembro",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{ \"memberId\": \"M001\", \"trainerId\": \"T001\", \"tipoEntrenamiento\": \"Fuerza\", \"duracionMinutos\": 60 }"
                            )
                    )
            )
            @RequestBody DatosEntrenamiento datos) {

        datos.setFecha(LocalDateTime.now());
        memberService.inscribir(datos);
    }
}

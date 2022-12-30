package com.study.resource;

import com.study.dto.DisciplinaResponse;
import com.study.dto.ErrorResponse;
import com.study.dto.ProfessorRequest;
import com.study.dto.ProfessorResponse;
import com.study.service.AlunoService;
import com.study.service.DisciplinaService;
import com.study.service.ProfessorService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/professores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class ProfessorResource {

    @Inject
    ProfessorService professorService;

    @Inject
    DisciplinaService disciplinaService;

    @Inject
    AlunoService alunoService;

    private Response listarProfessores() {
        return Response.ok(professorService.listarTodos()).build();
    }

    @POST
    public Response cadastrarProfessor(final ProfessorRequest professor) {
        try {
            ProfessorResponse response = professorService.cadastrarProfessor(professor);

            return Response
                    .status(Response.Status.CREATED)
                    .entity(response)
                    .build();

        } catch (ConstraintViolationException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.createFromValidation(e))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") int id) {

        try {
            ProfessorResponse response = professorService.buscarPeloId(id);

            return Response.ok(response).build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    public Response buscarProfessorPeloNome(@QueryParam("Nome") String nome) {
        if (nome == null)
            return listarProfessores();
        List<ProfessorResponse> professorsFiltrados = professorService.burcarPeloNome(nome);
        if (Objects.isNull(professorsFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professorsFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response atualizarProfessor(@PathParam("id") Integer id, ProfessorRequest professor) {
        try {
            ProfessorResponse professorAlterado = professorService.atualizar(id, professor);
            return Response.ok(professorAlterado).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponse.createFromValidation(e))
                    .build();
        } catch (NullPointerException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("O campo professor deve ser preenchido", professor)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Integer id) {
        if (professorService.deletar(id))
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/disciplina")
    public Response getDisciplina(@PathParam("id") int id) {

        DisciplinaResponse response = disciplinaService.getDisciplinaByProfessorId(id);

        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}/tutorados")
    public Response getTutorados(@PathParam("id") int id) {

        final var response = alunoService.getTutoradosByProfessorId(id);

        return Response.ok(response).build();
    }
}
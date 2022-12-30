package com.study.resource;

import com.study.dto.DisciplinaRequest;
import com.study.dto.DisciplinaResponse;
import com.study.dto.ErrorResponse;
import com.study.service.DisciplinaService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/disciplinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class DisciplinaResource {

    @Inject
    private DisciplinaService disciplinaService;

    private Response listarDisciplinas() {
        return Response.ok(disciplinaService).build();
    }

    @POST
    public Response cadastrarDisciplina(final DisciplinaRequest disciplina) {
        try {
            DisciplinaResponse response = disciplinaService.cadastrarDisciplina(disciplina);

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
            DisciplinaResponse response = disciplinaService.buscarPeloId(id);

            return Response.ok(response).build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    public Response buscarDisciplinaPeloNome(@QueryParam("Nome") String nome) {
        if (nome == null)
            return listarDisciplinas();
        List<DisciplinaResponse> disciplinasFiltrados = disciplinaService.buscarPeloNome(nome);
        if (Objects.isNull(disciplinasFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(disciplinasFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response atualizarDisciplina(@PathParam("id") Integer id, DisciplinaRequest disciplina) {
        try {
            DisciplinaResponse disciplinaAlterado = disciplinaService.atualizar(id, disciplina);
            return Response.ok(disciplinaAlterado).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponse.createFromValidation(e))
                    .build();
        } catch (NullPointerException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("O campo disciplina deve ser preenchido", disciplina)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Integer id) {
        if (disciplinaService.deletar(id))
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}/titular/{idProfessor}")
    public Response atualizarTitular(@PathParam("id") int idDisciplina, @PathParam("idProfessor") int idProfessor) {
        final var response = disciplinaService.atualizarTitular(idDisciplina, idProfessor);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

}
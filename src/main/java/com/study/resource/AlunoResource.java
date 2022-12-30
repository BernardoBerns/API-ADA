package com.study.resource;

import com.study.dto.AlunoRequest;
import com.study.dto.AlunoResponse;
import com.study.dto.ErrorResponse;
import com.study.service.AlunoService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class AlunoResource {

    @Inject
    AlunoService alunoService;

    private Response listarAlunos() {
        return Response.ok(alunoService.listarTodos()).build();
    }

    @POST
    public Response cadastrarAluno(final AlunoRequest aluno) {
        try {
            AlunoResponse response = alunoService.cadastrarAluno(aluno);

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
            AlunoResponse response = alunoService.buscarPeloId(id);

            return Response.ok(response).build();
        } catch (EntityNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    public Response buscarAlunoPeloNome(@QueryParam("Nome") String nome) {
        if (nome == null)
            return listarAlunos();
        List<AlunoResponse> alunosFiltrados = alunoService.buscarPeloNome(nome);
        if (Objects.isNull(alunosFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(alunosFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response atualizarAluno(@PathParam("id") Integer id, AlunoRequest aluno) {
        try {
            AlunoResponse alunoAlterado = alunoService.atualizar(id, aluno);
            return Response.ok(alunoAlterado).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponse.createFromValidation(e))
                    .build();
        } catch (NullPointerException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .header("O campo aluno deve ser preenchido", aluno)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Integer id) {
        if (alunoService.deletar(id))
            return Response.status(Response.Status.NO_CONTENT).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}/tutor/{idProfessor}")
    public Response atualizarTitular(@PathParam("id") int idAluno, @PathParam("idProfessor") int idProfessor) {
        final var response = alunoService.atualizarTutor(idAluno, idProfessor);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }
}
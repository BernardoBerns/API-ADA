package com.study.resource;



import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.dto.ProfessorDto;
import com.study.service.ProfessorService;


@Path("/professor")
public class ProfessorResource {

    @Inject
    ProfessorService  professorService;
    
    private final Logger log = LoggerFactory.getLogger(ProfessorResource.class);


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrandoProfessor(ProfessorDto professor) {
        log.info(String.format("professor %d Cadastrado", professor.getId()));
        professorService.cadastrandoProfessor(professor);
        return Response.status(Response.Status.CREATED).build();
    }

    public Response listarProfessors() {
        return Response.ok(Arrays.asList(professorService.listAll())).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarProfessor(@PathParam("id") Integer id) {
        ProfessorDto professor = professorService.getById(id);
        if (Objects.isNull(professor))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professor).build();
    }

    @GET
    public Response buscarProfessorPorNome(@QueryParam("prefixo") String nome) {
        if(nome == null) return listarProfessors();
        List<ProfessorDto> ProfessorsFiltrados = professorService.buscarPorNome(nome);
        if (Objects.isNull(ProfessorsFiltrados))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(ProfessorsFiltrados).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterarProfessor(ProfessorDto professor) {
        ProfessorDto professorAlterado = professorService.alterarProfessor(professor);
        if (Objects.isNull(professorAlterado))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(professor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarProfessor(@PathParam("id") Integer id) {
        ProfessorDto professor = professorService.deletar(id);
        if (Objects.isNull(professor))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}

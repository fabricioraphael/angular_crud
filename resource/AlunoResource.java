package resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.br.explora.mob_vaga.db.midas.ParkingWarningConfigurationsDAO;
import com.br.explora.mob_vaga.enums.ResponseTypeEnum;
import com.br.explora.mob_vaga.model.FieldBean;
import com.br.explora.mob_vaga.model.ServerResponseBean;
import com.br.explora.mob_vaga.model.midas.ParkingWarningConfigurationsBean;
import com.br.explora.mob_vaga.resources.AbstractResource;
import com.br.explora.mob_vaga.services.FieldService;
import com.br.explora.mob_vaga.util.JPAUtil;

@Path("/alunos")
public class AlunoResource extends AbstractResource {

	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getAlunos(@Context HttpServletResponse servletResponse) throws IOException {
		ServerResponseBean serverResponseBean = new ServerResponseBean(false, 200, ResponseTypeEnum.SUCESS);

		JPAUtil jpaUtil = new JPAUtil();

		try {
			AlunoDAO alunoDAO = new AlunoDAO(jpaUtil.getEntityManager());
			
			List<AlunoBean> alunos = alunoDAO.findAll();

			serverResponseBean.setDataResponse(alunos);
		} catch (Exception e) {
			e.printStackTrace();
			serverResponseBean = getServerResponseError(OperationErroBean.ALUNO_ERROR(), ErrorBean.ALUNO_DEFAULT_ERROR);
		}

		return serverResponseBean.getJsonResponse();
	}
	
	@Path("/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getAluno(@PathParam("id") String id, @Context HttpServletResponse servletResponse) throws IOException {
		ServerResponseBean serverResponseBean = new ServerResponseBean(false, 200, ResponseTypeEnum.SUCESS);

		JPAUtil jpaUtil = new JPAUtil();

		try {
			AlunoDAO alunoDAO = new AlunoDAO(jpaUtil.getEntityManager());
			
			AlunoBean aluno = alunoDAO.findById(id);

			serverResponseBean.setDataResponse(aluno);
		} catch (Exception e) {
			e.printStackTrace();
			serverResponseBean = getServerResponseError(OperationErroBean.ALUNO_ERROR(), ErrorBean.ALUNO_DEFAULT_ERROR);
		}

		return serverResponseBean.getJsonResponse();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject saveAluno(AlunoBean aluno) throws IOException{
		ServerResponseBean responseBean = new ServerResponseBean(false, 200, ResponseTypeEnum.SUCESS);
		
		JPAUtil jpaUtil = new JPAUtil();
		
		try{			
			jpaUtil.beginTransaction();
			
			AlunoDAO alunoDAO = new AlunoDAO(jpaUtil.getEntityManager());
			
			alunoDAO.persist(aluno);
			
			jpaUtil.commit();
			
			responseBean.setDataResponse(aluno);
		} catch(Exception e){
			e.printStackTrace();
			
			serverResponseBean = getServerResponseError(OperationErroBean.ALUNO_ERROR(), ErrorBean.ALUNO_DEFAULT_ERROR);
		} finally{
			jpaUtil.close();
		}
		
		return responseBean.getJsonResponse();
	}
	
	@Path("/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject updateAluno(@PathParam("id") String id, AlunoBean aluno) throws IOException{
		ServerResponseBean responseBean = new ServerResponseBean(false, 200, ResponseTypeEnum.SUCESS);
		
		JPAUtil jpaUtil = new JPAUtil();
		
		try{
			jpaUtil.beginTransaction();
			
			AlunoDAO alunoDAO = new AlunoDAO(jpaUtil.getEntityManager());
			AlunoBean alunoFromDatabase = alunoDAO.findById(id);
			
			alunoFromDatabase.setName(aluno.getName());
			alunoFromDatabase.setAge(aluno.getAge());
			alunoFromDatabase.setApproved(aluno.getApproved());
			
			alunoDAO.persist(alunoFromDatabase);
			
			jpaUtil.commit();
			
			responseBean.setDataResponse(alunoFromDatabase);
		} catch(Exception e){
			e.printStackTrace();
			
			serverResponseBean = getServerResponseError(OperationErroBean.ALUNO_ERROR(), ErrorBean.ALUNO_DEFAULT_ERROR);
		} finally{
			jpaUtil.close();
		}
		
		return responseBean.getJsonResponse();
	}
}

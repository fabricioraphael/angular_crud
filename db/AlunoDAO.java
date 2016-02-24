package db;

import javax.persistence.EntityManager;

import db.DAOAbstract;
import model.AlunosBean;

public class AlunoDAO extends DAOAbstract<AlunosBean>{

	public AlunoDAO(EntityManager em){
		super(em);
	} 
}

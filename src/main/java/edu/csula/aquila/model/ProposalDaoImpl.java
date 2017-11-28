package edu.csula.aquila.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProposalDaoImpl  implements ProposalDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	
	@Override
	public Proposal getProposal(Long id) {
		return entityManager.find(Proposal.class, id);
	}

	@Override
	@Transactional
	public Proposal saveProposal(Proposal proposal) {
		return entityManager.merge( proposal );
	}
	
	@Override
	public List<Proposal> getProposalsOfUser( Long id ){
		String query = "from Proposal where user_id = :id";
		return entityManager.createQuery(query, Proposal.class)
				.setParameter("id", id)
				.getResultList();
		
	}

}



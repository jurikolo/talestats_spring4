package org.talestats.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.talestats.model.Council;

@Repository
public class CouncilDAOImpl implements CouncilDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCouncil(Council council) {
		Session openSession = sessionFactory.openSession();
		openSession.save(council);
		openSession.flush();
		openSession.close();
	}

	public void updateCouncil(Council council) {
		Session openSession = sessionFactory.openSession();
		Council councilToUpdate = getCouncil(council.getId());
		councilToUpdate.setName(council.getName());
		councilToUpdate.setJob(council.getJob());
		councilToUpdate.setRace(council.getRace());
		councilToUpdate.setSkill(council.getSkill());
		councilToUpdate.setAllies(council.getAllies());
		councilToUpdate.setEnemies(council.getEnemies());
		councilToUpdate.setInfluence(council.getInfluence());
		openSession.update(councilToUpdate);
		openSession.flush();
		openSession.close();
	}

	public Council getCouncil(int id) {
		Session openSession = sessionFactory.openSession();
		Council council = (Council) openSession.get(Council.class, id);
		openSession.flush();
		openSession.close();
		return council;
	}

	public void deleteCouncil(int id) {
		Session openSession = sessionFactory.openSession();
		Council council = getCouncil(id);
		if (council != null)
			openSession.delete(council);
		openSession.flush();
		openSession.close();
	}
	
	public void addOrUpdateCouncil(Council council) {
		Session openSession = sessionFactory.openSession();
		openSession.saveOrUpdate(council);
		openSession.flush();
		openSession.close();
	}

	@SuppressWarnings("unchecked")
	public List<Council> getCouncils() {
		Session openSession = sessionFactory.openSession();
		List<Council> councils = openSession.createQuery("from Council").list();
		openSession.close();
		return councils;
	}

}

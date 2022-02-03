/**
 * @author Christopher Pohlman - cmpolhman
 * CIS175 - Spring 2022
 * Feb 2, 2022
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.VideoGame;

/**
 * 
 */
public class VideoGameHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("VideoGames");
	
	
	
	public void insertGame(VideoGame vg) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(vg);
		em.getTransaction().commit();
		em.close();
		
	}



	/**
	 * @return
	 */
	public List<VideoGame> showAllGames() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		List<VideoGame> allGames = em.createQuery("Select vg from VideoGame vg").getResultList();
		return allGames;
	}
	
	public void deleteGame(VideoGame toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGame> typedQuery = em.createQuery("select vg from VideoGame vg where "
				+ "vg.title = :selectedTitle and vg.system = :selectedSystem", VideoGame.class);
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedSystem", toDelete.getSystem());
		
		typedQuery.setMaxResults(1);
		
		VideoGame result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}



	/**
	 * @param title
	 * @return
	 */
	public List<VideoGame> searchForGameByTitle(String title) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGame> typedQuery = em.createQuery("select vg from VideoGame vg where "
				+ "vg.title=:selectedTitle", VideoGame.class);
		typedQuery.setParameter("selectedTitle", title);
		List<VideoGame> foundGames = typedQuery.getResultList();
		em.close();
		return foundGames;
	}



	/**
	 * @param system
	 * @return
	 */
	public List<VideoGame> searchForGameBySystem(String system) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VideoGame> typedQuery = em.createQuery("select vg from VideoGame vg where "
				+ "vg.system=:selectedSystem", VideoGame.class);
		typedQuery.setParameter("selectedSystem", system);
		List<VideoGame> foundGames = typedQuery.getResultList();
		em.close();
		return foundGames;
	}
	
	public VideoGame searchForGameById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		VideoGame found = em.find(VideoGame.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateGame(VideoGame toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}

}

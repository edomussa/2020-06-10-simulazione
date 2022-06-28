package it.polito.tdp.imdb.db;

import java.util.Map;

import it.polito.tdp.imdb.model.Actor;

public class TestDao {

	public static void main(String[] args) {
		TestDao testDao = new TestDao();
		testDao.run();
	}
	
	public void run() {
		ImdbDAO dao = new ImdbDAO();
		System.out.println("Actors:");
		//Map<Integer,Actor> idMap=
		//System.out.println(dao.listAllActors());
		System.out.println("Movies:");
		System.out.println(dao.listAllMovies());
		System.out.println("Directors:");
		System.out.println(dao.listAllDirectors());
	}

}

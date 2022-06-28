package it.polito.tdp.imdb.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;


import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	private ImdbDAO dao;
	private Map<Integer,Actor> idMap;
	private Graph<Actor,DefaultWeightedEdge> grafo;
	
	public Model() {
		dao=new ImdbDAO();
		idMap=new HashMap<Integer,Actor>();
		dao.listAllActors(idMap);
		
	}
	
	public List<String> listAllGenres(){
		return dao.listAllGenres();
	}
	
	public void creaGrafo(String genre) {
		grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, dao.listAllActorsByGenre(genre));
		
		for(Adiacenza a: dao.getArchi(genre, idMap)) {
			Graphs.addEdgeWithVertices(grafo, a.getA1(), a.getA2(), a.getPeso());
		}
	
	}
	
	public String getVertAndEdges() {
		return "#vertici= "+this.grafo.vertexSet().size()+" #archi= "+this.grafo.edgeSet().size();
	}
	
	
	public Set<Actor> getVertici(){
		return grafo.vertexSet();
	}
	
	public List<Actor> getSimili(Actor a){
		List<Actor> visitati=new ArrayList<>();
		DepthFirstIterator<Actor,DefaultWeightedEdge> it= new DepthFirstIterator<Actor,DefaultWeightedEdge>(this.grafo, a);
		
		while(it.hasNext()) {
			visitati.add(it.next());
		}
		Collections.sort(visitati);
		
		return visitati;
	}
	
	/*public List<Actor> getConnectedActors(Actor a){
		ConnectivityInspector<Actor, DefaultWeightedEdge> ci = new ConnectivityInspector<Actor, DefaultWeightedEdge>(graph);
		List<Actor> actors = new ArrayList<>(ci.connectedSetOf(a));
		actors.remove(a);
		Collections.sort(actors, new Comparator<Actor>() {

			@Override
			public int compare(Actor o1, Actor o2) {
				return o1.lastName.compareTo(o2.lastName);
			}
			
		});
		return actors;
	}*/
}

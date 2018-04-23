/*package cl.citiaps.spring.backend.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the film database table.
 * 
 */
/*
@Entity
@Table(name="film_actor")
@NamedQuery(name="film_actor.findAll", query="SELECT fa FROM Film fa")
public class Actor_Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;

	@Column(name="actor_id", unique=true, nullable=false)
	private int actorId;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	public Actor_Film() {
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int actorId) {
		this.filmId = actorId;
	}

	public int getActorId() {
		return this.actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}*/
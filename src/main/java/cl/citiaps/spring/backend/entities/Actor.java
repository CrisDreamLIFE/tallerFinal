package cl.citiaps.spring.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actor")
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Atributos
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
	private Long id;

	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JsonIgnore
	@JoinTable(name = "film_actor",
		joinColumns = {@JoinColumn(name = "actor_id")},
		inverseJoinColumns = {@JoinColumn(name = "film_id")})
	private Set<Film> films = new HashSet<Film>();

	// Constructores
	
	public Actor() {
    }

	public Actor(String firstName, String lastName, Timestamp lastUpdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

	// Métodos GET/SET
	
	public Long getId() {
		return this.id;
	}

	public void setActorId(Long actorId) {
		this.id = actorId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public Set<Film> getFilms() {
	    return this.films;
	}
	
	public void setFilms(Set<Film> films) {
	    this.films = films;
	}

	// Métodos
	
	/**
	 * Procedimiento que permite añadir una película al listado de películas en las que
	 * participó el actor.
	 * @param film película que se desea agregar.
	 */
	public void addFilm(Film film) {
		this.films.add(film);
	}
}
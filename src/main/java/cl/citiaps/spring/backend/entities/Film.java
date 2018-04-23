package cl.citiaps.spring.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

@Entity
@Table(name="film")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos
	
	@ManyToMany(mappedBy = "films")
    @JsonIgnore
    private Set<Actor> actors = new HashSet<Actor>();
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
	private Long id;

	@Column(name="title", nullable=false, length=45)
	private String title;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="release_year", nullable=false)
	private int releaseYear;
	
	@Column(name="language_id", nullable=false)
	private Long language_id;
	
	@Column(name="original_language_id", nullable=false)
	private Long original_language_id;
	
	@Column(name="rental_duration", nullable=false)
	private int rental_duration;
	
	@Column(name="rental_rate", nullable=false)
	private int rental_rate;
	
	@Column(name="length", nullable=false)
	private int length;
	
	@Column(name="replacement_cost", nullable=false)
	private int replacement_cost;
	
	@Column(name="rating", nullable=false)
	private String rating;
	
	@Column(name="special_features", nullable=false)
	private String special_features;
	
	// Constructores

	public Film() {
	}
	
	// Métodos GET/SET

	public String getSpecial_features() {
		return this.special_features;
	}

	public void setSpecial_features(String special_features) {
		this.special_features = special_features;
	}
	
	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public int getReplacement_cost() {
		return this.replacement_cost;
	}

	public void setReplacement_cost(int replacement_cost) {
		this.replacement_cost = replacement_cost;
	}
	
	public int getLengthe() {
		return this.length;
	}

	public void setLengthe(int length) {
		this.length = length;
	}
	
	public int getRental_rate() {
		return this.rental_rate;
	}

	public void setRental_rate(int rental_rate) {
		this.rental_rate = rental_rate;
	}
	
	public int getRental_duration() {
		return this.rental_duration;
	}

	public void setRental_duration(int rental_duration) {
		this.rental_duration = rental_duration;
	}
	
	public Long getOriginal_language_id() {
		return this.original_language_id;
	}

	public void setOriginal_language_id(Long lenOriId) {
		this.original_language_id = lenOriId;
	}
	
	public Long getLanguageId() {
		return this.language_id;
	}

	public void setLanguageId(Long lengId) {
		this.language_id = lengId;
	}
	
	public int getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(int releaseY) {
		this.releaseYear = releaseY;
	}
	
	public Long getFilmId() {
		return this.id;
	}

	public void setFilmId(Long filmId) {
		this.id = filmId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String titulo) {
		this.title = titulo;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String descripcion) {
		this.description = descripcion;
	}

	
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public Set<Actor> getActors(){
		return actors;
	}
	
	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	
	// Métodos

	/**
	 * Procedimiento que permite añadir un actor a los actores participantes en la
	 * película.
	 * @param actor actor que se desea agregar.
	 */
	public void addActor(Actor actor) {
		this.actors.add(actor);
	}
}
package cl.citiaps.spring.backend.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.repository.FilmRepository;
import cl.citiaps.spring.backend.entities.Film;

@CrossOrigin
@RestController  
@RequestMapping("/actors")
public class ActorService {
	
	// Atributos
	
	@Autowired
    private ActorRepository actorRepository;
    @Autowired
    private FilmRepository filmRepository;
	
	// M�todos "GET"

    /**
     * M�todo que obtiene todos los actores de la base de datos.
     * @return listado de actores con sus datos correspondientes.
     */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Actor> getAllUsers() {
		return actorRepository.findAll();
	}
	
	/**
	 * M�todo que obtiene un actor y sus datos de la base de datos.
	 * @param id identificador del actor del que se est� consultando.
	 * @return instancia del actor consultado, con sus datos.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Actor findOne(@PathVariable("id") Long id) {
		return actorRepository.findOne(id);
	}

	/**
	 * M�todo que obtiene todas las pel�culas en las que particip� un
	 * actor determinado.
	 * @param actorId identificador del actor del que se desea conocer
	 * 				  sus pel�culas.
	 * @return conjunto de pel�culas en que el actor seleccionado ha 
	 * 		   participado.
	 */
	@RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
	@ResponseBody
	public Set<Film> getFilmsOfActor(@PathVariable(value = "id") Long actorId) {
		return actorRepository.findActorById(actorId).getFilms();
	}
	
	// M�todos "POST"
		
	/**
	 * M�todo que le asigna a un determinado actor una pel�cula en la que
	 * este haya participado, almacenando estos datos dentro de una base
	 * de datos.
	 * @param idActor identificador del actor que se desea modificar.
	 * @param idFilms identificador de la pel�cula que se desea agregar.
	 * @return el estado de la petici�n http, indicando �xito o fracaso.
	 */
	@RequestMapping(value = "{idActor}/films/{idFilm}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
	public HttpStatus assignFilmToActor(@PathVariable("idActor") Long idActor, @PathVariable("idFilm") Long idFilm) {

        Actor actor = actorRepository.findActorById(idActor);
        Film film = filmRepository.findFilmById(idFilm);
        
        if (actor !=  null &&  film != null) {
            actor.addFilm(film);
            actorRepository.save(actor);
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }
	
	/**
	 * M�todo que permite agregar un nuevo actor a la base de datos.
	 * @param resource instancia del actor que se desea almacenar.
	 * @return el actor almacenado.
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Actor create(@RequestBody Actor resource){
	     return actorRepository.save(resource);
	}
} 
	 

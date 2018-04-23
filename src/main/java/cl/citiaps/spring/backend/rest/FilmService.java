package cl.citiaps.spring.backend.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.citiaps.spring.backend.entities.Film;
import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.repository.FilmRepository;
import cl.citiaps.spring.backend.repository.ActorRepository;


@CrossOrigin
@RestController  
@RequestMapping("/films")
public class FilmService{
	
	// Atributos
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private ActorRepository actorRepository;
	
	// M�todos "GET"

	/**
	 * M�todo que obtiene todas las pel�culas almacenadas en la base de datos.
	 * @return listado con todas las pel�culas y sus datos.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Film> getAllFilms() {
		return filmRepository.findAll();
	}
	
	/**
	 * M�todo que obtiene una pel�cula y sus datos de la base de datos.
	 * @param id identificador de la pel�cula que se desea consultar.
	 * @return una instancia de la pel�cula seleccionada, con sus datos.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Film getOne(@PathVariable("id") Long id) {
		return filmRepository.findOne(id);
	}
	
	/**
	 * M�todo que obtiene todos los actores que participaron en una
	 * pel�cula determinada.
	 * @param id identificador de la pel�cula de la cual se desea conocer
	 * 			 sus actores.
	 * @return conjunto de actores que posee la pel�cula consultada.
	 */
	@RequestMapping(value = "{id}/actors", method = RequestMethod.GET)
    @ResponseBody
    public Set<Actor> getFilmActors (@PathVariable("id") Long id){
        return  filmRepository.findFilmById(id).getActors();

    }
	
	// M�todos "POST"
	
	/**
	 * M�todo que le asigna a una determinada pel�cula un actor que haya
	 * participado en esta, almacenando estos datos dentro de una base
	 * de datos.
	 * @param idFilms identificador de la pel�cula que se desea modificar.
	 * @param idActor identificador del actor que se desea agregar.
	 * @return el estado de la petici�n http, indicando �xito o fracaso.
	 */
	@RequestMapping(value = "{idFilm}/actors/{idActor}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public HttpStatus assignActorToFilm(@PathVariable("idFilm") Long idFilm, @PathVariable("idActor") Long  idActors){

        Actor actor = actorRepository.findActorById(idActors);
        Film film = filmRepository.findFilmById(idFilm);
        
        if(actor != null && film != null){
            actor.addFilm(film);
            actorRepository.save(actor);
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_ACCEPTABLE;
        }

    }
	
	/**
	 * M�todo que permite agregar una nueva pel�cula a la base de datos.
	 * @param resource instancia de la pel�cula que se desea almacenar.
	 * @return la pel�cula almacenada.
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Film create(@RequestBody Film resource) {
	     return filmRepository.save(resource);
	}
	 
}
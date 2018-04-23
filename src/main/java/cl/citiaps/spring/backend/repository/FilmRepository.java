package cl.citiaps.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.citiaps.spring.backend.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
	
	Film findFilmById(Long id);
}
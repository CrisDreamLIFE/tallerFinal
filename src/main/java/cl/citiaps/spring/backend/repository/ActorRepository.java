package cl.citiaps.spring.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.citiaps.spring.backend.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
	
	Actor findActorById(Long id);
}

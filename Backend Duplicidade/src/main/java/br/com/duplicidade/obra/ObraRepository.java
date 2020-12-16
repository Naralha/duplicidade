package br.com.duplicidade.obra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "obra", path = "obra")
public interface ObraRepository extends JpaRepository<Obra, Long> {

}

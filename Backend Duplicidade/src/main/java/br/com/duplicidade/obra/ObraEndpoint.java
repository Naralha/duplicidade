package br.com.duplicidade.obra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/empresa")
public class ObraEndpoint {

	@GetMapping
	public ResponseEntity<List<Obra>> findAll(){
		Obra obra = new Obra();
		List<Obra> listaObra = new ArrayList<Obra>();
		listaObra.addAll(obra.findAll());
		
		return new ResponseEntity<List<Obra>>(listaObra, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Obra> insert(@RequestBody Obra obra){

		obra.salvar(); 
		
		return new ResponseEntity<Obra>(obra, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Obra> delete(@RequestBody Obra obra){
		
		obra.deletar();
		
		return new ResponseEntity<Obra>(obra, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Obra> atualizar(@RequestBody Obra obra){
		
		obra.atualizar();
		
		return new ResponseEntity<Obra>(obra, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{tituloPrincipal}")
	public ResponseEntity<Obra> findByTituloPrincipal(@PathVariable(name = "tituloPrincipal") String tituloPrincipal){
		Obra obra = new Obra();
		obra.setTituloPrincipal(tituloPrincipal);
		obra.findByTituloPrincipal();
		
		return new ResponseEntity<Obra>(obra, HttpStatus.OK);
	}
}

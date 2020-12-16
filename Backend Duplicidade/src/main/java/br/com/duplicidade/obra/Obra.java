package br.com.duplicidade.obra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import layer.dao.Dao;
import lombok.Data;

@Data
@Entity
@Table(name="tbObra")
public class Obra extends Dao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_obra")
	private long codigoObra;
	
	@Column(name = "cd_ecad")
	private long codigoEcad;
	
	@Column(name = "cd_societario")
	private long codigoSocietario;
	
	@Column(name = "tx_titulo_principal")
	private String tituloPrincipal;
	
	@Column(name = "is_nacional")
	private boolean nacional;
	
	@Column(name = "is_derivada")
	private boolean derivada;
	
	@Column(name = "cd_agrupador")
	private long codigoAgrupador;
	
	public void salvar() {
		
		iniciarOperacao();
		
		setId(sequence());
		session.save(this);
		
		finalizarOperacao();
	
	} 
	
	public List<Obra> findAll(){
		iniciarOperacao();
		
		List<Obra> listaObra = new ArrayList<Obra>();
		listaObra = session.createQuery("SELECT a FROM Obra a", Obra.class).getResultList();
		
		finalizarOperacao();
		
		return listaObra;
	}
	
	public void deletar(){
		
		iniciarOperacao();
		Obra obra = (Obra)session.load(Obra.class, this.codigoObra);
		session.delete(obra);
		
		finalizarOperacao();
		
	}
	
	public void atualizar(){
		
		iniciarOperacao();
		
		session.update(this);
		
		finalizarOperacao();
		
	}
	
	public void findByTituloPrincipal(){
		
		iniciarOperacao();
		 
		Obra obra = session.createQuery("SELECT a FROM Obra a Where a.tituloPrincipal ='"+ this.tituloPrincipal+"'", Obra.class).getResultList().get(0);
		this.setCodigoObra(obra.codigoObra);
		
		finalizarOperacao();
		
	}
}

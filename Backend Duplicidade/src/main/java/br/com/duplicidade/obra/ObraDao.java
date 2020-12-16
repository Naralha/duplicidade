package br.com.duplicidade.obra;

import java.util.ArrayList;
import java.util.List;

import layer.dao.Dao;
import layer.dao.EventStore;
import layer.entity.Event;
import layer.flush.EventIterator;
import layer.flush.EventIteratorImpl;

public class ObraDao extends Dao {

	public void salvar(Obra obra){
		this.factory.getCurrentSession().save(obra);
	}
	
	public List<Obra> findAll(){

		List<Obra> listaFuncionarios = new ArrayList<Obra>();
		listaFuncionarios = Dao.factory.getCurrentSession().createQuery("SELECT a FROM Funcionario a", Obra.class).getResultList();
		
		return listaFuncionarios;
	}
	
}

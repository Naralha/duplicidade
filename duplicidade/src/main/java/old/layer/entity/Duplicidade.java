package old.layer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import br.com.duplicidade.enums.TipoDuplicidadeResolvidaEnum;
import lombok.Data;

@Data
@Entity
@Table(name="tbDuplicidade")
public class Duplicidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_duplicidade")
	private long codigoDuplicidade;
	
	@Column(name = "dt_inclusao")
	@CreationTimestamp
	private Date dataInclusao;
	
	@Column(name="tx_motivo")
	private String motivo;
	
	@ManyToOne
	@JoinColumn(name="cd_agrupador")
	private Agrupador agrupador;
	
	@Column(name = "tp_duplicidade_resolvida")
	private TipoDuplicidadeResolvidaEnum tipoDuplicidadeResolvida;

}
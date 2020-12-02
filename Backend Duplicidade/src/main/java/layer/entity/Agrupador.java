package layer.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="tbAgrupador")
public class Agrupador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_agrupador")
	private long codigoAgrupador;
	
	@JsonIgnore
	@OneToMany(mappedBy = "agrupador", targetEntity = Duplicidade.class, fetch = FetchType.LAZY)
	private Set<Duplicidade> duplicidades;
	
}

package layer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tbObra")
public class Obra {
	
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
}

package layer.enums;

public enum TipoDuplicidadeResolvidaEnum {

	NAO_RESOLVIDA(1),DEPURACAO(2),HOMONIMO(3),AUTOMATICO(4);
	
	private int b;
	private int[] listaInt = {1,2,3};
	
	private TipoDuplicidadeResolvidaEnum(final int b) {
		this.b = b;
	}
	
	
	public boolean verificarValores(int[] listaInt) {
		for(TipoDuplicidadeResolvidaEnum tipoDuplicidade : values()) {
		   
		}
		return true;
		
	}
}

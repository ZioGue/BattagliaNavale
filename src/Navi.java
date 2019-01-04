
public enum Navi {

	Portaerei (5, 'P', "Portarei"),
	Corazzata (4, 'C', "Corazzata"),
	Sottomarino (3, 'S', "Sottomarino"),
	Incrociatore (3, 'I', "Incrociatore"),
	Torpediniere  (2, 'T', "Torpediniere"),
	Barcone (1, 'B', "Barcone");
	
	private final int size, vita;
	private final char icon;
	private final String nome;
	Navi(int size, char icon, String nome){
		this.size = size;
		this.icon = icon;
		this.vita = size;
		this.nome = nome;
	}
	
	public int getSize() {
		return size;
	}
	
	public char getIcon() {
		return icon;
	}

	public int getVita() {
		return vita;
	}
	
	public String getNome() {
		return nome;
	}
}

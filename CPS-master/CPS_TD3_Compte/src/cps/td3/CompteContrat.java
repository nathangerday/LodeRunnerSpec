package cps.td3;

public class CompteContrat extends CompteDecorator {
	
	private String copyNom;
	private int copyNumero;
	private double copyLimite;
	
	public CompteContrat(Compte c){
		super(c);
	}
	
	@Override 
	public void depot(double somme) throws Exception {
		if(somme < 0) 
			throw new Exception("somme < 0");
		super.depot(somme);
	}
	
	@Override
	public void retrait(double somme) throws Exception {
		checkInvariant();
		if(!super.peutPrelever(somme)) {
			throw new Exception("Pre condition non satisfaite");
		}
		final double soldePre = super.getSolde();
		super.retrait(somme);
		checkInvariant();
		if(super.getSolde() != soldePre - somme) {
			throw new Exception("Post condition non satisfaite");
		}		
	}
	
	@Override
	public void init(String nom, int numero, double dec) throws Exception {
		if (nom.equals("")) {
			throw new Exception("nom vide");
		}
		if (numero <= 0) {
			throw new Exception("numero <= 0");
		}
		if (dec < 0) {
			throw new Exception("dec < 0");
		}
		super.init(nom, numero, dec);
		storeConstObservators();
	}
	
	public void checkInvariant() throws Exception {
		if (super.estDecouvert() && (super.getSolde() != -super.getMontantDecouvert())) {
			throw new Exception("Invariant viole");
		}
		//TODO check les autres invariants
	}
	
	private void storeConstObservators() {
		//TODO a mettre dans les init
		copyNom = super.getNom();
		copyNumero = super.getNumero();
		copyLimite = super.getLimite();
	}
	
	
	public void verifyConstObservators() throws Exception {
		if (super.getNom() != copyNom) {
			throw new Exception("const nom");
		}
		if (super.getNumero() != copyNumero) {
			throw new Exception("const numero");
		}
		if (super.getLimite() != copyLimite) {
			throw new Exception("const limite");
		}
	}
	
	public static void main(String[] args) {
		try {
			Compte c = new CompteImpl();
			c.init("toto", 1, 0.0);
			CompteContrat cc = new CompteContrat(c);
			cc.depot(500);
			cc.retrait(700);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
}

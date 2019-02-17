package cps.td3;

public class CompteDecorator implements Compte {
	private Compte compte;
		
	
	public CompteDecorator(Compte c) {
		this.compte = c;
	}

	@Override
	public String getNom() {
		return compte.getNom();
	}

	@Override
	public int getNumero() {
		return compte.getNumero();
	}

	@Override
	public double getSolde() {
		return compte.getSolde();
	}

	@Override
	public double getLimite() {
		return compte.getLimite();
	}

	@Override
	public boolean estDecouvert() {
		return compte.estDecouvert();
	}

	@Override
	public double getMontantDecouvert() {
		return compte.getMontantDecouvert();
	}

	@Override
	public boolean peutPrelever(double somme) {
		return compte.peutPrelever(somme);
	}

	@Override
	public void init(String nom, int numero, double dec) throws Exception {
		compte.init(nom, numero, dec);
	}

	@Override
	public void init(Compte compte) {
		compte.init(compte);
	}

	@Override
	public void depot(double somme) throws Exception {
		compte.depot(somme);
	}

	@Override
	public void retrait(double somme) throws Exception {
		compte.retrait(somme);
	}

}

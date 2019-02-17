package cps.td3;

public class CompteImpl implements Compte {
	//TODO a finir
	private String nom;
	private int numero;
	private double solde;
	private double limite;
	
	public CompteImpl() {
		super();
	}
		
	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getNumero() {
		return numero;
	}

	@Override
	public double getSolde() {
		return solde;
	}

	@Override
	public double getLimite() {
		return limite;
	}

	@Override
	public boolean estDecouvert() {
		return getSolde() < 0;
	}

	@Override
	public double getMontantDecouvert() {
		return - getSolde();
	}

	@Override
	public boolean peutPrelever(double somme) {
		return getSolde() - somme >= limite;
	}

	@Override
	public void init(String nom, int numero, double dec) {
		this.nom = nom;
		this.numero = numero;
		this.limite = dec;
		this.solde = 0;
	}

	@Override
	public void init(Compte compte) {
		this.nom = compte.getNom();
		this.numero = compte.getNumero();
		this.limite = compte.getLimite();
		this.solde = compte.getSolde();
	}

	@Override
	public void depot(double somme) {
		solde = solde + somme;
	}

	@Override
	public void retrait(double somme) {
		solde = solde - somme;
	}

}

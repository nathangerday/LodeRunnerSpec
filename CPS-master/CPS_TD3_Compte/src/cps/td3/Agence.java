package cps.td3;

import java.util.Set;

public interface Agence {
	
	/** Observators **/
	public String getNom(); // const
	public Set<Integer> getNumeros();
	public int getNbComptes();
	public boolean compteExiste(int numero);
	// \pre getConmpte(n) require compteExiste
	public Compte getCompte(int numero) throws Exception;
	
	/** Constructors **/
	// \pre init(n) require n != ""
	// \post getNom() == nom
	// \post getNumeros() == 0
	public void init(String nom) throws Exception;
	
	/** Operators **/
	// \pre creerCompte(nom, num, dec) require !compteExiste(num)
	public void creerCompte(String nom, int numero, double decouvert)
			throws Exception;
	
	// \pre fermerCompte(num) require compteExiste(num)
	public void fermerCompte(int numero) throws Exception;
	
	// \pre virement(n1, n2, s) require n1 != n2 
	// \pre virement(n1, n2, s) require compteExiste(n1)
	// \pre virement(n1, n2, s) require compteExiste(n2)
	// \pre virement(n1, n2, s) require getCompte(n1).peutPrelever(s)
	public void virement(int num1, int num2, double somme) 
			throws Exception;
	
	
	/** Invariants **/
	// \inv getNbComptes() == getNumeros.size()
	// \inv compteExiste(num) == getNumeros().contains(num) 
}

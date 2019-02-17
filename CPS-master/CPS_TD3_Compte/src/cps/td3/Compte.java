package cps.td3;

public interface Compte {
	
	/** observators **/
	
	public String getNom(); //const
	public int getNumero(); //const
	public double getSolde(); 
	public double getLimite(); //const
	public boolean estDecouvert();
	// \pre getMontantDecouvet() require estDecouvert()
	public double getMontantDecouvert();
	// \pre peutPrelever(s) require s>0 
	public boolean peutPrelever(double somme);
	
	/** Constructors 
	 * @throws Exception **/
	// \pre n != "" 
	// \pre num > 0 
	// \pre dec >= 0
	// \post getNom() == nom
	// \post getNumero() == numero
	// \post getSolde() == 0
	// \post getlimite() == - dec
	public void init(String nom, int numero, double dec) throws Exception;
	
	// \post getNom(init(C)) = getNom(C)
	// \post getNumero(init(C)) = getNumero(C)
	// \post getSolde(init(C)) = getSolde(C)
	// \post getLimite(init(C)) = getLimite(C)
	public void init(Compte compte);
	
	/** Operators 
	 * @throws Exception **/
	// \pre depot(somme) require somme > 0
	// \post getSolde() == getSolde()@pre + somme
	public void depot(double somme) throws Exception;
	
	// \pre retrait(somme) require peutPrelever(somme)
	// \post getSolde() == getSolde()@pre - somme
	public void retrait(double somme) throws Exception;
	
	/** invariants **/
	// \inv getMontantDecouvet() == -getSolde()
	// \inv estDecouvert() == (getSolde() < 0)
	// \inv peutPrelever(somme) == (getSolde() - somme >= getLimite())
	// \inv getSolde() >= getLimite()
	
}

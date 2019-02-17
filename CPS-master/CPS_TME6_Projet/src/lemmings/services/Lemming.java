package lemmings.services;

public interface Lemming {

	public enum Direction { 
		DROITIER, GAUCHER;
	}	
	
	public enum Type {
		MARCHEUR, TOMBEUR;
	}
	
	/** Observateurs **/
	public Direction getDirection();
	public Type getType();
	public int getX();
	public int getY();
	
	// \pre getType() = TOMBEUR
	public int tombeDepuis();

	public int getId();
	public GameEng getGameEng();
	
	/** Constructeur **/
	// \post getDirection() = DROITIER
	// \post getType() = MARCHEUR 
	// \post getId() = id
	public void init(int id);
	
	/** Operateurs **/
	// \pre getType() != TOMBEUR
	// \post getType() = TOMBEUR
	public void devientTombeur();
	
	// \pre getType() != MARCHEUR
	// \post getType() = MARCHEUR
	public void devientMarcheur();
	
	// \pre getDirection() != GAUCHER
	// \post getDirection() = GAUCHER
	public void devientGaucher();
	
	// \pre getDirection() != DROITIER
	// \post getDirection() = DROITIER
	public void devientDroitier();
	
	// \pre !getGameEng().gameOver()
	// \pre getId() \in getGameEng().getLemmingActifs()
	/* \post getType()@pre = MARCHEUR AND !getGameEng().isObstacle(getX(), getY()+1) 
	 * 							  \implies getType() = TOMBEUR AND getX() = getX()@pre AND getY() = getY()@pre
	 *
	 *
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 							  AND getGameEng().isObstacle(getX()+1, getY()-1)
	 * 							  \implies getDirection() = GAUCHER 
	 * 										AND getType() = MARCHEUR
	 * 										AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirecton()@pre = DROITIER
	 * 							  AND getGameEng().isObstacle(getX()+1, getY())
	 * 							  AND getGameEng().isObstacle(getX()+1, getY()-2)
	 * 							  \implies getDirection() = GAUCHER
	 * 										AND getType() = MARCHEUR
	 * 										AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 							  AND getGameEng().isObstacle(getX()+1, getY())
	 * 							  AND !getGameEng().isObstacle(getX()+1, getY()-1)
	 * 							  AND !getGameEng().isObstacle(getX()+1, getY()-2)
	 * 							  \implies getX() = getX()@pre+1 AND getY() = getY()@pre-1 AND getType() = MARCHEUR
	 * 
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = DROITIER
	 * 							  AND !getGameEng().isObstacle(getX()+1, getY())
	 * 							  AND !getGameEng().isObstacle(getX()+1, getY()-1)
	 * 							  \implies getX() = getX()@pre+1 AND getY() = getY()@pre AND getType() = MARCHEUR
	 * 
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 							  AND getGameEng().isObstacle(getX()-1, getY()-1)
	 * 							  \implies getDirection() = DROITIER AND getType() = MARCHEUR
	 * 										AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirecton()@pre = GAUCHER
	 * 							  AND getGameEng().isObstacle(getX()-1, getY())
	 * 							  AND getGameEng().isObstacle(getX()-1, getY()-2)
	 * 							  \implies getDirection() = DROITIER AND getType() = MARCHEUR
	 * 										AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 							  AND getGameEng().isObstacle(getX()-1, getY())
	 * 							  AND !getGameEng().isObstacle(getX()-1, getY()-1)
	 * 							  AND !getGameEng().isObstacle(getX()-1, getY()-2)
	 * 							  \implies getX() = getX()@pre-1 AND getY() = getY()@pre-1 AND getType() = MARCHEUR
	 * 
	 * \post getType()@pre = MARCHEUR AND getDirection()@pre = GAUCHER
	 * 							  AND !getGameEng().isObstacle(getX()-1, getY())
	 * 							  AND !getGameEng().isObstacle(getX()-1, getY()-1)
	 * 							  \implies getX() = getX()@pre-1 AND getY() = getY()@pre AND getType() = MARCHEUR
	 * 
	 * 
	 * \post getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX(), getY()+1) AND tombeDepuis() < 8
	 * 						\implies getType() = MARCHEUR AND getX() = getX()@pre AND getY() = getY()@pre
	 * 
	 * \post getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX(), getY()+1) AND tombeDepuis >= 8
	 * 							 \implies le lemming meurt : getId() n'appartient pas a getGameEng().getLemmingsActif()
	 * 									AND getGameEng().getNombreMorts() = getGameEng().getNombreMorts()@pre + 1
	 * 
	 * \post getType()@pre = TOMBEUR AND getGameEng().isObstacle(getX(), getY()+1) AND tombeDepuis() < 8
	 * 							 \implies getType() = TOMBEUR AND getX() = getX()@pre AND getY() = getY()@pre + 1
	 * 
	 * \post if getGameEng().getLevel().isExit(getX(), getY()) == true 
	 * 			\implies le lemming est sauve : getId() n'appartient pas a getGameEng().getLemmingsActif()
	 * 					AND getGameEng().getNombreSauves() = getGameEng().getNombreSauves()@pre + 1
	 */				
	public void step();
	
	/** Invariants **/
	// \inv 1 <= getX() <= getGameEng().getLevel().getWidth()-1 
	// \inv 1 <= getY() <= getGameEng().getLevel().getHeight()-1
	// \inv getId() > 0
	// \inv getType() = TOMBEUR \implies tombeDepuis() >= 0
	
}

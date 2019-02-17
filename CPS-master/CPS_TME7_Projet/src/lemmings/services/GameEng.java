package lemmings.services;

import java.util.Set;

public interface GameEng {
	
	/** Observators **/
	public Level getLevel();
	
	//�\pre 0 <= x < getLevel().getWidth() AND 0 <= y < getLevel().getHeight()
	public boolean isObstacle(int x, int y);

	//�\pre 0 <= x < getLevel().getWidth() AND 0 <= y < getLevel().getHeight()
	public boolean isLibre(int x, int y);
	
	public int getSizeColony();
	
	public int getSpawnSpeed();
	
	public boolean gameOver();
	
	// \pre gameOver()
	public double score();
	
	// \pre getLevel().isEditing() == false
	// \pre !gameOver()
	public int getNombreTours();
	
	// \pre gameOver()
	public int getNombreToursFinal();
	
	// \pre !gameOver()
	public Set<Integer> getLemmingsActifs();
	
	// \pre !gameOver()
	// \pre i \in getLemmingsActifs()
	//TODO normalement i > 0
	public Lemming getLemming(int i);
	
	public int getNombreSauves();
	
	public int getNombreMorts();
	
	public int getNombreActifs();
	
	public int getNombreCrees();
	
	/** Constructors **/
	// \pre sizeColony > 0
	// \pre spawnSpeed > 0
	// \post getSpawnSpeed() = spawnSpeed;
	// \post getSizeColony() = sizeColony;
	// \post getNombreSauves() = 0;
	// \post getNombreMorts() = 0;
	// \post getNombreActifs() = 0;
	// \post getNombreCrees() = 0;
	// \post getNombreTours() = 0
	public void init(int sizeColony, int spawnSpeed);
	
	/** Opérateurs **/
	// \pre !gameOver()
	/* \post getNombresTours mod getSpawnSpeed() = 0 AND getNombresCrees() < getSizeColony()
	 * 		\implies getNombreCrees() = getNombreCrees()@pre+1
	 * 				 AND getNombreCrees() \in getLemmingsActifs()
	 * 				 AND getLemming(getNombreCrees()).getX() = x
	 * 				 AND getLemming(getNombreCrees()).getY() = y
	 * 				 AND isEntrance(x,y)
	 */
	// \post getNombreTours() = getNombreTours()@pre + 1
	public void step();
	
	// \pre !gameOver()
	// \pre i \in getLemmingsActifs
	/* \post tuerLemming(i) \implies getNombreActifs() = getNombreActifs()@pre-1
	 *						AND getNombreMorts() = getNombreMorts()@pre+1
	 *						AND getLemmingsActifs() = getLemmingsActifs()@pre - {i}	
	 */
	public void tuerLemming(int i);
	
	// \pre !gameOver()
	// \pre i \in getLemmingsActifs
	// \pre getLemming(i).getX() = x AND getLemming(i).getY() = y AND getLevel().isExit(x, y)
	/* \post sauverLemming(i) \impliest getNombreActifs() = getNombreActifs()@pre-1
	 *						  AND getNombreSauves() = getNombreSauves()@pre+1
	 *						  AND getLemmingsActifs() = getLemmingsActifs()@pre - {i}					  
	 */
	public void sauverLemming(int i);
	
	/** Invariants **/
	// \invMin gameOver() == ( getNombreActifs() == 0 AND getNombreCrees() == getSizeColony() )  
	// \invMin getNombreActifs() == | getLemmingActifs() |
	// \invMin score() == getNombreSauves / getSizeColony()
	// \invMin getNombreCrees() == getNombreActifs() + getNombreSauves() + getNombreMorts()
	// \invMin isObstacle(x,y) == getLevel().getNature(x,y) != EMPTY
	// \inv getNombreTours() >= 0
	// \inv getNombreSauves() >= 0 AND getNombreSauves() <= getNombreCrees() 
	// \inv getNombreMorts() >= 0 AND getNombreMorts() <= getNombreCrees()
	// \inv getNombreActifs() >= 0 AND getActifs() <= getNombreCrees()
	// \inv getNombreCrees() >= 0 AND getNombreCrees() <= getNombreCrees()
	
}

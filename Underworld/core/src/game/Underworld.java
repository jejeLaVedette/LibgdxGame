package game;


import screens.EcranAccueil;

import com.badlogic.gdx.Game;

public class Underworld extends Game {
 
    public void create() { // La méthode que l'on doit implementer sinon erreur :)
    	//setScreen(new StageScreen(this));//Au lancement de notre jeu, le premier écran à s'afficher est le menu
    	setScreen(new EcranAccueil(this));
    }
}
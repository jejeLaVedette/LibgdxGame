package dialogues;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ManipulationEntree implements ApplicationListener {

	public static int longueur = 320;
	public static int largeur = 480 ;
	public static int deltaTemps = 3;
	private SpriteBatch batch;
	private BitmapFont fontTexte ;
	private BitmapFont fontCadre ;
	private String message1;
	private String message2;
	private String message3;
	private String message4;
	private String msgActionDesktop;
	private String msgActionAndroid;
	private String coordonneesDesktop;
	private String coordonneesAndroid;
	private String android ;
	private String desktop ;
	private String cadre1;
	private String cadre2;
	private float tempsEcouler ;
	private boolean afficheMsg ;
	private boolean afficheContenu ;
	@Override
	public void create() {
		batch = new SpriteBatch();
		fontTexte= new BitmapFont();
		fontCadre= new BitmapFont();
		message1 ="Ceci est une application qui vous aidra a mieux voir";
		message2 ="la gestion des evenements de la souris sous Desktop";
		message3 ="et ecran tactile sous Android.";
		message4 ="cliquer/toucher l'ecran pour commencer !";
		msgActionDesktop = "Action : -----";
		msgActionAndroid = "Action : -----";
		afficheContenu = false ;
		afficheMsg = true;
		android = "Plateforme Android";
		desktop = "Plateforme Desktop";
		cadre1 = "|";
		cadre2 = "_";
	}
	@Override
	public void dispose() {
	}
	@Override
	public void pause() {
	}
	//**************** Méthode qui dessine le cadre ************************//
	public void dessinerCadre()
	{
		int distance = 0;
		for(int i =0 ; i <= 18 ; i++)
		{
			fontCadre.setColor(Color.GREEN);
			fontCadre.draw(batch, cadre1, largeur /2 , distance);
			distance +=20 ;
		}
		distance = 0;
		for(int i = 0; i<= 25 ; i++)
		{
			fontCadre.setColor(Color.GREEN);
			fontCadre.draw(batch, cadre2, distance , longueur - 50);
			distance +=20 ;
		}
	}
	//***********************************************************************//
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);          
		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean touchUp(int x, int y, int pointeur, int bouton) {
				tempsEcouler = 0 ;  // Initialiser le temps depuis
				// la dernier entrée
				if(bouton == Buttons.LEFT)
					msgActionDesktop = "relache bouton G";
				if(bouton == Buttons.RIGHT)
					msgActionDesktop = "relache bouton D";
				if(bouton == Buttons.MIDDLE)
					msgActionDesktop = "relache bouton M";
				if(pointeur == 0)
					msgActionAndroid = "premier doigt leve";
				if(pointeur == 1)
					msgActionAndroid = "deuxieme doigt leve";
				if(pointeur == 2)
					msgActionAndroid = "troisieme doigt leve";
				// Recuperer coordonnées et les convertir en String
				coordonneesDesktop ="x="+ Integer.toString(x) +" y="+ Integer.toString(y);
				coordonneesAndroid ="x="+ Integer.toString(x) +" y="+ Integer.toString(y);
				coordonneesAndroid = "coordonnees : "+coordonneesAndroid ;
				coordonneesDesktop = "coordonnees : "+coordonneesDesktop;
				return false;
			}
			public boolean touchMoved(int x, int y) {
				// Cette méthode n'est jamais appelée sous Android
				tempsEcouler = 0 ;
				msgActionDesktop = "Action : mouvement du curseur";
				// Récupérer les coordonnées et les convertir en String
				coordonneesDesktop ="x="+ Integer.toString(x) +" y="+ Integer.toString(y);
				coordonneesDesktop = "coordonnees : "+coordonneesDesktop;

				return false;
			}

			@Override
			public boolean touchDragged(int x, int y, int pointeur) {
				tempsEcouler = 0 ; // Initialiser le temps depuis
				// la dernier entree
				// Cette méthode ne nous informe pas sur le bouton de
				// la souris il faut utiliser le polling
				if(Gdx.input.isButtonPressed(Buttons.LEFT))
					msgActionDesktop = "Action : Glissement avec bouton G";
				if(Gdx.input.isButtonPressed(Buttons.RIGHT))
					msgActionDesktop = "Action : Glissement avec bouton D";
				if(Gdx.input.isButtonPressed(Buttons.MIDDLE))
					msgActionDesktop = "Action : Glissement avec bouton M";

				if(pointeur == 0) // si c'est le premier doigt
					msgActionAndroid = "Action : Glissement du 1er doigt";
				if(pointeur == 1) // si c'est le deuxième doigt
					msgActionAndroid = "Action : Glissement du 2eme doigt";
				if(pointeur == 2) // si c'est le troisième doigt
					msgActionAndroid = "Action : Glissement du 3eme doigt";

				// Récupérer coordonnées et les convertir en String
				coordonneesDesktop ="x="+ Integer.toString(x) +" y="+ Integer.toString(y);
				coordonneesAndroid ="x="+ Integer.toString(x) +" y="+ Integer.toString(y);
				coordonneesAndroid = "coordonnees : "+coordonneesAndroid ;
				coordonneesDesktop = "coordonnees : "+coordonneesDesktop;

				return false;
			}

			@Override
			public boolean touchDown(int x, int y, int pointeur, int bouton) {
				tempsEcouler = 0 ; // Initialiser le temps depuis
				// la dernier entrée
				// Cette méthode nous informe sur le bouton de la souris
				// Ce n’est pas la peine d'utiliser le polling
				if(bouton ==Buttons.LEFT)
					msgActionDesktop = "Action : clic bouton gauche souris";
				if(bouton ==Buttons.RIGHT)
					msgActionDesktop = "Action : clic bouton droit souris ";
				if(bouton ==Buttons.MIDDLE)
					msgActionDesktop = "Action : clic bouton milieu souris";

				// Pour distinguer quel doit a effectué l'action
				if(pointeur == 0)
					msgActionAndroid = "premier doigt pose";
				if(pointeur == 1)
					msgActionAndroid = "deuxieme doigt pose";
				if(pointeur == 2)
					msgActionAndroid = "troisieme doigt pose";

				// Récupérer coordonnées et les convertir en String
				coordonneesDesktop ="x="+ Integer.toString(x) +" y="+ Integer.toString(y);
				coordonneesAndroid ="x="+ Integer.toString(x) +" y="+ Integer.toString(y);
				coordonneesAndroid = "coordonnees : "+coordonneesAndroid ;
				coordonneesDesktop = "coordonnees : "+coordonneesDesktop;

				return false;
			}
			@Override
			public boolean scrolled(int direction) {

				if(direction == 1) // roule vers le bas
					msgActionDesktop = "roulette vers bas";
				if(direction == -1)// roule vers le haut
					msgActionDesktop = "roulette vers haut";
				return false;
			}
			//*********************Entrée clavier********************************************//
			// On est obligé de les redéfinir puisqu’on a implémenté l’interface
			@Override
			public boolean keyUp(int arg0) {
				return false;
			}
			@Override
			public boolean keyTyped(char arg0) {
				return false;
			}
			@Override
			public boolean keyDown(int arg0) {
				return false;
			}
			//*******************************************************************************//
			@Override
			public boolean mouseMoved(int screenX,
					int screenY) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		if(Gdx.input.justTouched()) // si cliquer/toucher puis relâcher
		{
			afficheMsg     = false; // Masquer la 1er page
			afficheContenu = true;  // Afficher la 2eme page
		}

		batch.begin();
		if(afficheMsg)     // 1ere page
		{
			fontTexte.draw(batch, message1, 80, 300); //insérer du texte
			fontTexte.draw(batch, message2, 80, 280);
			fontTexte.draw(batch, message3, 80, 260);
			fontTexte.draw(batch, message4, 80, 240);
		}
		if(afficheContenu) // 2eme page
		{
			tempsEcouler += Gdx.graphics.getDeltaTime();
			// Compter le temps depuis la dernière entrée
			if(tempsEcouler > deltaTemps) // Aucune entrée n'a été
			{                             // éffectuée durant un temps
				// delta
				msgActionDesktop = "Action : -----"; // aucune action
				msgActionAndroid = "Action : -----"; // aucune action
				coordonneesAndroid = " ";
				coordonneesDesktop = " ";
			}
			dessinerCadre();
			// Partie Android 
			// insertion titre + action +coordonnées
			fontTexte.draw(batch,android, 50,longueur-25);       
			fontTexte.draw(batch,msgActionAndroid,10,longueur-70);
			fontTexte.draw(batch,coordonneesAndroid,10, longueur-90);
			// Parite Desktop
			// insertion titre + action +coordonnées
			fontTexte.draw(batch,desktop,largeur/2 +50,longueur-25);        
			fontTexte.draw(batch,msgActionDesktop,largeur/2 +10,longueur-70);
			fontTexte.draw(batch,coordonneesDesktop,largeur/2+10,longueur-90);
		}
		batch.end();
	}
	@Override
	public void resize(int arg0, int arg1) {
	}
	@Override
	public void resume() {
	}
}
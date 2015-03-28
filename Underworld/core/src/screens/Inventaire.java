package screens;

import jeu.Map;
import game.Underworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



public class Inventaire extends Game implements Screen{

	private Stage stage;
	private Underworld game;
	private Texture background;
	private Texture btnUp,btnDown,btnChecked;
	private int xHero,yHero,valDeplacement;

	public Inventaire (Underworld game){
		this.game = game;    
	}

	public Inventaire(Underworld game, int x, int y, int valDeplacement) {
		// TODO Auto-generated constructor stub
		this.xHero=x;
		this.yHero=y;
		this.valDeplacement=valDeplacement;
		this.game=game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.getBatch().begin();
		stage.getBatch().draw(background, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.getBatch().end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub        
	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("ihmGame/Inventaire.png"));

		btnUp = new Texture(Gdx.files.internal("ui/fondBleu.png"));
		btnDown = new Texture(Gdx.files.internal("ui/fondBleu.png"));
		btnChecked = new Texture(Gdx.files.internal("ui/fondBleu.png"));        

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);//stage reçoit les input

		/**** utiliser un skin ****/
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));        
		//Label titre = new Label("Interface de jeu", skin);


		/*** créer son style ****/
		BitmapFont fontPerso = new BitmapFont(Gdx.files.internal("ui/default.fnt"),Gdx.files.internal("ui/default.png"), false);
		LabelStyle style = new LabelStyle(fontPerso, new Color(Color.CYAN));//fonte et couleur
		//titre.setPosition(Constantes.fenetreWidth/2-titre.getWidth()/2, Constantes.fenetreHeight-50);

		/***créer un Textbutton avec un skin existant*****/
		//TextButton bouton = new TextButton("quitter",skin);


		/** créer son style de bouton ***/
		TextButtonStyle styleBouton = new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(btnUp)),
				new TextureRegionDrawable(new TextureRegion(btnDown)),
				new TextureRegionDrawable(new TextureRegion(btnChecked)) ,
				fontPerso);
		styleBouton.over = new TextureRegionDrawable(new TextureRegion(btnDown));
		//		
		//		TextButton btnJouer = new TextButton("Jouer",styleBouton);
		//		TextButton btnParam = new TextButton("Parametres",styleBouton);
		TextButton btnQuit = new TextButton("quitter",styleBouton);
		//		btnJouer.setPosition(Gdx.graphics.getWidth()/2-btnJouer.getWidth()/2, 150);btnJouer.setSize(100, 50);
		//		btnParam.setPosition(Gdx.graphics.getWidth()/2-btnParam.getWidth()/2, 100);btnParam.setSize(100, 50);
		btnQuit.setPosition(50, 50);btnQuit.setSize(100, 50);

		/*
		 * ecoute des btns
		 */

		btnQuit.addListener(new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.setScreen(new Map(xHero,yHero,valDeplacement,game));
				return false;    
			}
		});

		/*** on l'ajoute au stage ****/
		stage.addActor(btnQuit);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub        
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub        
	}

	@Override
	public void resume() {        
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		background.dispose();        
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

}
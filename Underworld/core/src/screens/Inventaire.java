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
	private Texture inventaireG1;
	private Texture inventaireG2;
	private Texture inventaireH;
	private Texture inventaireD;
	private Texture portraitHero;
	private Texture btnUp,btnDown,btnChecked;
	
	private int xHero,yHero,valDeplacement;
	
	private int widthPortrait;
	private int widthInventaireG;
	private int widthInventaireD;
	
	private int heightInventaireG2;
	private int heightInventaireG1;
	private int heightInventaireD;
	private int heightInventaireH;
	private int heightPortrait;

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
		
		widthInventaireG=Gdx.graphics.getWidth()/4;
		heightInventaireG2=Gdx.graphics.getHeight()/4;
		
		heightInventaireG1=Gdx.graphics.getHeight()*3;
		heightInventaireG1=heightInventaireG1/4;
		
		widthInventaireD=Gdx.graphics.getWidth()*3;
		widthInventaireD=widthInventaireD/4;
		heightInventaireD=Gdx.graphics.getHeight()*3;
		heightInventaireD=heightInventaireD/4;
		
		heightInventaireH=Gdx.graphics.getHeight()/4;

		stage.getBatch().begin();
		//stage.getBatch().draw(background, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage.getBatch().draw(inventaireG1, 0, heightInventaireG2,widthInventaireG,heightInventaireG1);
		stage.getBatch().draw(inventaireG2, 0, 0,widthInventaireG,heightInventaireG2);
		//stage.getBatch().draw(inventaireH, widthInventaireG, heightInventaireD,widthInventaireD,heightInventaireH);
		stage.getBatch().draw(inventaireD, widthInventaireG, 0,widthInventaireD,Gdx.graphics.getHeight()-2);
		
		stage.getBatch().draw(portraitHero, widthInventaireG+50, Gdx.graphics.getHeight()-100,widthPortrait,heightPortrait);
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
		widthPortrait=50;
		heightPortrait=70;
		
		background = new Texture(Gdx.files.internal("ihmGame/Inventaire.png"));
		inventaireD = new Texture(Gdx.files.internal("ihmGame/InventaireD.png"));
		inventaireG1 = new Texture(Gdx.files.internal("ihmGame/InventaireG1.png"));
		inventaireG2 = new Texture(Gdx.files.internal("ihmGame/InventaireG2.png"));
		inventaireH = new Texture(Gdx.files.internal("ihmGame/InventaireH.png"));
		portraitHero = new Texture(Gdx.files.internal("personnages/PortraitHero.png"));

		btnUp = new Texture(Gdx.files.internal("ui/fondBleu.png"));
		btnDown = new Texture(Gdx.files.internal("ui/fondBleuClair.png"));
		btnChecked = new Texture(Gdx.files.internal("ui/fondBleuChecked.png"));        

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);//stage reçoit les input

		/**** utiliser un skin ****/
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));        
		//Label titre = new Label("Interface de jeu", skin);


		/*** créer son style ****/
		BitmapFont fontPerso = new BitmapFont(Gdx.files.internal("ui/default.fnt"),Gdx.files.internal("ui/default.png"), false);
		LabelStyle style = new LabelStyle(fontPerso, new Color(Color.CYAN));//fonte et couleur

		/** créer son style de bouton ***/
		TextButtonStyle styleBouton = new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(btnUp)),
				new TextureRegionDrawable(new TextureRegion(btnDown)),
				new TextureRegionDrawable(new TextureRegion(btnChecked)) ,
				fontPerso);
		styleBouton.over = new TextureRegionDrawable(new TextureRegion(btnDown));
		
		TextButton btnHero = new TextButton("Selection du hero",styleBouton);
		TextButton btnInventaire = new TextButton("Inventaire",styleBouton);
		TextButton btnQuit = new TextButton("quitter",styleBouton);
		btnQuit.setPosition(30, 50);btnQuit.setSize(100, 30);
		btnHero.setPosition(30, 370);btnQuit.setSize(100, 30);
		btnInventaire.setPosition(30, 300);btnQuit.setSize(100, 30);

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
		stage.addActor(btnHero);
		stage.addActor(btnInventaire);

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
		inventaireG1.dispose();
		inventaireG2.dispose();
		inventaireH.dispose();
		inventaireD.dispose();
		portraitHero.dispose();
		btnUp.dispose();
		btnDown.dispose();
		btnChecked.dispose();
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

}
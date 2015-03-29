package jeu;

import screens.EcranAccueil;
import screens.GameOver;
import screens.Inventaire;
import game.Underworld;
import heros.Animator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import constantes.Constantes;
import dialogues.TextWrapper;

public class Map extends Game implements Screen {

	private Underworld game;    
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private int valDeplacement;
	private int xCam;
	private int yCam;
	private boolean inAngleWidth=false;
	private boolean inAngleHeight=false;
	private int xCamInAngle;
	private int yCamInAngle;
	private SpriteBatch batch;
	private Animator animation;
	private float stateTime; 
	private Animation walkAnimation; 
	private TextureRegion currentFrame;
	private Texture walkSheet;
	private int xhero;
	private int yhero;
	private boolean animeChange = false;
	private int tailleMapX;
	private int tailleMapY;
	private TiledMapTileLayer collisionLayer1;
	private TiledMapTileLayer collisionLayer2;
	private TiledMapTileLayer collisionLayer3;
	private int xImage;
	private int yImage;
	private BitmapFont font;
	private SpriteBatch spriteBatch;
	private boolean dejaCreer=false;
	private Music music;

	public Map(Underworld game){
		this.game=game;
	}

	public Map(int x, int y, int positionPerso, Underworld game){
		this.xhero=x;
		this.yhero=y;
		valDeplacement=positionPerso;
		this.game=game;
		dejaCreer=true;
	}

	@Override
	public void render(float delta) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		font = new BitmapFont(Gdx.files.internal("ui/default.fnt"), Gdx.files.internal("ui/default.png"), false);
		font.setColor(Color.BLACK);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		renderer.render();//rendu de la carte        
		camera.update();//important sinon pas de prise en compte des input camera !!        
		renderer.setView(camera);//on indique la caméra utilisée pour coupler les systèmes de coordonnées   


		handleInput();
		stateTime += Gdx.graphics.getDeltaTime();           
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);  

		batch.begin();

		batch.draw(currentFrame, xhero, yhero);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		
		/*
		 * sound
		 */
		
		/*
		 * Map power
		 */
		TmxMapLoader loader = new TmxMapLoader();//création du loader de carte
		map = loader.load("map/labo.tmx");//chargement de la carte
		renderer = new OrthogonalTiledMapRenderer(map);//création du renderer

		/*
		 * on check la collision sur chaque couche de la map
		 * Ici nous avons 3 couches
		 */
		collisionLayer1=(TiledMapTileLayer) map.getLayers().get(0);
		collisionLayer2=(TiledMapTileLayer) map.getLayers().get(1);
		collisionLayer3=(TiledMapTileLayer) map.getLayers().get(2);

		/*
		 * Perso Power
		 * The mapWidth and mapHeight are the dimensions of the map in tiles. So they would both be 10 in a 10x10 grid. 
		 * The tilePixelWidth and tilePixelHeight are the dimensions of the tiles in pixels. In a 32x32 tileset they would both be 32. 
		 * Multiplying the dimensions together you will get the map dimensions in pixels.
		 */

		tailleMapX = (map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class));
		tailleMapY = (map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class));

		xCam=Constantes.fenetreWidth-100;
		yCam=Constantes.fenetreHeight-100;


		if(!dejaCreer){

			//chargement animation
			animation = new Animator("personnages/persoSeulBAS.png");
			stateTime = animation.getStateTime();
			currentFrame = animation.getTextreRegion();
			walkAnimation = animation.getWalkAnimation();
			walkSheet = animation.getWalkSheet();

			xImage=walkSheet.getWidth()/(map.getProperties().get("tilewidth", Integer.class));
			yImage=walkSheet.getHeight();

			xhero=tailleMapX/2;
			yhero=tailleMapY/2 - (walkSheet.getHeight()/(map.getProperties().get("height", Integer.class)) );

			music=Gdx.audio.newMusic(Gdx.files.internal("sons/Prelude.wav"));
			music.setLooping(true);
			music.play();
//			xhero=100;
//			yhero=100;
			
		} else {
			updatePictureFixe(valDeplacement);
		}

		//chargement camera
		camera = new OrthographicCamera(xCam, yCam);//taille du viewPort
		camera.position.set(xhero,yhero,0);//position de la caméra dansle monde 2D...centrée sur le perso!!
		camera.update();

		batch = new SpriteBatch();
	}

	private void handleInput() {
		// TODO Auto-generated method stub

		int x = xhero/collisionLayer1.getWidth();
		int y = yhero/collisionLayer1.getHeight();

		boolean collisionX=false;
		boolean collisionY=false;

		/*
		 * UP = 3
		 * right = 1
		 * down = 2
		 * left = 0
		 */

		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)){
			x=(xhero-2)/collisionLayer1.getWidth();
			collisionX=checkBlocked(x, y);

			if(!collisionX){
				xhero=xhero-2;
			}
			if(!animeChange||valDeplacement!=0) {
				animeChange = true;
				updatePicture(0);
				valDeplacement=0;
			}
			if(inAngleWidth && xhero==xCamInAngle){
				inAngleWidth=false;
			} else if(xhero-(camera.viewportWidth/2)>0 && !inAngleWidth) {
				if(!collisionX) camera.translate(-2, 0, 0);
				inAngleWidth=false;
			} else{
				if(!inAngleWidth){
					xCamInAngle = xhero;
				}
				inAngleWidth = true;
			}
		} else if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)){

			x=(xhero+2)/collisionLayer1.getWidth();
			collisionX=checkBlocked(x, y);

			if(!collisionX){
				xhero=xhero+2;
			}
			if(!animeChange||valDeplacement!=1) {
				animeChange = true;
				updatePicture(1);
				valDeplacement=1;
			}

			if(inAngleWidth && xhero==xCamInAngle){
				inAngleWidth=false;
			} else if(xhero+(camera.viewportWidth/2)<tailleMapX && !inAngleWidth) {
				if(!collisionX) camera.translate(2, 0, 0);
				inAngleWidth=false;
			} else{
				if(!inAngleWidth){
					xCamInAngle = xhero;
				}
				inAngleWidth = true;
			}
		} else if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)){

			y=(yhero-2)/collisionLayer1.getWidth();
			collisionY=checkBlocked(x, y);

			if(!collisionY){
				yhero=yhero-2;
			}
			if(!animeChange||valDeplacement!=2) {
				animeChange = true;
				updatePicture(2);
				valDeplacement=2;
			}
			if(inAngleHeight && yhero==yCamInAngle){
				inAngleHeight=false;
			} else if(yhero-(camera.viewportHeight/2)>0 && !inAngleHeight) {
				if(!collisionY) camera.translate(0, -2, 0);
				inAngleHeight=false;
			} else{
				if(!inAngleHeight){
					yCamInAngle = yhero;
				}
				inAngleHeight = true;
			}
		} else if(Gdx.input.isKeyPressed(Keys.DPAD_UP)){

			y=(yhero+2)/collisionLayer1.getWidth();
			collisionY=checkBlocked(x, y);

			if(!collisionY){
				yhero=yhero+2;
			}
			if(!animeChange||valDeplacement!=3) {
				animeChange = true;
				updatePicture(3);
				valDeplacement=3;
			}
			if(inAngleHeight && yhero==yCamInAngle){
				inAngleHeight=false;
			} else if(yhero+(camera.viewportHeight/2)<tailleMapY && !inAngleHeight) {
				if(!collisionY) camera.translate(0, 2, 0);
				inAngleHeight=false;
			} else{
				if(!inAngleHeight){
					yCamInAngle = yhero;
				}
				inAngleHeight = true;
			}
		} else if (Gdx.input.isKeyPressed(Keys.ESCAPE)){
			
			game.setScreen(new Inventaire(game,xhero,yhero,valDeplacement));
			
		} else { // on appuit sur rien
			animeChange = false;
			updatePictureFixe(valDeplacement);
		}
	}
	
	private void updatePictureFixe(int i){
		switch(i){
		case 0:
			animation = new Animator("personnages/persoSeulGAUCHE.png");
			break;
		case 1:
			animation = new Animator("personnages/persoSeulDROIT.png");
			break;
		case 2:
			animation = new Animator("personnages/persoSeulBAS.png");
			break;
		case 3:
			animation = new Animator("personnages/persoSeulHAUT.png");
			break;
		}
		stateTime = animation.getStateTime();
		currentFrame = animation.getTextreRegion();
		walkAnimation = animation.getWalkAnimation();
	}

	private void updatePicture(int i){
		switch(i){
		case 0:
			animation = new Animator("personnages/herosGauche.png");
			stateTime = animation.getStateTime();
			currentFrame = animation.getTextreRegion();
			walkAnimation = animation.getWalkAnimation();
			break;
		case 1:
			animation = new Animator("personnages/herosDROITE.png");
			stateTime = animation.getStateTime();
			currentFrame = animation.getTextreRegion();
			walkAnimation = animation.getWalkAnimation();
			break;
		case 2:
			animation = new Animator("personnages/herosBAS.png");
			stateTime = animation.getStateTime();
			currentFrame = animation.getTextreRegion();
			walkAnimation = animation.getWalkAnimation();
			break;
		case 3:
			animation = new Animator("personnages/herosHAUT.png");
			stateTime = animation.getStateTime();
			currentFrame = animation.getTextreRegion();
			walkAnimation = animation.getWalkAnimation();
			break;
		default :
		}
	}

	private boolean checkBlocked(int x, int y){

		if(collisionLayer3.getCell(x, y) != null && 
				collisionLayer3.getCell(x,y).getTile().getProperties().containsKey("blocked")){
			return true;
		} else if(collisionLayer2.getCell(x, y) != null &&
				collisionLayer2.getCell(x,y).getTile().getProperties().containsKey("blocked")){
			return true;
		} else if(collisionLayer1.getCell(x, y) != null &&
				collisionLayer1.getCell(x,y).getTile().getProperties().containsKey("blocked")){
			return true;
		} else return false;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

}
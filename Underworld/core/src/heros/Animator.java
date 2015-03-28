package heros;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator implements ApplicationListener {

    private static final int        FRAME_COLS = 4;         // #1
    private static final int        FRAME_ROWS = 1;         // #2

    private Animation                       walkAnimation;          // #3
    private Texture                         walkSheet;              // #4
    private TextureRegion[]                 walkFrames;             // #5
    private SpriteBatch                     spriteBatch;            // #6
    private TextureRegion                   currentFrame;           // #7

    private float stateTime;               // #8
    private String picture;
    
    
    public Animator(String picture){
    	this.picture=picture;

        walkSheet = new Texture(Gdx.files.internal(picture)); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.25f, walkFrames);      // #11
        spriteBatch = new SpriteBatch();                // #12
        stateTime = 0f;                         // #13
    }

    @Override
    public void create() {
    	
    }

    public TextureRegion getTextreRegion() {
		// TODO Auto-generated method stub
		return currentFrame;
	}
    
    public Texture getWalkSheet(){
    	return walkSheet;
    }
    
    public Animation getWalkAnimation(){
    	return walkAnimation;
    }
    
    public float getStateTime(){
    	return stateTime;
    }

	@Override
    public void render() {
		// TODO Auto-generated method stub
    }

	@Override
	public void resize(int width, int height) {
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
}
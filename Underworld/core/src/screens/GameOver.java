package screens;

import game.Underworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
 
public class GameOver implements Screen{
    Stage stage;
    Underworld game;
    Texture background;
     
    public GameOver (Underworld game){this.game = game;}
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//corrige sans caméra !!
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
        background = new Texture(Gdx.files.internal("ihmGame/Gameover.png"));
        stage = new Stage();        
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
 
}
package dialogues;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TextWrapper{
    private String text;
    private Vector2 position;
    private BitmapFont font;
    private int red;
    private int green;
    private int blue;
    private int opacity;
    private int width;
    private int height;
     
     
    //Constructeur
    /*
     * @param text String R�cup�re le texte
     * @param pos Vector2 R�cup�re la position du texte
     * */
    public TextWrapper(String txt, BitmapFont bf, Vector2 vector2){
        text = txt;
        position = vector2;
        font = bf;
        red = 0;
        green = 0;
        blue = 0;
        opacity = 255;
    }
 

	//Affiche du texte de fa�on simple
    /*
     * @param sb SpriteBatch R�cup�re le spritebatch de l'application
     * @param bf BitmapFont R�cup�re la font du texte � afficher
     * */
    public void Draw(SpriteBatch sb){
        width = (int)font.getBounds(text).width; //R�cup�re la largeur du text de la font utilis�e
        height = (int)font.getBounds(text).height; //R�cup�re la hauteur du text de la font utilis�e
        font.draw(sb, text, position.x - width/2, Gdx.graphics.getHeight() - position.y + height/2); //Affiche le text au milieu;
    }
     
    //Fait apparaitre le texte
    /*
     * @param duree int d�termine la vitesse d'apparition du texte
     * */
    public void fadeIn(int duree, SpriteBatch sb){
         
        int i = 0;
        while(i < 255){
            Gdx.gl.glClearColor(0, 0, 0, 255);
            sb.begin();
            Gdx.gl.glClear(GL20.GL_COLOR_CLEAR_VALUE);
            font.setColor(red, green, blue, i);
            Draw(sb);
             
            sb.end();
            try {
                Thread.sleep(duree);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            i++;
             
        }
    }
     
    //Fait dispara�tre le texte
    public void fadeOut(int duree){
         
    }
     
    public void setText(String s){
        text = s;
    }
     
    public void setColor(int r, int g, int b){
        font.setColor(r, g, b, 255);
    }
 
}
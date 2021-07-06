package com.example.shawnengine;
 
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
 
public class StaticSprite
{
       private static final int BMP_ROWS = 1;
       private static final int BMP_COLUMNS = 1;
       private int x = 0;
       private int y = 0;
       private int xSpeed = 5;
       private int ySpeed = 5;
       private GameView gameView;
       private Bitmap bmp;
       private int currentFrame = 0;
       private int width;
       private int height;
       private boolean gravity = true;
       private boolean jump = false;
       
       private boolean jumping = false;
       
       private boolean onPlatform = false;
 
       public StaticSprite(GameView gameView, Bitmap bmp,int _x, int _y) 
       {
             this.gameView = gameView;
             this.bmp = bmp;
             this.width = bmp.getWidth() / BMP_COLUMNS; //final width
             this.height = bmp.getHeight() / BMP_ROWS; //final height
             this.x = _x;
             this.y = _y;
       }
 
       private void update()
       {
    	   /*
             if (x > gameView.getWidth() - width - xSpeed)
             {
                    xSpeed = -5;
             }
             if (x + xSpeed < 0)
             {
                    xSpeed = 5;
             }
             */
             //x = x + xSpeed;

    	   //currentFrame = ++currentFrame % BMP_COLUMNS;
    	   
    	
            
       }
 
       public void draw(Canvas canvas)
       {
             update();
             int srcX = currentFrame * width;
             int srcY = 0;
             Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
             Rect dst = new Rect(x, y, x + width, y + height);
             canvas.drawBitmap(bmp, src, dst, null);
       }
       
       
       public boolean hasCollided(float x2, float y2) 
       {
             return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
       }
       
       public int getX()
       {
    	   return x;
       }
       
       public int getY()
       {
    	   return y;
       }
       
       public int getWidth()
       {
    	   return width;
       }
       
       public int getHeight()
       {
    	   return height;
       }
       
       public boolean hasTouched(float x2, float y2) 
       {
             return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
       }
      
       
	
}
package com.example.shawnengine;
 
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
 
public class SplashScreen
{
      
       private int x = 0;
       private int y = 0;
       private int xSpeed = 20;
       private GameView gameView;
       private Bitmap bmp;
       private int currentFrame = 0;
       private int width;
       private int height;
       
       Rect src;

       Rect dst;
       Rect dst2;
 
       public SplashScreen(GameView gameView, Bitmap bmp) 
       {
             this.gameView = gameView;
             this.bmp = bmp;
             this.width = bmp.getWidth();
             this.height = bmp.getHeight();
             
             src = new Rect(x, y, x + width, y + height); //not needed cos use entire image
             
            // Rect dst = new Rect(x, y, x + width, y + height);
             dst = new Rect(x, y, 1280, 720);
             dst2 = new Rect(x + 1280, y, x + 2560, 720);
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
           //  x = x + xSpeed;
             
             //move dst rect
             
           
             
             
             
       }
       
       //draw is a loop
       public void draw(Canvas canvas)
       {
             update();
             int srcX = width;
             int srcY = height;
             

             canvas.drawBitmap(bmp, src, dst, null);
             
            

    	
             canvas.drawBitmap(bmp, src, dst2, null);
    	         

             
           
       }

       
       public boolean hasCollided(float x2, float y2) 
       {
            if (x2 < x && y2 > y)
            {
            	//inside the bg
            	
            	return true;
            }
            else
            {
            	return false;
            }
       }
}
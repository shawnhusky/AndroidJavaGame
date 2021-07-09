package com.example.shawnengine;
 
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
 
public class Background
{
      
       private int x = 0;
       private int y = 0;
       private int xSpeed = 15;
       private GameView gameView;
       private Bitmap bmp;
       private int currentFrame = 0;
       private int width;
       private int height;
       
       Rect src;

       Rect dst;
       Rect dst2;
 
       public Background(GameView gameView, Bitmap bmp, int _speed) 
       {
             this.gameView = gameView;
             this.bmp = bmp;
             this.width = bmp.getWidth();
             this.height = bmp.getHeight();
             this.xSpeed = _speed;
             
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
             
             dst.left -= 20;
             dst.right -= 20;
             
             dst2.left -= 20;
             dst2.right -= 20;
             
             
             //check if the rect has gone out of the screen
             
             if (dst.right <= 0)
             {
            	 //put the rect at the back
            	 
            	 dst.left = dst2.right;
            	 dst.right = dst2.right + 1280;

             }
             else  if (dst2.right <= 0)
             {
            	 //put the rect at the back
            	 
            	 dst2.left = dst.right;
            	 dst2.right = dst.right + 1280;

             }
             
             
             
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
       
       public int getX()
       {
    	   return x;
       }
       
       public int getY()
       {
    	   return y;
       }
       
       public void setX(int _x)
       {
    	   x = _x;
       }
       
       public void setY(int _y)
       {
    	   y = _y;
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
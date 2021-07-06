package com.example.shawnengine;
 
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
 
public class Sprite
{
       private static final int BMP_ROWS = 1;
       private static final int BMP_COLUMNS = 3;
       private int x = 0;
       private int y = 0;
       private int xSpeed = 5;
       private int ySpeed = 5;
       private GameView gameView;
       private Bitmap bmp;
       private int currentFrame = 0;
       private int width;
       private int height;
       private boolean gravity = false;
       private boolean jump = false;
       
       private boolean jumping = false;
       
       private boolean onPlatform = false;
 
       public Sprite(GameView gameView, Bitmap bmp,int _x, int _y) 
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

    	   currentFrame = ++currentFrame % BMP_COLUMNS;
    	   
    	
    	   
             //gravity
             
             if(gravity)
             {
            	 y = y + ySpeed; 
             }
             else
             {
            	 jumping = false; //prevent jumping mid air
             }
             
             //jump
             
             if (jump)
             {
            	 jumping = true;
            	 
        	 	if (y > 400)
        	 	{
        	 		y -= 15;
        	 	}
        	 	else
        	 	{
        	 		jump = false;
        	 		
        	 	}
            
             }
             
             //check if hit the tile

            
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
       
       public void setX(int _x)
       {
    	   x = _x;
       }
       
       public void setY(int _y)
       {
    	   y = _y;
       }
       
       public int getWidth()
       {
    	   return width;
       }
       
       public int getHeight()
       {
    	   return height;
       }
       

       public void setGravity(boolean _gravity)
       {
    	   gravity = _gravity;
       }
       
       public void setOnPlatform(boolean _onPlatform)
       {
    	   onPlatform = _onPlatform;
       }
       
       public boolean getOnPlatform()
       {
    	   return onPlatform;
       }
       
       public void jump()
       {
    	   //dont allow double jump
    	   
    	   	if (jumping == false)
    	   	{
    	   		jump = true;
    	   		
    	   	}
    	   	else
    	   	{
    	   		jump = false;
    	   	}
    	   	
    	   		
       }
       
	
}
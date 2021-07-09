package com.example.shawnengine;
 
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
 
public class Leaf
{
       private static final int BMP_ROWS = 1;
       private static final int BMP_COLUMNS = 2;
       private int x = 0;
       private int y = 0;
       private int xSpeed = 20;
       private int ySpeed = 10;
       private GameView gameView;
       private Bitmap bmp;
       private int currentFrame = 0;
       private int width;
       private int height;
       
       private boolean moveVertical = false;

 
       public Leaf(GameView gameView, Bitmap bmp,int _x, int _y) 
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
    	   
             
    	   if (y > 600)
	   	 	{
    		   moveVertical = false;
	   	 	}
	   	 	else
	   	 	{
	   	 		moveVertical = true;
	   	 		
	   	 	}
    	   
    	   if (moveVertical)
    	   {
    		   y = y + ySpeed;
    	   }
    	   

             x = x - xSpeed;

    	   currentFrame = ++currentFrame % BMP_COLUMNS;
    	   
    	
    	   
            
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
       
       
       public boolean hasCollided(float _x, float _y, float _width, float _height) 
       {
	    	 //Calculate the sides of player
	   	   	float leftA = _x;
	   	   	float rightA = _x + _width;
	   	   	float topA = _y;
	   	   	float bottomA = _y + _height;
	   	        
	   	    //Calculate the sides of leaf
	   	  	float leftB = x;
	   	   	float rightB = x + width;
	   	   	float topB = y;
	   	   	float bottomB = y + height;
	   	   
	
	   	    //If any of the sides from A are outside of B
	   	    if( bottomA <= topB || topA >= bottomB || rightA <= leftB ||  leftA >= rightB)
	   	    {
	   	        return false;
	   	    }
	   	    else
	   	    {
	   	    	//If none of the sides from A are outside B
	   	    	return true;
	   	    }
	
	   	    
	   
   	   	
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
       
   
      
       
     
       
	
}
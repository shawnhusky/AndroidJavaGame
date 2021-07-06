package com.example.shawnengine;
 
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
 
public class Tile
{

       private int x = 0;
       private int y = 0;
       private int xSpeed = -7;
       private GameView gameView;
       private Bitmap bmp;
       private int currentFrame = 0;
       private int width;
       private int height;
       private boolean move = false;
 
       public Tile(GameView gameView, Bitmap bmp, int _x, int _y, boolean _move) 
       {
             this.gameView = gameView;
             this.bmp = bmp;
             this.width = bmp.getWidth();
             this.height = bmp.getHeight();
             this.x = _x;
             this.y = _y;
             move = _move;
       }
 
       private void update()
       {
    	   
    	   if (move)
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
             
             x = x + xSpeed;
          //  currentFrame = ++currentFrame % BMP_COLUMNS;
    	   }
    	   
    	   
    	
    	   	
             
       }
       
       //no set x and y cos if u setX or Y in draw it will keep looping so no point.
       
 
       public void draw(Canvas canvas)
       {
             update();
             int srcX = currentFrame * width;
             int srcY = currentFrame * height;
             Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
             Rect dst = new Rect(x, y, x + width, y + height);
             canvas.drawBitmap(bmp, src, dst, null);
       }

       
       public boolean hasCollided(float x2, float y2) 
       {
             return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
       }
       
       public boolean hasCollided(float _x, float _y, float _width, float _height) 
       {

    	   //Calculate the sides of player
    	   	float leftA = _x;
    	   	float rightA = _x + _width;
    	   	float topA = _y;
    	   	float bottomA = _y + _height;
    	        
    	    //Calculate the sides of platform
    	  	float leftB = x;
    	   	float rightB = x + width;
    	   	float topB = y;
    	   	float bottomB = y + height;
    	   


    	   	if (bottomA <= topB || leftA >= rightB || rightA <= leftB)
    	   	{
    	   		return false;
    	   		
    	   	}
    	   	else
    	   	{
    	   		return true;
    	   	}
    	   	
    	   	
    	   	
    	   	/*
    	   	//bottom of player less than the top of the tile and
    	   //the player left side is more than the tile rightside
    	   
    	   	if ( _y + _height < y || _x > x + width || _x + _width < x)
    	   	{
    	   		return false;
    	   	}
    	   	else
    	   	{
    	   		return true;
    	   	}
    	   	*/
    	   	
    	   	
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
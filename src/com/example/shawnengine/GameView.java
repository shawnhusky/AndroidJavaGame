package com.example.shawnengine;

import java.util.ArrayList;
import java.util.List;







import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;



import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;







import java.util.Random;

public class GameView extends SurfaceView
{
		private Activity myActivity;
		
	
	
       private Bitmap badboy;//declare bitmap name
       private Sprite badboySprite;// declare sprite sheet name
       
       private Bitmap playText;//declare bitmap name
       private StaticSprite playTextSprite;// declare sprite sheet name
       
       private Bitmap aboutUsText;//declare bitmap name
       private StaticSprite aboutUsTextSprite;// declare sprite sheet name
       
       private Bitmap title;//declare bitmap name
       private StaticSprite titleSprite;// declare sprite sheet name
       

       
       private Bitmap startingTile;//declare bitmap name
       private Tile startingTileTile;// declare sprite sheet name
       
       private Bitmap bg1;//declare bitmap name
       private Background bg1Background;// declare sprite sheet name
       
       private Bitmap bg3;//declare bitmap name
       private Background bg3Background;// declare sprite sheet name
       
       private Bitmap bg2;//declare bitmap name
       private Background bg2Background;// declare sprite sheet name
       
       private Bitmap bg4;//declare bitmap name
       private Background bg4Background;// declare sprite sheet name
       


       private Bitmap s1;//declare bitmap name
       private SplashScreen s1SplashScreen;// declare sprite sheet name
       
       private Bitmap s2;//declare bitmap name
       private SplashScreen s2GameOverScreen;// declare sprite sheet name
       
       private Bitmap s3;//declare bitmap name
       private SplashScreen s3AboutUsScreen;// declare sprite sheet name

          
       private SurfaceHolder holder;
       private GameLoopThread gameLoopThread;
       
       private int currentState = 0; //0 for splash screen, 1 for in game,2 for game over, 3 for main menu
       
       private int gameScore = 0;
       
       private long lastClick;
       
       
       //timer for generating leaves
       //timer for splash screen and generating of platforms
       
       long startTime2 = System.currentTimeMillis();
       long elapsedTime2;
       long elapsedSeconds2;
       
     
       
       //tile array
       private List<Tile> tileList = new ArrayList<Tile>();
       
       //leaves array
       private List<Leaf> leafList = new ArrayList<Leaf>();
       
       //random generator

       
       Random generator2 = new Random();
       int randomNumber2 = 1;
      
       public GameView(Context context) 
       {
             super(context);
             
            
             
             myActivity = (Activity) context;
             
             //play music--------------------
             playMusic();
             
             gameLoopThread = new GameLoopThread(this);
             holder = getHolder();
             holder.addCallback(new SurfaceHolder.Callback() 
             {
 
                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) 
                    {
                           boolean retry = true;
                           gameLoopThread.setRunning(false);
                           while (retry) 
                           {
                                  try 
                                  {
                                        gameLoopThread.join();
                                        retry = false;
                                  } catch (InterruptedException e) 
                                  {
                                  }
                           }
                    }
 
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) 
                    {
                           gameLoopThread.setRunning(true);
                           gameLoopThread.start();
                    }
 
                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format,
                                  int width, int height)
                    {
                    }
             });
             
             //creation of image objects
             badboy = BitmapFactory.decodeResource(getResources(), R.drawable.bad51);
             badboySprite = new Sprite(this,badboy,0,560);
             
             playText = BitmapFactory.decodeResource(getResources(), R.drawable.play);
             playTextSprite = new StaticSprite(this,playText,375,300);
             
             aboutUsText = BitmapFactory.decodeResource(getResources(), R.drawable.aboutus);
             aboutUsTextSprite = new StaticSprite(this,aboutUsText,375,380);
             
             title = BitmapFactory.decodeResource(getResources(), R.drawable.titlep);
             titleSprite = new StaticSprite(this,title,350,200);
             

          
             startingTile = BitmapFactory.decodeResource(getResources(), R.drawable.startingcliff);
             startingTileTile = new Tile(this,startingTile,0,620,true);

             
             bg1 = BitmapFactory.decodeResource(getResources(), R.drawable.shawnwhitebg);
             bg1Background = new Background(this,bg1,15);
             
             bg2 = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
             bg2Background = new Background(this,bg2,15);
             
             bg3 = BitmapFactory.decodeResource(getResources(), R.drawable.shawnplant);
             bg3Background = new Background(this,bg3,5);
             
             bg4 = BitmapFactory.decodeResource(getResources(), R.drawable.startingcliffbg);
             bg4Background = new Background(this,bg4,15);
             

             
          
             s1 = BitmapFactory.decodeResource(getResources(), R.drawable.splashscreen1);
             s1SplashScreen = new SplashScreen(this,s1);
             
             s2 = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
             s2GameOverScreen = new SplashScreen(this,s2);
             
             s3 = BitmapFactory.decodeResource(getResources(), R.drawable.aboutusscreen);
             s3AboutUsScreen = new SplashScreen(this,s3);
      
 
             
             //add tile obj to the array
             
           
           tileList.add(createSprite(R.drawable.platform));
           leafList.add(createLeaf(R.drawable.leafdown));
           
             
       }
       
       private void playMusic()
       {
    	   
    	  MediaPlayer bgMusic = new MediaPlayer();

           
    	    
   	    bgMusic = MediaPlayer.create(myActivity, R.raw.shawnmelody);
    	  
   	    
   	    bgMusic.setLooping(true);
   	    

   	    
   	    bgMusic.start();
   	
       }
       
       private void playWaterSound()
       {
    	   
    	  MediaPlayer bgMusic = new MediaPlayer();

           
    	    
    	  bgMusic = MediaPlayer.create(myActivity, R.raw.gameoversound);
    	  
	
	
		   	    bgMusic.start();

       }
       
       private void playJumpSound()
       {
    	   
    	  MediaPlayer bgMusic = new MediaPlayer();

           
    	    
    	  bgMusic = MediaPlayer.create(myActivity, R.raw.yippie);
    	  
	
	
		   	    bgMusic.start();

       }
       
       
       
       
       private Tile createSprite(int resource)
       {
             Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
             return new Tile(this,bmp,1700,620,true);
       }
       
       private Leaf createLeaf(int resource)
       {
    	   //add to counter
    	   gameScore ++;
    	   
             Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
             return new Leaf(this,bmp,1280,50);
       }
       

       
       @Override
       public void draw(Canvas canvas)
       {
    	   
    	   
    	   //splash screen
    	   
    	   if (currentState == 0)
		   {
			   
			   elapsedTime2 = System.currentTimeMillis() - startTime2;
		       elapsedSeconds2 = elapsedTime2 / 1000;
			   
			   if (elapsedSeconds2 <= 2)
			   {
		
				   s1SplashScreen.draw(canvas);
				   
			   }
			   else
			   {
				   s1SplashScreen = null;
				   //System.gc();
				   
				   //reset start time
	    		   startTime2 = System.currentTimeMillis();
	    		   
				   currentState = 3;
				   
			   }
    	   }
    	   
    	   
    	   if (currentState == 3)
    	   {
    		   //main menu
    		   
    		   //DRAWING!--------------------------------------
   	        
   	     	bg1Background.draw(canvas);
   	     	
   	     	bg3Background.draw(canvas);
   	     	
   	     	
   	     	badboySprite.draw(canvas);
   	     	
   	     bg4Background.draw(canvas);
	     
	     bg2Background.draw(canvas);
	     
	     playTextSprite.draw(canvas);
	     titleSprite.draw(canvas);
	     aboutUsTextSprite.draw(canvas);

   	     	
    	   }
    	
    	   
    	   
    	   //in game
    	   
    	   
    	   if (currentState == 1)
    	   {

    		  // s2GameOverScreen.draw(canvas); //for testing 
	    	 
		    	   //DRAWING!--------------------------------------
	    	        
    		   	
    		   		
	    	     	bg1Background.draw(canvas);
	    	     
	    	     	bg3Background.draw(canvas);
	    	    
	    	     	
	    	     	badboySprite.draw(canvas);
	    	     	
	    	     	
    			
	    	     
	    	      //draw everything in the tilelist
	    	      
	    	        for (Tile tile : tileList) 
			        {
	    	        	
	    	        	tile.draw(canvas);	
	    	       
			        }
	    	        
	    	

	    	      //draw sprite sheets
	    	        
	    	   

		    	     startingTileTile.draw(canvas);
		    	     
		    	     bg2Background.draw(canvas);
		    	     
		    	     
	     		  	 //draw everything in the leafList
		    	      
	    	        for (Leaf leaf : leafList) 
			        {
	    	        	
	    	        	leaf.draw(canvas);	
	    	       
			        }
	    	     	
			    	     
		    	     
		    	     //check if player fell down and hit bottom of screen
		    	      
		    	      if (badboySprite.getY() + badboySprite.getHeight() > 720)
		    	      {
		    	    	  //collided
		    	    	  
		    	    	  currentState = 2;
		    	      }
		    	      
		    	      
		    	
		    	      
		    	 
		   	    	   
		    	      //timer to generate platforms and leaves
		    	      
		   	    	     elapsedTime2 = System.currentTimeMillis() - startTime2;
		   	    	     elapsedSeconds2 = elapsedTime2 / 1000;
		   	    	   
		   	    	   if (elapsedSeconds2 == randomNumber2)
		   	    	   {
		   	    		   	
		   	    		   tileList.add(createSprite(R.drawable.platform));
		   	    		   
		   	    		   if(elapsedSeconds2 >= 2)
		   	    		   {
		   	    			   leafList.add(createLeaf(R.drawable.leafdown));
		   	    		   }

		   	    		   randomNumber2 =  1+ generator2.nextInt(2);//generates from 3 to 5s
		   	    		   
		   	    		   //reset start time
		   	    		  startTime2 = System.currentTimeMillis();
		   	
		   	    	   }
		   	    	   
		   	    
		   	    	   
		   	 
		   
	    	      
	    	     
	  
	    	      //check if player collide till the preset tile
	   
	    	      
	    	     if ( startingTileTile.hasCollided(badboySprite.getX(),badboySprite.getY(),badboySprite.getWidth(),badboySprite.getHeight()))
	    	     {
	    	    	 //return true means collided
	    	    	 
	    	    	 badboySprite.setGravity(false);
	    	    	 badboySprite.setOnPlatform(true);
	    	     }
	    	     else
	    	     {
	    	    	 badboySprite.setGravity(true);
	    	    	 badboySprite.setOnPlatform(false);
	    	     }
	    	     
	    	
	    	          
	    	     
	    	     //check tileList collided or not

	    	        for (Tile tile : tileList) 
			        {
	
	    	        	
	    	            if (tile.hasCollided(badboySprite.getX(),badboySprite.getY(),badboySprite.getWidth(),badboySprite.getHeight()))
	    	    	     {
	    	    	    	 //return true means collided
	    	    	    	 
	    	    	    	 badboySprite.setGravity(false);
	    	    	    	 badboySprite.setOnPlatform(true);
	    	    	     }
			        }
	    	        
	    	 //check leafList collided or not
	    	        
	    	        for (Leaf leaf : leafList) 
			        {
	
	    	        	
	    	            if (leaf.hasCollided(badboySprite.getX(),badboySprite.getY(),badboySprite.getWidth(),badboySprite.getHeight()))
	    	    	     {
	    	    	    	 //return true means collided
	    	            	//game over
	    	            	
	    	            	currentState = 2;
	    	    	     }
			        }
	    	     
	    	     
    		   
			    	
			    	 
	    	       
	    	   }
	    	   
    	   
	    	   if (currentState == 2)
	    	   {
	    		   
	    		  
	    		
	    		   
	    	     
	    		   
	    		   
	    		   s2GameOverScreen.draw(canvas);
	    		     
	               //score text
	               
	               Paint scorePaint = new Paint();
	    		   scorePaint.setColor(Color.WHITE);
	    		   scorePaint.setTextSize(75);
	    		   
	    		   canvas.drawText("Score: " + gameScore, 400, 500, scorePaint);
	    		   
	    		   
	    		   
	    	   
	    		   /*
	    		   //cleanup previous state
	    		   
	    		   
	    		   badboySprite = null;

	    		   
	    	        for (Tile tile : tileList) 
			        {
	    	        	
	    	        	tileList.remove(tile);
	    	       
			        }
	    		   
	    		   
	    		   startingTileTile= null;
	    		   bg1Background= null;
	    		   bg3Background= null;
	    		   */
	    		   
	    	   }
	    	   
	    	   
	    	   //about us screen
	    	   
	    	   if (currentState == 4)
	    	   {
	    		   s3AboutUsScreen.draw(canvas);
	    	   }
    	   
    	   
    	   
       }
       
       
       public void resetGame()
       {
    	   //reset pos
 		  
 		   //restart time
		   startTime2 = System.currentTimeMillis();
		   
		   //set positions
		   badboySprite.setX(0);
		   badboySprite.setY(500);
		   
		   startingTileTile.setX(0);
		   startingTileTile.setY(620);
		   

     	
     	
     	//remove all tiles
     	
	      tileList.removeAll(tileList);
	      leafList.removeAll(leafList);
       }

      


       @Override
	     public boolean onTouchEvent(MotionEvent event)
	     {
    	   
	           if (System.currentTimeMillis() - lastClick > 1000) 
	           {
	                  lastClick = System.currentTimeMillis();
	                  //synchronized (getHolder())
	                  //{
	                	  if (currentState == 1)
	                	  {
	                		  //in game
	                		  
	                		  
		                	  if (badboySprite.getOnPlatform() == true)
		                	  {
		                		  //play jump sound
		                		  
		                		  playJumpSound();
		                		  
		                		  badboySprite.jump();
		                	  }
		                	  
	                	  }
	                	  
	                	  else if (currentState == 3)
	                	  {
	                		  //in the main menu
	                		  
	                		  //cleanup 
	                		
	              	     /*
	              	     playTextSprite= null;
	              	     titleSprite= null;
	              	     */
	              	     
	              	   //reset start time
	  	    		   //startTime2 = System.currentTimeMillis();
	                		  
	                	
	                		  //check if press play button
	                		  
	                		  if (playTextSprite.hasCollided(event.getX(), event.getY())) 
	                		  {
	                			  //touched
	                			  //go to game
	                			  playWaterSound();
	                			   currentState = 1;
	                		  }
	                		  else
	                		  {
	                			  //go to about us 
	                			  playWaterSound();
	                			  currentState = 4;
	                			  
	                		  }
		                		
		              	     
		              	    

	                		  
	                	  }
	                	 
	                	  
	                	  else if (currentState == 2)
	                	  {
	                		
	                		  
	                		  //game over state
	                		  
	                	//	  s2GameOverScreen = null;
	                		  
	                		  playWaterSound();
	                		 
	                		  resetGame();
	                		
			            
			    	     	  currentState = 1;
		    	     
			    	     	  
	       	    		   
	                	  }
	                	  
	                	  else if (currentState == 4)
	                	  {
	                		  //in about us screen
	                		  
	                		  //go back to menu
	                		  currentState = 3;
	                		  
	                	  }
	                	  

	                  }
	           //}
	           return true;
	     }
}
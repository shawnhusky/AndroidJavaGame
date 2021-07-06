package com.example.shawnengine;

import android.graphics.Canvas;


public class GameLoopThread extends Thread 
{
	   static final long fps = 60; //regulate framerate
       private GameView view;

       private boolean running = false;

       

       public GameLoopThread(GameView view) 
       {

             this.view = view;

       }

 

       public void setRunning(boolean run) 
       {

             running = run;

       }

 

       @Override

       public void run() 
       {

    	   long tickPerSecond = 1000/ fps;
    	   long startTime = 0;
    	   long sleepTime;
    	   
             while (running)
             {

                    Canvas c = null;

                    try {

                           c = view.getHolder().lockCanvas();

                           synchronized (view.getHolder()) 
                           {

                                  view.draw(c);

                           }

                    }
                    finally
                    {

                           if (c != null)
                           {

                                  view.getHolder().unlockCanvasAndPost(c);

                           }
                           
                           sleepTime = tickPerSecond-(System.currentTimeMillis() - startTime);
                           
                           try
                           {
                        	   if (sleepTime > 0)
                        	   {
                        		   sleep(sleepTime);
                        	   }
                        	   
                           }
                           catch(Exception e) {}

                    }

             }

       }

}   

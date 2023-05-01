package LAAD_Group._Main;

import LAAD_Group._VisualFiles.*;

public class MyVisual extends Visual
{       
    C20755199 andrei;
    C21317311 ledion;
    C21341851 albert;
    C21390151 dan;

    boolean startAndrei;
    public boolean startDan;
    boolean startAlbert;
    boolean startLedion;

    int currentVisual = 0;
     
    public void settings()
    {
        size(1024, 500,P3D);
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }
    
    public void setup()
    {
        andrei = new C20755199(this);
        dan = new C21390151(this);
        albert = new C21341851(this);
        ledion = new C21317311(this);
        
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("theSong.mp3");   

        
        // Call this instead to read audio from the microphone
        //startListening();      
    }
    
    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
            
        }
        if(key == '1'){
            currentVisual = 1;
        }
        if(key == '2'){
            currentVisual = 2;
        }
        if(key == '3'){
            currentVisual = 3;
        }
        if(key == '4'){
            currentVisual = 4;
        }      
      
    }

    public void draw()
    {
        //super.draw();
        background(0);
        try
        {
             //Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();
        switch (currentVisual) {
            case 1:
                andrei.draw();
                break;
            case 2:
                dan.draw();
                break;
            case 3:
                albert.draw();
                break;
            case 4:
                ledion.draw();
                break;
            default:
                break;
        }
        
    }
    
}

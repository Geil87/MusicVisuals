package LAAD_Group._VisualFiles;

import LAAD_Group._Main.*;

public class C21317311 extends Visual
{
    
    MyVisual mv;
    public C21317311(MyVisual m){
        this.mv = m;
    }
    

    float smoothedBoxSize = 0;

    public void draw()
    {
        //Don't uncomment
        /* mv.calculateAverageAmplitude();
        mv.background(0);
        mv.noFill();
        mv.lights();
        mv.stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        mv.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        mv.translate(0, 0, -250);*/
            
        float boxSize = 50 + (mv.getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);        
        
            mv.pushMatrix();
            mv.translate(-200, 0, 0);
            mv.rotateY(angle);
            mv.rotateX(angle);

            mv.triangle(0, 0, 0, smoothedBoxSize, 0, 0);
            
            mv.popMatrix();
            mv.pushMatrix();
            mv.translate(200, 0, 0);
            mv.rotateY(angle);
            mv.rotateX(angle);
            mv.strokeWeight(5); 

            mv.triangle(0, 0, 0, smoothedBoxSize, 0, 0);
            
            mv.popMatrix();
        
        
        angle += 0.01f;
    } 
    float angle = 0;
}

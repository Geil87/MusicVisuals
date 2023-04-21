package LAAD_Group._VisualFiles;

import LAAD_Group._Main.*;

public class C20755199 extends Visual
{
    MyVisual mv;
    public C20755199(MyVisual m){
        this.mv = m;
    }
    

    float smoothedBoxSize = 0;

    public void draw()
    {
        mv.calculateAverageAmplitude();
        mv.background(0);
        mv.noFill();
        mv.lights();
        mv.stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        mv.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        mv.translate(0, 0, -250);
               
        float boxSize = 50 + (mv.getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);        
        mv.pushMatrix();
        mv.translate(-100, 0, 0);
        mv.rotateY(angle);
        mv.rotateX(angle);
        mv.box(smoothedBoxSize);
        mv.strokeWeight(1);
        //mv.sphere(smoothedBoxSize);
        mv.popMatrix();
        mv.pushMatrix();
        mv.translate(100, 0, 0);
        mv.rotateY(angle);
        mv.rotateX(angle);
        mv.strokeWeight(5); 
        mv.box(smoothedBoxSize);
        mv.popMatrix();
        angle += 0.01f;
    }
    float angle = 0;
} 

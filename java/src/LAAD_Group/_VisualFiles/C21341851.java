package LAAD_Group._VisualFiles;

import LAAD_Group._Main.*;

public class C21341851 extends Visual
{
    boolean isOn = false;
    MyVisual mv;
    public C21341851(MyVisual m){
        this.mv = m;
    }
   

    float smoothedCircleSize = 0;

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

        float circleSize = 50 + (getAmplitude() * 300);
        smoothedCircleSize = lerp(smoothedCircleSize, circleSize, 0.2f);        
        
            mv.pushMatrix();
            mv.translate(-100, -100, 0);
            mv.rotateY(angle);
            mv.rotateX(angle);
            mv.strokeWeight(5);
            mv.ellipse(0, 0, smoothedCircleSize, smoothedCircleSize);
            mv.popMatrix();

            mv.pushMatrix();
            mv.translate(100, -100, 0);
            mv.rotateY(angle);
            mv.rotateX(angle);
            mv.strokeWeight(5); 
            mv.ellipse(0, 0, smoothedCircleSize, smoothedCircleSize);
            mv.popMatrix();
        
        angle += 0.01f;
        }
    float angle = 0;
} 

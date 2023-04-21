package LAAD_Group._VisualFiles;

import LAAD_Group._Main.*;

public class C21390151 extends Visual
{
    public boolean isOn = false;
    MyVisual mv;
    public C21390151(MyVisual m){
        this.mv = m;
    }
    

    float smoothedHexSize = 0;

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

        float hexSize = 50 + (mv.getAmplitude() * 300);
        smoothedHexSize = lerp(smoothedHexSize, hexSize, 0.2f);
       
        mv.pushMatrix();
        mv.translate(-100, 100, 0);
        mv.rotateY(angle);
        mv.rotateX(angle);
        drawHexagon(smoothedHexSize);
        mv.popMatrix();

        mv.pushMatrix();
        mv.translate(100, 100, 0);
        mv.rotateY(angle);
        mv.rotateX(angle);
        drawHexagon(smoothedHexSize);
        mv.popMatrix();
    

        angle += 0.01f;
    }
    float angle = 0;

    public void drawHexagon(float size) {
        mv.beginShape();
        for (int i = 0; i < 6; i++) {
            float angle = TWO_PI / 6 * i;
            float x = sin(angle) * size;
            float y = cos(angle) * size;
            mv.vertex(x, y, 0);
        }
        mv.endShape(CLOSE);
    }
} 

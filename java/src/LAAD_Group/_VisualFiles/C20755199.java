package LAAD_Group._VisualFiles;

import java.util.ArrayList;

import LAAD_Group._Main.*;
import processing.core.PApplet;

public class C20755199 extends Visual {
    MyVisual mv;
    float amplitude = 0;
    float circleDiameter = 130;
    float segmentLength = 300;
    float maxSegmentLength = 350;
   

    public C20755199(MyVisual m) {
        this.mv = m;
      
    }

    public void draw() {
        // Get audio level
        float level = mv.getAmplitude();
        Electrical(level);
       
    }

    
    

    void Electrical(float amplitude) {
        mv.noFill(); // Don't fill the shape
        mv.strokeWeight(2); // Set the stroke weight to 2
        mv.stroke(0, 0, 255); // Set the stroke color to blue

        // Calculate the amplitude of the audio and use it to control the size of the
        // circle and the length of the electricity

        float newCircleDiameter = circleDiameter + amplitude * 50;
        float newSegmentLength = segmentLength + amplitude * 400;

        // Draw the circle
        float x = mv.width / 2; // Set the x-coordinate of the circle to the center of the canvas
        float y = mv.height / 2; // Set the y-coordinate of the circle to the center of the canvas
        mv.ellipse(x, y, newCircleDiameter, newCircleDiameter); // Draw the circle

        // Draw the electricity coming out of the edges of the circle
        float angleIncrement = TWO_PI / 40; // Set the angle increment between each line segment
        float angle = 0; // Initialize the angle
        float radius = newCircleDiameter / 2; // Calculate the radius of the circle

        while (angle < TWO_PI) {
            float x1 = x + radius * cos(angle);
            float y1 = y + radius * sin(angle);
            float x2 = x1 + newSegmentLength * cos(angle);
            float y2 = y1 + newSegmentLength * sin(angle);

            if (newSegmentLength > maxSegmentLength) {
                newSegmentLength = segmentLength;
            }

            // Calculate the zigzag effect of the line segment based on audio amplitude
            float zigzagAmount = amplitude * 10;
            float zigzagAngle = angle + PApplet.map(mv.random(-zigzagAmount, zigzagAmount), -1, 1, -PI / 4, PI / 4);
            float x3 = x1 + newSegmentLength * cos(zigzagAngle);
            float y3 = y1 + newSegmentLength * sin(zigzagAngle);

            // Draw the zigzag line segment with variable opacity
            float opacity = PApplet.map(PApplet.sin((float) (angle * 20 + millis() / 100.0)), -1, 1, 0, 255);
            mv.stroke(0, 0, 255, opacity);
            mv.line(x1, y1, x3, y3);

            angle += angleIncrement;
        }

        // Add a glowing effect to the circle
        float glowSize = (float) (newCircleDiameter * 6.3f); // Calculate the size of the glow
        float glowOpacity = PApplet.map(PApplet.sin((float) (millis() / 100.0)), -1, 1, 250, 350); // Calculate the
                                                                                                   // opacity of the
                                                                                                   // glow using a sine
                                                                                                   // wave
        mv.stroke(0, 0, 255, glowOpacity);
        mv.ellipse(x, y, glowSize, glowSize); // Draw the glowing circle
    }

}


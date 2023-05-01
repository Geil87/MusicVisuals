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
    ArrayList<Sphere> spheres = new ArrayList<Sphere>();
    float sphereSpeed = 5;

    public C20755199(MyVisual m) {
        this.mv = m;
    }

    public void draw() {
        float level = mv.getAmplitude();
        mv.fill(255);
        
        float textSize = (level+.2f) * 100;
        mv.textSize(textSize);
        mv.textAlign(CENTER); // Set text alignment to center
        mv.text("ANDREI", mv.width/2,50 );
        mv.fill(255);
        
        float textSize1 = (level+.2f) * 100;
        mv.textSize(textSize1);
        mv.textAlign(CENTER); // Set text alignment to center
        mv.text("C20755199", mv.width/2,mv.height-40 );
        // Get audio level
        
        Electrical(level);
        // Create new spheres randomly
        Spheres(level);
        // Draw stars
        Stars();

    }

    void Stars() {
        mv.strokeWeight(2);
        mv.stroke(255);
        for (int i = 0; i < 50; i++) {
            float x = mv.random(mv.width);
            float y = mv.random(mv.height);
            mv.point(x, y);
        }
    }

    void Spheres(float level) {
        if (mv.random(.5f) < level) {
            float x = mv.random(mv.width);
            float y = mv.random(mv.height);
            float size = mv.random(20, 50);
            spheres.add(new Sphere(x, y, size, sphereSpeed, mv));
        }

        // Update and draw the spheres
        float cx = mv.width / 2;
        float cy = mv.height / 2;
        for (Sphere sphere : spheres) {
            sphere.moveTowardsCircle(cx, cy);
            sphere.draw();
        }
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

            if (newSegmentLength > maxSegmentLength) {
                newSegmentLength = segmentLength;
            }

            // Calculate the zigzag effect of the line segment based on audio amplitude
            // Calculate the zigzag effect of the line segment based on audio amplitude and
            // distortion
            float zigzagAmount = amplitude * 10;
            float distortion = PApplet.map(mv.noise((float) angle * (float) 0.1, millis() / 10), 0, 1, -1, 1)
                    * amplitude * 10;
            float zigzagAngle = angle + PApplet.map(mv.random(-zigzagAmount, zigzagAmount), -1, 1, -PI / 4, PI / 4)
                    + distortion;
            float x3 = x1 + newSegmentLength * cos(zigzagAngle);
            float y3 = y1 + newSegmentLength * sin(zigzagAngle);

            // Draw the zigzag line segment with variable opacity
            float opacity = PApplet.map(PApplet.sin((float) (angle * 20 + millis() / 100.0)), -1, 1, 0, 255);
            mv.stroke(random(0, 255), random(0, 255), random(0, 255), opacity);
            mv.line(x1, y1, x3, y3);

            angle += angleIncrement;
        }

        // Add a glowing effect to the circle
        float glowSize = (float) (newCircleDiameter * 6.3f); // Calculate the size of the glow
        float glowOpacity = PApplet.map(PApplet.sin((float) (millis() / 100.0)), -1, 1, 250, 350);
        mv.strokeWeight(6);
        mv.stroke(0, 0, 255, glowOpacity + 50);
        mv.ellipse(x, y, glowSize, glowSize); // Draw the glowing circle
    }

}

class Sphere {
    float x, y;
    float size;
    float speed;
    MyVisual mv;
    C20755199 c = new C20755199(mv);

    Sphere(float x, float y, float size, float speed, MyVisual m) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.mv = m;
    }

    void moveTowardsCircle(float cx, float cy) {
        float dx = cx - x;
        float dy = cy - y;
        float distance = PApplet.sqrt(dx * dx + dy * dy);
        if (distance > 430) {
            x += dx / distance * speed;
            y += dy / distance * speed;
        } else {
            destroy();
        }
    }

    void draw() {
        mv.noStroke();
        mv.fill(255, 0, 0);
        mv.ellipse(x, y, size, size);
    }

    void destroy() {
        // Remove the sphere from the list of spheres
        this.size = 0;
        c.spheres.remove(this);
    }
}

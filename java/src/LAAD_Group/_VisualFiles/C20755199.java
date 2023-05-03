package LAAD_Group._VisualFiles;

import java.util.ArrayList;

import LAAD_Group._Main.*;
import processing.core.PApplet;

public class C20755199 extends Visual {
    // Declare a variable of type MyVisual
    MyVisual mv;

    // Declare and initialize some variables
    float amplitude = 0;
    float circleDiameter = 130;
    float segmentLength = 300;
    float maxSegmentLength = 350;
    float sphereSpeed = 5;

    // Declare an ArrayList of type Sphere
    ArrayList<Sphere> spheres = new ArrayList<Sphere>();

    // Constructor that initializes the MyVisual object
    public C20755199(MyVisual m) {
        this.mv = m;
    }

    // Main draw method that gets called repeatedly
    public void draw() {
        // Set the background color to black
        // mv.background(0);

        // Set the camera view
        mv.camera();

        // Get the current amplitude of the audio
        float level = mv.getAmplitude();

        // Set the fill color to white
        mv.fill(255);

        // Scale the text size based on the audio level and draw the "ANDREI" text
        float textSize = (level + .2f) * 100;
        mv.textSize(textSize);
        mv.textAlign(CENTER); // Set text alignment to center
        mv.text("ANDREI", mv.width / 2, 50);

        // Set the fill color to white
        mv.fill(255);

        // Scale the text size based on the audio level and draw the "C20755199" text
        float textSize1 = (level + .2f) * 100;
        mv.textSize(textSize1);
        mv.textAlign(CENTER); // Set text alignment to center
        mv.text("C20755199", mv.width / 2, mv.height - 40);

        // Call Electrical method to draw lines
        Electrical(level);

        // Call Spheres method to create and animate new spheres randomly
        Spheres(level);

        // Call Stars method to draw stars
        Stars();
    }

    // Define a function to draw stars
    void Stars() {
        // Set the stroke weight and color
        mv.strokeWeight(2);
        mv.stroke(255);
        // Draw 50 stars randomly on the canvas
        for (int i = 0; i < 50; i++) {
            float x = mv.random(mv.width);
            float y = mv.random(mv.height);
            mv.point(x, y);
        }
    }

    // Define a function to create and update spheres
    void Spheres(float level) {
        // Create new spheres randomly based on audio level
        if (mv.random(.5f) < level) {
            float x = mv.random(mv.width);
            float y = mv.random(mv.height);
            float size = mv.random(20, 50);
            spheres.add(new Sphere(x, y, 0, size, 10, mv));
        }

        // Update and draw the spheres
        float cx = mv.width / 2;
        float cy = mv.height / 2;
        for (Sphere sphere : spheres) {
            sphere.moveTowardsCircle(cx, cy, 0);
            sphere.draw();
        }
    }

    void Electrical(float amplitude) {
        mv.noFill(); // Don't fill the shape
        mv.strokeWeight(.2f); // Set the stroke weight to 2
        mv.stroke(250, 250, 255, 50); // Set the stroke color to blue

        // Calculate the amplitude of the audio and use it to control the size of the
        // circle and the length of the electricity
        float newCircleDiameter = circleDiameter + amplitude * 50;
        float newSegmentLength = segmentLength + amplitude * 400;

        // Draw the circle
        float x = mv.width / 2; // Set the x-coordinate of the circle to the center of the canvas
        float y = mv.height / 2; // Set the y-coordinate of the circle to the center of the canvas
        SphereShape(x, y, 80);

        // Draw the electricity coming out of the edges of the circle
        float angleIncrement = TWO_PI / 40; // Set the angle increment between each line segment
        float angle = 0; // Initialize the angle
        float radius = newCircleDiameter / 2; // Calculate the radius of the circle
        float startY = y + radius; // Set the start y-coordinate of the line
        // This loop creates a series of line segments that will form a circle
        while (angle < TWO_PI) {
            // Calculate the starting point of the line segment
            float x1 = x + radius * cos(angle);
            float y1 = y + radius * sin(angle);
            float z1 = 0;

            // Ensure that the length of the current segment does not exceed the maximum
            // length
            if (newSegmentLength > maxSegmentLength) {
                newSegmentLength = segmentLength;
            }

            // Calculate the amount of zigzag in the line segment based on audio amplitude
            // and distortion
            float zigzagAmount = amplitude * 10;
            float distortion = PApplet.map(mv.noise((float) (angle * .1f), millis() / 10), 0, 1, -1, 1) * amplitude
                    * 10;
            float zigzagAngle = angle + PApplet.map(mv.random(-zigzagAmount, zigzagAmount), -1, 1, -PI / 4, PI / 4)
                    + distortion;
            float x3 = x1 + newSegmentLength * cos(zigzagAngle);
            float y3 = y1 + newSegmentLength * sin(zigzagAngle);
            float z3 = 0;

            // Add some random depth to the line segment
            x1 += mv.random(-10, 10);
            y1 += mv.random(-10, 10);
            z1 += mv.random(-10, 10);
            x3 += mv.random(-distortion, distortion);
            y3 += mv.random(-distortion, distortion);
            z3 += mv.random(-distortion, distortion);

            // Draw the line segment with a heart scanner effect
            float lineOpacity = PApplet.map(PApplet.sin((float) (angle * 20 + millis() / 100.0)), -1, 1, 0, 255);
            mv.stroke(mv.random(0, 255), mv.random(0, 255), mv.random(0, 255), lineOpacity);
            mv.strokeWeight(1);
            mv.beginShape();
            mv.vertex(x1, y1, z1);

            // Create a zigzag pattern by adding offsets to the line segment
            for (float i = 0; i <= 1; i += 0.05) {
                float x2 = x1 + i * (x3 - x1);
                float y2 = y1 + i * (y3 - y1);
                float z2 = z1 + i * (z3 - z1);
                float offset = PApplet.map(PApplet.abs(2 * i - 1), 0, 1, 0, 30);
                mv.vertex(x2 + offset * cos(angle), y2 + offset * sin(angle), z2);
            }

            // Draw the end point of the line segment
            mv.vertex(x3, y3, z3);
            mv.endShape();

            // Increase the angle for the next line segment
            angle += angleIncrement;
        }

        // Add a glowing effect to the circle
        float glowSize = (float) (newCircleDiameter * 6.3f); // Calculate the size of the glow
        float glowOpacity = PApplet.map(PApplet.sin((float) (millis() / 100.0)), -1, 1, 0, 0);
        mv.strokeWeight(1);
        SphereShape(mv.width / 2, mv.height / 2, glowSize / 2);
        mv.stroke(0, 0, 255, glowOpacity + 50);
        mv.ellipse(x, y, glowSize, glowSize); // Draw the glowing circle

    }

    // Create a sphere shape
    void SphereShape(float x, float y, float size) {
        mv.pushMatrix();
        mv.translate(x, y, 0);
        mv.sphere(size);
        mv.popMatrix();
    }

}

// Define a Sphere class that represents a sphere object in 3D space
class Sphere {
    // Initialize instance variables
    float x, y, z; // position coordinates
    float size; // size of the sphere
    float speed; // movement speed
    MyVisual mv; // reference to the main visualization
    C20755199 c = new C20755199(mv); // instance of another class that manages spheres

    // Constructor to set the initial values of the instance variables
    Sphere(float x, float y, float z, float size, float speed, MyVisual m) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.size = size;
        this.speed = speed;
        this.mv = m;
    }

    // Move the sphere towards a given point (cx, cy, cz)
    void moveTowardsCircle(float cx, float cy, float cz) {
        // Calculate the distance between the current position and the target position
        float dx = cx - x;
        float dy = cy - y;
        float dz = cz - z;
        float distance = PApplet.sqrt(dx * dx + dy * dy + dz * dz);

        // If the distance is greater than a threshold, move the sphere towards the
        // target
        if (distance > 430) {
            x += dx / distance * speed;
            y += dy / distance * speed;
            z += dz / distance * speed;
        } else {
            // If the distance is less than or equal to the threshold, destroy the sphere
            destroy();
        }
    }

    // Draw the sphere using the P3D renderer
    void draw() {
        mv.noStroke();
        mv.fill(255, 0, 0);
        mv.pushMatrix();
        mv.translate(x, y, z);
        mv.sphere(size);
        mv.popMatrix();
    }

    // Destroy the sphere by removing it from the list of spheres
    void destroy() {
        this.size = 0;
        c.spheres.remove(this);
    }
}

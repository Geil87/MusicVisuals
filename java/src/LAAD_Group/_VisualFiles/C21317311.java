package LAAD_Group._VisualFiles;

import java.util.ArrayList;

import LAAD_Group._Main.*;
import processing.core.PConstants;

public class C21317311 extends Visual {
    MyVisual mv;
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    public C21317311(MyVisual m) {
        this.mv = m;
    }

    public void draw() {
        //mv.background(0);
        mv.camera();
        float level = mv.getAmplitude();

        // Draw and update existing shapes
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape s = shapes.get(i);
            s.update();
            s.display();
            if (s.dead()) {
                shapes.remove(i);
            }
        }

        // Create new shapes randomly
        if (mv.random(1) < level) {
            int color = mv.color(mv.random(255), mv.random(255), mv.random(255));
            shapes.add(new Shape(mv.random(mv.width), mv.random(mv.height), mv.random(10, 50), mv.random(-5, 5),
                    mv.random(-5, 5), color, mv));
        }

        // Draw trippy background effect
        for (int i = 0; i < 50; i++) {
            mv.noStroke();
            mv.fill(mv.random(255), mv.random(255), mv.random(255), 5);
            mv.rect(mv.random(mv.width), mv.random(mv.height), mv.random(200), mv.random(200));
        }

        // Draw stars
        Stars();
    }

    void Stars() {
        mv.noStroke();
        mv.pushMatrix();
        mv.translate(mv.width / 2, mv.height / 2);
        mv.rotate(mv.frameCount * 0.01f);

        // Create a gradient with varying shades of gray
        for (int i = 0; i < 100; i++) {
            float x = mv.random(mv.width) - mv.width / 2;
            float y = mv.random(mv.height) - mv.height / 2;
            float d = mv.dist(x, y, 0, 0);
            float gray = mv.map(d, 0, mv.width / 2, 0, 255);
            mv.fill(gray);

            // Draw circles with random sizes
            mv.ellipse(x, y, mv.random(5, 15), mv.random(5, 15));
        }

        mv.popMatrix();
    }

    class Shape {
        float x, y;
        float size;
        float angle;
        float vx, vy;
        int color;
        MyVisual mv;

        Shape(float x, float y, float size, float vx, float vy, int color, MyVisual m) {
            this.x = x;
            this.y = y;
            this.size = 2 * size;
            this.angle = 0;
            this.vx = 2 * vx;
            this.vy = 2 * vy;
            this.color = color;
            this.mv = m;
        }

        void update() {
            angle += 0.05f;
            x += vx;
            y += vy;
            if (x < 0 || x > mv.width) {
                vx = -vx;
            }
            if (y < 0 || y > mv.height) {
                vy = -vy;
            }
        }

        void display() {
            mv.pushMatrix();
            mv.translate(x, y);

            // Rotate the shape based on time
            float time = mv.millis() / 1000.0f;
            mv.rotateY(time * vx);// Rotate y
            mv.rotateX(time * vy);// Rotate x

            // Create a random 3D shape
            int shapeType = (int) mv.random(3);

            switch (shapeType) {
                case 0:
                    mv.fill(color, 128);
                    mv.beginShape();
                    mv.vertex(-size / 2, -size / 2, 0);
                    mv.vertex(size / 2, -size / 2, 0);
                    mv.vertex(size / 2, size / 2, 0);
                    mv.vertex(-size / 2, size / 2, 0);
                    mv.endShape(PConstants.CLOSE);
                    break;

                case 1:
                    mv.fill(color, 128);
                    mv.beginShape();
                    for (int i = 0; i < 360; i += 20) {
                        float x = mv.cos(mv.radians(i)) * size / 2;
                        float y = mv.sin(mv.radians(i)) * size / 2;
                        mv.vertex(x, y, 0);
                    }
                    mv.endShape(PConstants.CLOSE);
                    break;

                case 2:
                    mv.fill(color, 128);
                    mv.beginShape(PConstants.TRIANGLES);
                    mv.vertex(0, -size / 2, 0);
                    mv.vertex(size / 2, size / 2, 0);
                    mv.vertex(-size / 2, size / 2, 0);
                    mv.vertex(0, -size / 2, 0);
                    mv.vertex(0, size / 2, size / 2);
                    mv.vertex(-size / 2, size / 2, 0);
                    mv.vertex(0, size / 2, size / 2);
                    mv.vertex(size / 2, size / 2, 0);
                    mv.endShape(PConstants.CLOSE);
                    break;

                default:
                    break;
            }

            mv.popMatrix();
        }

        boolean dead() {
            return (x < -size || x > mv.width + size || y < -size || y > mv.height + size);
        }
    }
}

class Shape {

    float x, y;
    float size;
    float angle;
    float vx, vy;
    int color;
    MyVisual mv;

    Shape(float x, float y, float size, float vx, float vy, int color, MyVisual m) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.angle = 0;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
        this.mv = m;
    }

}

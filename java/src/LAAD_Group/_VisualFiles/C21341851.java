package LAAD_Group._VisualFiles;

import LAAD_Group._Main.*;

public class C21341851 extends Visual {
    public boolean isOn = false;
    MyVisual mv;

    public C21341851(MyVisual m) {
        this.mv = m;
    }

    float smoothedTriangleSize = 0;
    float angle = 0;

    // Overriding the draw() method from the Visual class
    @Override
    public void draw() {
        // Calculate and set up the background, lights, and stroke
        mv.calculateAverageAmplitude();
        // mv.background(0);
        mv.noFill();
        mv.lights();
        mv.stroke(map(mv.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        // Set up the camera
        mv.camera(0, 0, 1000, 0, 0, -1, 0, 1, 0);
        mv.translate(width / 2, height / 2, -250);

        // Calculate triangle size and apply smoothing

        float triangleSize = 50 + (mv.getSmoothedAmplitude() * 500);
        smoothedTriangleSize = lerp(smoothedTriangleSize, triangleSize, 0.2f);

        // Configure the grid of triangles
        int numTrianglesX = 4;
        int numTrianglesY = 4;
        float triangleSpacing = 100;

        // Iterate over the grid of triangles
        for (int i = 0; i < numTrianglesX; i++) {

            for (int j = 0; j < numTrianglesY; j++) {
                // Calculate hue based on position in the grid and frame count
                float hue = (map(i + j, 0, numTrianglesX + numTrianglesY, 0, 255) + frameCount * 5) % 255;

                mv.translate((i - numTrianglesX / 2) * triangleSpacing, (j - numTrianglesY / 2) * triangleSpacing);

                // Draw triangles in layers
                for (int layer = 0; layer < 3; layer++) {
                    float layerSpeedMultiplier = (layer + 1) * (0.01f + mv.getAmplitude() * 0.2f);
                    mv.rotateX(angle + TWO_PI / numTrianglesX * i * layerSpeedMultiplier);
                    mv.rotateY(angle + TWO_PI / numTrianglesY * j * layerSpeedMultiplier);

                    // Set properties and draw triangles based on layer
                    if (layer == 0) {
                        mv.fill(hue, 255, 255);
                        drawTriangle(smoothedTriangleSize);
                    } else if (layer == 1) {
                        mv.noFill();
                        mv.stroke(hue, 255, 255);
                        drawTriangle(smoothedTriangleSize * 1.2f);
                    } else {
                        mv.noFill();
                        mv.stroke(hue, 255, 255);
                        drawTriangle(smoothedTriangleSize * 1.5f);
                    }

                    // Draw additional smaller triangles
                    for (int k = 0; k < 3; k++) {
                        // mv.pushMatrix();
                        mv.rotateZ(angle + TWO_PI / 3 * k * layerSpeedMultiplier);
                        mv.translate(smoothedTriangleSize * (1 - layer * 0.2f) * 1.5f, 0);
                        // mv.popMatrix();
                        // Set properties and draw smaller triangles based on layer
                        if (layer == 0) {
                            mv.fill((hue + 128) % 255, 255, 255);
                            drawTriangle(smoothedTriangleSize * 0.3f);
                        } else if (layer == 1) {
                            mv.noFill();
                            mv.stroke((hue + 128) % 255, 255, 255);
                            drawTriangle(smoothedTriangleSize * 0.4f);
                        } else {
                            mv.noFill();
                            mv.stroke((hue + 128) % 255, 255, 255);
                            drawTriangle(smoothedTriangleSize * 0.2f);
                        }
                    }
                }
            }
        }
    }

    private void drawTriangle(float size) {
        // Calculate half the size of the triangle
        float halfSize = size / 2.0f;

        // Begin a new shape using Processing's beginShape() method
        mv.beginShape();

        // Define the triangle vertices using Processing's vertex() method
        mv.vertex(-halfSize, -halfSize, 0);
        mv.vertex(halfSize, -halfSize, 0);
        mv.vertex(0, halfSize, 0);

        // Close the shape and render the triangle using Processing's endShape() method
        mv.endShape(CLOSE);
    }
}
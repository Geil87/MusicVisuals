package LAAD_Group._VisualFiles;

import LAAD_Group._Main.*;

public class C21390151 extends Visual {
    public boolean isOn = false;
    MyVisual mv;

    public C21390151(MyVisual m) {
        this.mv = m;
    }

    float smoothedCubeSize = 0;
    float angle = 0;

    public void draw() {
        mv.calculateAverageAmplitude();
        //mv.background(0);
        //mv.noFill();
        mv.lights();
        mv.stroke(map(mv.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        mv.camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        
        
        mv.translate(width / 2, height / 2, -250);

        float cubeSize = 50 + (mv.getSmoothedAmplitude() * 500);
        smoothedCubeSize = lerp(smoothedCubeSize, cubeSize, 0.2f);

        int numCubesX = 4;
        int numCubesY = 4;
        float cubeSpacing = 100;

        for (int i = 0; i < numCubesX; i++) {
            for (int j = 0; j < numCubesY; j++) {
                float hue = (map(i + j, 0, numCubesX + numCubesY, 0, 255) + frameCount * 5) % 255;
                mv.pushMatrix();
                mv.translate((i - numCubesX / 2) * cubeSpacing, (j - numCubesY / 2) * cubeSpacing);

                for (int layer = 0; layer < 3; layer++) {
                    float layerSpeedMultiplier = (layer + 1) * (0.01f + mv.getAmplitude() * 0.2f);
                    mv.rotateX(angle + TWO_PI / numCubesX * i * layerSpeedMultiplier);
                    mv.rotateY(angle + TWO_PI / numCubesY * j * layerSpeedMultiplier);
                    mv.fill(hue, 255, 255);
                    mv.box(smoothedCubeSize * (1 - layer * 0.2f));

                    for (int k = 0; k < 4; k++) {
                        mv.pushMatrix();
                        mv.rotateZ(angle + TWO_PI / 4 * k * layerSpeedMultiplier);
                        mv.translate(smoothedCubeSize * (1 - layer * 0.2f) * 1.5f, 0);
                        mv.fill((hue + 128) % 255, 255, 255);
                        mv.box(smoothedCubeSize * 0.3f * (1 - layer * 0.2f));
                        mv.popMatrix();
                    }
                }

                mv.popMatrix();
            }
        }

        angle += 0.01f + mv.getAmplitude() * 0.1f;
        
    }

}

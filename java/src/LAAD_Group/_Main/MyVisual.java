package LAAD_Group._Main; // Declare package

import LAAD_Group._VisualFiles.*; // Import classes
import processing.core.PImage; // Import classes

public class MyVisual extends Visual { // Declare class MyVisual that extends Visual class
    C20755199 andrei; // Declare C20755199 variable andrei
    C21317311 ledion; // Declare C21317311 variable ledion
    C21341851 albert; // Declare C21341851 variable albert
    C21390151 dan; // Declare C21390151 variable dan

    PImage andreiImg; // Declare PImage variable andreiImg
    PImage danImg; // Declare PImage variable danImg
    PImage albertImg; // Declare PImage variable albertImg
    PImage ledionImg; // Declare PImage variable ledionImg

    public int currentVisual = 0; // Declare integer variable currentVisual and assign it the value 0

    boolean isSound; // Declare boolean variable isSound

    public void settings() { // Define settings method
        size(1024, 500, P3D); // Set window size
                              // Use this to make fullscreen
                              // fullScreen();
                              // Use this to make fullscreen and use P3D for 3D graphics
                              // fullScreen(P3D, SPAN);
    }

    public void setup() { // Define setup method
        andreiImg = loadImage("Andrei.png"); // Load image into andreiImg variable
        danImg = loadImage("Dan.png"); // Load image into danImg variable
        albertImg = loadImage("Albert.png"); // Load image into albertImg variable
        ledionImg = loadImage("Ledion.png"); // Load image into ledionImg variable

        andrei = new C20755199(this); // Create new C20755199 object and assign it to andrei variable
        dan = new C21390151(this); // Create new C21390151 object and assign it to dan variable
        albert = new C21341851(this); // Create new C21341851 object and assign it to albert variable
        ledion = new C21317311(this); // Create new C21317311 object and assign it to ledion variable

        startMinim(); // Start Minim

        // Call loadAudio to load an audio file to process
        loadAudio("DropIt.mp3"); // Load audio file

        getAudioPlayer().cue(0); // Cue audio player to start at beginning of audio file
        getAudioPlayer().play(); // Start playing audio file

        // Call this instead to read audio from the microphone
        // startListening();
    }

    // This method is called automatically by Processing whenever a key is pressed.
    public void keyPressed() {

        // If the spacebar is pressed:
        if (key == ' ') {
            // Here, we would normally play audio from the beginning.
            // However, this functionality is currently commented out.
            // getAudioPlayer().cue(0);
            // getAudioPlayer().play();

        }
        // If the '1' key is pressed:
        if (key == '1') {
            // This function sets up the audio player and loads the audio file for the first
            // visualization.
            SetAudio();
            // Set the current visualization to the first one.
            currentVisual = 1;
        }
        // If the '2' key is pressed:
        if (key == '2') {
            // This function sets up the audio player and loads the audio file for the
            // second visualization.
            SetAudio();
            // Set the current visualization to the second one.
            currentVisual = 2;
        }
        // If the '3' key is pressed:
        if (key == '3') {
            // This function sets up the audio player and loads the audio file for the third
            // visualization.
            SetAudio();
            // Set the current visualization to the third one.
            currentVisual = 3;
        }
        // If the '4' key is pressed:
        if (key == '4') {
            // This function sets up the audio player and loads the audio file for the
            // fourth visualization.
            SetAudio();
            // Set the current visualization to the fourth one.
            currentVisual = 4;
        }
        // If the '5' key is pressed:
        if (key == '5') {
            // This function sets up the audio player and loads the audio file for the fifth
            // visualization.
            SetAudio();
            // Set the current visualization to the fifth one.
            currentVisual = 5;
        }
    }

    public void draw() {

        // Set the background color to black
        background(0);

        try {
            // Call this if you want to use FFT data
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }

        // Call this is you want to use frequency bands
        calculateFrequencyBands();

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();

        // If the current visual is set to the default value of 0
        if (currentVisual == 0) {

            // Get the amplitude level for each person
            float levelAndrei = getAmplitude();
            float levelDan = getAmplitude();
            float levelAlbert = getAmplitude();
            float levelLedion = getAmplitude();

            // Draw the images for each person, with size based on their amplitude level
            image(andreiImg, width / 2f, 350, 200 + levelAndrei * 200, 400);
            image(danImg, width / 1.6f, 250, 300 + levelDan * 300, 400);
            image(albertImg, width / 2.5f, 350, 150 + levelAlbert * 150, 400);
            image(ledionImg, width / 4f, 320, 200 + levelLedion * 250, 300);

            // Add text labels for each person's name
            textSize(80);
            text("Ledion", width / 4.2f, height / 1.6f, 1000, 1000);
            text("Albert", width / 2.7f, height / 1.4f, 1000, 1000);
            text("|Andrei", width / 2f, height / 1.4f, 1000, 1000);
            text("Dan", width / 1.5f, height / 1.6f, 1000, 1000);
            text("LAAD", width / 2.3f, height / 7f, 1000, 1000);

            // Add a key legend at the bottom of the screen
            textSize(30);
            text("1:Play Andrei   2:Play Dan  3:Play Albert   4:Play Ledion   5:Play All", width / 4f, height / 1.1f,
                    2000, 1000);

        }

        // If the current visual is set to a specific person or to "Play All"
        switch (currentVisual) {
            case 1:
                andrei.draw();
                break;
            case 2:
                dan.draw();
                break;
            case 3:
                albert.draw();
                break;
            case 4:
                ledion.draw();
                break;
            case 5:
                andrei.draw();
                dan.draw();
                ledion.draw();
                albert.draw();
                break;
            default:
                break;
        }

    }

    /**
     * Sets the audio file to be played if there isn't any audio playing already.
     */
    void SetAudio() {
        // If audio is not playing yet
        if (!isSound) {
            // Set the flag to true to indicate audio is now playing
            isSound = true;
            // Stop the audio player if it's currently playing
            getAudioPlayer().close();
            // Load the audio file "theSong.mp3" to the audio player
            loadAudio("theSong.mp3");
            // Set the audio player to start playing from the beginning of the audio file
            getAudioPlayer().cue(0);
            // Start playing the audio file
            getAudioPlayer().play();
        }
    }

}

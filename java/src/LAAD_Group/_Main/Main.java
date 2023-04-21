package LAAD_Group._Main;


import processing.core.PApplet;

public class Main
{	

	
	public void startUI()
	{
		String[] a = {"MAIN"};
    	PApplet.runSketch(a, new MyVisual());
    
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}
package ca.etsmtl.log430.lab1;

import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.ArrayList;
import java.util.Collections;


public class AlphabeticSortFilter extends Thread {

	// Declarations

	boolean Done;

	String Keyword;
	PipedReader InputPipe = new PipedReader();
	PipedWriter OutputPipe1 = new PipedWriter();

	ArrayList<String> lineList = new ArrayList<String>();
	
	public AlphabeticSortFilter(PipedWriter InputPipe,
			PipedWriter OutputPipe1) {


		try {

			// Connect InputPipe to Main

			this.InputPipe.connect(InputPipe);
			System.out.println("KeywordFilter " + Keyword
					+ ":: connected to upstream filter.");

			// Connect OutputPipe to Merge

			this.OutputPipe1 = OutputPipe1;
			
			System.out.println("AlphabeticSortFilter "
					+ ":: connected to downstream filter.");

		} catch (Exception Error) {

			System.out.println("AlphabeticSortFilter "
					+ ":: Error connecting to other filters.");

		} // try/catch

	} // Constructor

	// This is the method that is called when the thread is started in
	// Main
	public void run() {

		// Declarations

		char[] CharacterValue = new char[1];
		// char array is required to turn char into a string
		String LineOfText = "";
		// string is required to look for the keyword
		int IntegerCharacter; // the integer value read from the pipe

		try {

			Done = false;

			while (!Done) {

				IntegerCharacter = InputPipe.read();
				CharacterValue[0] = (char) IntegerCharacter;

				if (IntegerCharacter == -1) { // pipe is closed
					
					Collections.sort(lineList);
					for(int i = 0; i < lineList.size();i++){
						String listEntry = lineList.get(i);
						
						CharacterValue = new char[]{'\n'};
						listEntry += new String(CharacterValue);
						
						System.out.println("AlphabeticSortFilter "
								+ ":: sending: "
								+ LineOfText + " to output pipe 1.");
						
						OutputPipe1.write(listEntry, 0, listEntry.length());
						OutputPipe1.flush();
				
					}
					
					Done = true;

				} else {

					if (IntegerCharacter == '\n') { // end of line

						System.out.println("AlphabeticSortFilter "
								+ ":: received: " + LineOfText + ".");
						
						lineList.add(LineOfText);
						
						
						
						LineOfText += new String(CharacterValue);
						
				
						LineOfText = "";
						
						
						
					} else {

						LineOfText += new String(CharacterValue);

					} // if //

				} // if

			} // while

		} catch (Exception Error) {

			System.out.println("AlphabeticSortFilter::"
					+ " Interrupted.");

		} // try/catch

		try {

			InputPipe.close();
			System.out.println("AlphabeticSortFilter "
					+ ":: input pipe closed.");

			OutputPipe1.close();
			System.out.println("AlphabeticSortFilter "
					+ ":: output pipe closed.");
			
			
		} catch (Exception Error) {

			System.out.println("AlphabeticSortFilter" 
					+ ":: Error closing pipes.");

		} // try/catch

	} // run

} // class
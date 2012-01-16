package ca.etsmtl.log430.lab1;

/**************************************************************************************
 ** Class name: LanguageFilter
 ** Author: R. Champagne, Ecole de technologie superieure
 ** Date: 2011-Jan-12
 ** Version 1.0
 ** Last modification: R. Champagne, 2012-Jan-13
 **
 ***************************************************************************************
 ** Purpose: Assignment 1 for LOG430, Architecture logicielle. This assignment is 
 ** designed to illustrate a pipe and filter architecture.  For the instructions, refer
 ** to the assignment write-up.
 **
 ** Abstract: This class is intended to be a filter that will send the information on
 **           an output pipe specific to each language. In other terms, this filter splits
 **           the data by language. 
 ** 
 ** Pseudo Code:
 **
 ** 	connect to input pipe
 ** 	connect to output pipe
 **
 **	while not end of line
 **    if " FRA " appears on line of text
 **        write line of text to FRA output pipe
 **        flush pipe
 **    else if " EN " appears on line of text
 **        write line of text to EN output pipe
 **        flush pipe
 **    end if
 **	end while
 **
 **	close pipes
 **
 ** Running the program
 **
 ** 	See Main.java
 **
 ** Modification Log
 **************************************************************************************
 **
 **************************************************************************************/

import java.io.*;

public class LanguageFilter extends Thread {

	// Declarations

	boolean Done;

	PipedReader InputPipe = new PipedReader();
	PipedWriter OutputPipe1 = new PipedWriter();
	PipedWriter OutputPipe2 = new PipedWriter();

	public LanguageFilter(PipedWriter InputPipe, PipedWriter OutputPipe1, PipedWriter OutputPipe2) {

		try {

			// Connect InputPipe

			this.InputPipe.connect(InputPipe);
			System.out.println("LanguageFilter:: connected to upstream filter.");

			// Connect OutputPipes

			this.OutputPipe1 = OutputPipe1;
			this.OutputPipe2 = OutputPipe2;
			System.out.println("LanguageFilter:: connected to downstream filters.");

		} catch (Exception Error) {

			System.out.println("LanguageFilter:: Error connecting to other filters.");

		} // try/catch

	} // Constructor

	// This is the method that is called when the thread is started in
	// Main

	public void run() {

		// Declarations

		char[] CharacterValue = new char[1];
		// char array is required to turn char into a string
		String LineOfText = "";
		// string is required to look for the language code
		int IntegerCharacter; // the integer value read from the pipe

		try {

			Done = false;

			while (!Done) {

				IntegerCharacter = InputPipe.read();
				CharacterValue[0] = (char) IntegerCharacter;

				if (IntegerCharacter == -1) { // pipe is closed

					Done = true;

				} else {

					if (IntegerCharacter == '\n') { // end of line

						System.out.println("LanguageFilter:: received: " + LineOfText + ".");

						if (LineOfText.indexOf(" FRA ") != -1) {

							System.out.println("LanguageFilter:: sending: "
									+ LineOfText + " to output pipe 1 (FRA).");
							LineOfText += new String(CharacterValue);
							OutputPipe1
									.write(LineOfText, 0, LineOfText.length());
							OutputPipe1.flush();
						} else if(LineOfText.indexOf(" EN ") != -1) {

							System.out.println("LanguageFilter:: sending: "
									+ LineOfText + " to output pipe 2 (EN).");
							LineOfText += new String(CharacterValue);
							OutputPipe2
									.write(LineOfText, 0, LineOfText.length());
							OutputPipe2.flush();
 						} // if

						LineOfText = "";

					} else {

						LineOfText += new String(CharacterValue);

					} // if //

				} // if

			} // while

		} catch (Exception Error) {

			System.out.println("LanguageFilter:: Interrupted.");

		} // try/catch

		try {

			InputPipe.close();
			System.out.println("LanguageFilter:: input pipe closed.");

			OutputPipe1.close();
			OutputPipe2.close();
			System.out.println("LanguageFilter:: output pipes closed.");

		} catch (Exception Error) {

			System.out.println("LanguageFilter:: Error closing pipes.");

		} // try/catch

	} // run

} // class
package ca.etsmtl.log430.lab1;

import java.io.PipedReader;
import java.io.PipedWriter;

/**************************************************************************************
 ** Class name: KeywordFilter
 ** Original author: A.J. Lattanze, CMU
 ** Date: 12/3/99
 ** Version 1.0
 **
 ** Adapted by R. Champagne, Ecole de technologie superieure
 ** 2002-May-08, 2012-Jan-13
 **
 ***************************************************************************************
 ** Purpose: Assignment 1 for LOG430, Architectures logicielle. This
 ** assignment is designed to illustrate a pipe and filter architecture.  For the 
 ** instructions, refer to the assignment write-up.
 **
 ** Abstract: This class is intended to be a filter that will key on a particular keyword
 **	     provided at instantiation.  Note that the stream has to be buffered so that
 **      it can be checked to see if the specified keyword appears on the stream.
 **      If this string appears in the stream from Main, to the output stream.
 ** 
 ** Pseudo Code:
 **0
 ** 	connect to input pipe
 ** 	connect to output pipe
 **
 **	while not end of line
 **
 **		read input pipe
 **
 **		if specified keyword appears on line of text
 **			write line of text to output pipe
 **			flush pipe
 **		end if
 **
 **	end while
 **	close pipes
 **
 ** Running the program
 **
 ** 	See Main.java
 **
 ** Modification Log
 *
 *		01/30/2012 - With parameter "keywordWanted", one can use the filter to either
 *					 find lines WITH or WITHOUT the specified keyword.
 **************************************************************************************
 **
 **************************************************************************************/


public class KeywordFilter extends Thread {

	// Declarations

	boolean Done;

	String Keyword;
	boolean keywordWanted;
	PipedReader InputPipe = new PipedReader();
	PipedWriter OutputPipe = new PipedWriter();

	public KeywordFilter(String Keyword, PipedWriter InputPipe,
			PipedWriter OutputPipe, boolean keywordWanted) {

		this.Keyword = Keyword;
		this.keywordWanted = keywordWanted;

		try {

			// Connect InputPipe to Main

			this.InputPipe.connect(InputPipe);
			System.out.println("KeywordFilter " + Keyword
					+ ":: connected to upstream filter.");

			// Connect OutputPipe to Merge

			this.OutputPipe = OutputPipe;
			System.out.println("KeywordFilter " + Keyword
					+ ":: connected to downstream filter.");

		} catch (Exception Error) {

			System.out.println("KeywordFilter " + Keyword
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

					Done = true;

				} else {

					if (IntegerCharacter == '\n') { // end of line

						System.out.println("KeywordFilter " + Keyword
								+ ":: received: " + LineOfText + ".");

						// Mot voulu et trouvé
						if (keywordWanted && LineOfText.indexOf(Keyword) != -1) {

							System.out.println("KeywordFilter "
									+ Keyword + ":: sending: "
									+ LineOfText + " to output pipe.");
							LineOfText += new String(CharacterValue);
							OutputPipe
									.write(LineOfText, 0, LineOfText.length());
							OutputPipe.flush();

						} // if
						// Mot non voulu et non trouvé
						else if(!keywordWanted && LineOfText.indexOf(Keyword) == -1) {
							
							System.out.println("KeywordFilter "
									+ Keyword + ":: sending: "
									+ LineOfText + " to output pipe.");
							LineOfText += new String(CharacterValue);
							OutputPipe
									.write(LineOfText, 0, LineOfText.length());
							OutputPipe.flush();
							
						} // else if

						LineOfText = "";

					} else {
						LineOfText += new String(CharacterValue);
					} // if //
				} // if
			} // while
		} catch (Exception Error) {
			System.out.println("KeywordFilter::" + Keyword
					+ " Interrupted.");
		} // try/catch

		try {

			InputPipe.close();
			System.out.println("KeywordFilter " + Keyword
					+ ":: input pipe closed.");

			OutputPipe.close();
			System.out.println("KeywordFilter " + Keyword
					+ ":: output pipe closed.");

		} catch (Exception Error) {

			System.out.println("KeywordFilter " + Keyword
					+ ":: Error closing pipes.");

		} // try/catch

	} // run

} // class
package ca.etsmtl.log430.lab1;

import java.io.PipedReader;
import java.io.PipedWriter;

/**************************************************************************************
 ** Class name: KeywordFilterOR
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
 **
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
 *		01/30/2012 - This version of KeywordFilter receives multiple keywords in an array
 *					 and, with the help of the "inclusive" boolean parameter, the class
 *					 will output lines where the specified keywords are found following
 *					 either an inclusive OR or exclusive OR pattern
 **************************************************************************************
 **
 **************************************************************************************/


public class KeywordFilterOR extends Thread {

	// Declarations

	boolean Done;

	String[] Keywords;
	boolean inclusive;
	PipedReader InputPipe = new PipedReader();
	PipedWriter OutputPipe = new PipedWriter();

	public KeywordFilterOR(String[] Keywords, PipedWriter InputPipe,
			PipedWriter OutputPipe, boolean inclusive) {

		this.Keywords = Keywords;
		this.inclusive = inclusive;

		try {

			// Connect InputPipe to Main

			this.InputPipe.connect(InputPipe);
			System.out.print("KeywordFilterOR ");
			for(int i = 0; i < Keywords.length; i++) {
				System.out.print(Keywords[i] + "-");
			}
			System.out.print(":: connected to upstream filter.\n");

			// Connect OutputPipe to Merge

			this.OutputPipe = OutputPipe;
			System.out.print("KeywordFilterOR ");
			for(int i = 0; i < Keywords.length; i++) {
				System.out.print(Keywords[i] + "-");
			}
			System.out.print(":: connected to downstream filter.\n");

		} catch (Exception Error) {

			System.out.print("KeywordFilterOR ");
			for(int i = 0; i < Keywords.length; i++) {
				System.out.print(Keywords[i] + "-");
			}
			System.out.print(":: Error connecting to other filters.\n");

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

						System.out.print("KeywordFilterOR ");
						for(int i = 0; i < Keywords.length; i++) {
							System.out.print(Keywords[i] + "-");
						}
						System.out.print(":: received: " + LineOfText + ".\n");

						// Inclusive OR
						if(inclusive) {
							for(int i = 0; i < Keywords.length; i++) {
								if (LineOfText.indexOf(Keywords[i]) != -1) {

									System.out.print("KeywordFilterOR ");
									for(int j = 0; j < Keywords.length; j++) {
										System.out.print(Keywords[j] + "-");
									}
									System.out.print(":: sending: " + LineOfText + " to output pipe.\n");
									
									LineOfText += new String(CharacterValue);
									OutputPipe
											.write(LineOfText, 0, LineOfText.length());
									OutputPipe.flush();
									break; // If one is found, no need to check the rest of the keywords
								}
							}
						}
						// Exclusive OR not yet implemented - not needed for assignment
						LineOfText = "";
					} else {
						LineOfText += new String(CharacterValue);
					} // if //
				} // if
			} // while
		} catch (Exception Error) {
			System.out.print("KeywordFilterOR ");
			for(int i = 0; i < Keywords.length; i++) {
				System.out.print(Keywords[i] + "-");
			}
			System.out.print(":: Interrupted.\n");
		} // try/catch

		try {

			InputPipe.close();
			System.out.print("KeywordFilterOR ");
			for(int i = 0; i < Keywords.length; i++) {
				System.out.print(Keywords[i] + "-");
			}
			System.out.print(":: input pipe closed.\n");

			OutputPipe.close();
			System.out.print("KeywordFilterOR ");
			for(int i = 0; i < Keywords.length; i++) {
				System.out.print(Keywords[i] + "-");
			}
			System.out.print(":: output pipe closed.\n");

		} catch (Exception Error) {

			System.out.print("KeywordFilterOR ");
			for(int i = 0; i < Keywords.length; i++) {
				System.out.print(Keywords[i] + "-");
			}
			System.out.print(":: Error closing pipes.\n");

		} // try/catch

	} // run

} // class
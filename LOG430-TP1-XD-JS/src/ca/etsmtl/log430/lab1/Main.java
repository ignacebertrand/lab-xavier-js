package ca.etsmtl.log430.lab1;

import java.io.PipedWriter;

/**************************************************************************************
 ** Class name: Main Original author: A.J. Lattanze, CMU Date: 12/3/99 Version
 * 1.2
 ** 
 ** Adapted by R. Champagne, Ecole de technologie superieure 2002-May-08,
 * 2011-Jan-12, 2012-Jan-11.
 ** 
 *************************************************************************************** 
 ** Purpose: Assignment 1 for LOG430, Architecture logicielle. This assignment is
 * designed to illustrate a pipe and filter architecture. For the instructions,
 * refer to the assignment write-up.
 ** 
 ** Abstract: This class contains the main method for assignment 1. The
 * assignment 1 program consists of these files:
 ** 
 ** 1) Main: instantiates all filters and pipes, starts all filters. 2)
 * FileReaderFilter: reads input file and sends each line to its output pipe. 3)
 * LanguageFilter: separates the input stream in two languages (FRA, EN) and
 * writes lines to the appropriate output pipe. 4) KeywordFilter: determines if
 * an entry contains a particular keyword specified at instantiation. If so,
 * sends the whole line to its output pipe. 5) MergeFilter: accepts inputs from
 * 2 input pipes and writes them to its output pipe. 6) FileWriterFilter: sends
 * its input stream to a text file.
 ** 
 ** Pseudo Code:
 ** 
 ** instantiate all filters and pipes start FileReaderFilter start LanguageFilter
 * start KeywordFilter for FRA start KeywordFilter for EN start MergeFilter
 * start FileWriterFilter
 ** 
 ** Running the program
 ** 
 ** java Main IputFile OutputFile > DebugFile
 ** 
 ** Main - Program name InputFile - Text input file (see comments below)
 * OutputFile - Text output file with students DebugFile - Optional file to
 * direct debug statements
 ** 
 ** Modification Log
 ************************************************************************************** 
 ** 
 **************************************************************************************/

public class Main {

	public static void main(String argv[]) {
		// Lets make sure that input and output files are provided on the
		// command line

		if (argv.length != 2) {

			System.out
					.println("\n\nNombre incorrect de parametres d'entree. Utilisation:");
			System.out
					.println("\njava Main <fichier d'entree> <fichier de sortie>");

		} else {

			String inputFileName = argv[0];
//			String outputFileName1 = argv[1];
			String outputFileName1 = "dataout.txt";
			String outputFileName2 = "dataout2.txt";

			// ****SYSTEME A****

			// These are the declarations for the pipes.
			PipedWriter pipe01 = new PipedWriter();// Texte original
			PipedWriter pipe02 = new PipedWriter();// Lignes en FRA
			PipedWriter pipe03 = new PipedWriter();// Lignes en EN
			PipedWriter pipe04 = new PipedWriter();// Lignes en FRA qui contiennent le keyword
			PipedWriter pipe05 = new PipedWriter();// Lignes en EN qui contiennent le keyword
			PipedWriter pipe06 = new PipedWriter();// Lignes en FRA qui contiennent pas le keyword
			PipedWriter pipe07 = new PipedWriter();// Lignes en EN qui contiennent pas le keyword
			PipedWriter pipe08 = new PipedWriter();// Merge des resultats qui contiennent les keywords
			PipedWriter pipe09 = new PipedWriter();// Merge des resultats qui contiennent pas les keywords

			// Instantiate the Program Filter Thread
			Thread FileReaderFilter1 = new FileReaderFilter(inputFileName,
					pipe01);

			// Instantiate the LanguageFilter Thread
			Thread LanguageFilter1 = new LanguageFilter(pipe01, pipe02, pipe03);

			// Instantiate the Course Filter Threads
			Thread KeywordFilter1 = new KeywordFilterA("conception", pipe02,
					pipe04, pipe06);
			Thread KeywordFilter2 = new KeywordFilterA("architecture", pipe03,
					pipe05, pipe07);

			// Instantiate the Merge Filter Thread
			Thread MergeFilter1 = new MergeFilter(pipe04, pipe05, pipe08);

			// Instantiate the Merge Filter Thread
			Thread MergeFilter2 = new MergeFilter(pipe06, pipe07, pipe09);
						
			// Instantiate the FileWriter Filter Thread
			Thread FileWriterFilter1 = new FileWriterFilter(outputFileName1,
					pipe08);
			
			// Instantiate the FileWriter Filter Thread
			Thread FileWriterFilter2 = new FileWriterFilter(outputFileName2,
								pipe09);

			// Start the threads (these are the filters)
			FileReaderFilter1.start();
			LanguageFilter1.start();
			KeywordFilter1.start();
			KeywordFilter2.start();
			MergeFilter1.start();
			MergeFilter2.start();
			FileWriterFilter1.start();
			FileWriterFilter2.start();

			// ***************SYSTEME B

		} // if

	} // main

} // Main
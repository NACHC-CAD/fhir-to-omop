package org.nachc.tools.fhirtoomop.main;

import java.io.File;

import com.nach.core.util.file.FileUtil;

public class FhirToOmopMain {

	public static void main(String[] args) {
		System.out.println("Welcome to fhir-to-omop");
		if (args == null || args.length < 2) {
			zeroParam();
		} else {
			String fileName = args[1];
			System.out.println("Using config file from here:");
			System.out.println(fileName);
			String name = args[0];
			switch (name.toLowerCase()) {
			case "say-hello":
				sayHello();
				break;
			case "params-example": 
				paramsExample();
				break;
			case "my-params": 
				myParams(fileName);
				break;
			case "instant-omop": 
				break;
			case "run-tests": 
				paramsExample();
				break;
			default:
				wrongParam();
				break;
			}
		}
	}

	private static void sayHello() {
		System.out.println("Hello World!");
	}

	private static void paramsExample() {	
		System.out.println("\n\nThe following is an example of a params file");
		System.out.println("Cut and paste the section between the dotted lines and put it in a file called app.params");
		System.out.println("Put the file in the directory you are in now.");
		System.out.println("Replace <my_api_key> with your api key.\n\n");
		System.out.println("# -----------------8<-----------------8<-----------------8<--------------------\n\n");
		String msg = FileUtil.getAsString("/main/example-params/app.properties");
		System.out.println(msg);
		System.out.println("# -----------------8<-----------------8<-----------------8<--------------------");
	}
	
	private static void myParams(String fileName) {
		File file = new File(fileName, "app.properties");
		String msg = FileUtil.getAsString(file);
		System.out.println("The contents of you app.properties file is shown between the dotted lines below.\n\n");
		System.out.println("-----------------8<-----------------8<-----------------8<--------------------\n\n");
		System.out.println(msg);
		System.out.println("-----------------8<-----------------8<-----------------8<--------------------");
	}

	private static void instantOmop() {
		System.out.println("Not implemented yet");
	}
	
	private static void runTests() {
		System.out.println("Not implemented yet");
	}
	
	// ---
	//
	// error cases
	//
	// ---
	
	private static void zeroParam() {
		String msg = FileUtil.getAsString("/main/msg/zero-args-error.txt");
		System.out.println(msg);
	}

	private static void wrongParam() {
		String msg = FileUtil.getAsString("/main/msg/wrong-param-error.txt");
		System.out.println(msg);
	}

}

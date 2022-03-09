package org.nachc.tools.fhirtoomop.main;

import com.nach.core.util.file.FileUtil;

public class FhirToOmopMain {

	public static void main(String[] args) {
		System.out.println("Welcome to fhir-to-omop");
		if (args == null || args.length == 0) {
			printZeroArgsMsg();
		} else {

		}
	}

	private static void printZeroArgsMsg() {
		String msg = FileUtil.getAsString("/main/msg/zero-args-error.txt");
		System.out.println(msg);
	}

}

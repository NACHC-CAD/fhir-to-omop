package org.nachc.tools.fhirtoomop.tools.test;

import java.util.List;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListTestPatients {

	private static final String PATH = "/synthea/patients/synthea-test-patients/test-set-01";
	
	public static void exec() {
		List<String> paths = FileUtil.listResources(PATH, ListTestPatients.class);
		System.out.println("Got " + paths.size() + " test files.");
		System.out.println("------------------------------");
		for(String path : paths) {
			System.out.println(path);
		}
		System.out.println("------------------------------");
		System.out.println("Got " + paths.size() + " test files.");
	}
	
}

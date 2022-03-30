package org.nachc.tools.fhirtoomop.omop.write.listofpatients;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.write.listofpatients.allatonce.WriteListOfFhirPatientsToOmopAllAtOnce;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class should be used to write large lists of FHIR patients to an omop
 * database.
 *
 */
@Slf4j
public class WriteListOfFhirPatientsToOmop {

	public static void exec(List<String> fileList, List<Connection> connList, int maxThreads) {
		ArrayList<String> filesToWrite = new ArrayList<String>();
		int cnt = 0;
		for (int i = 0; i < fileList.size(); i++) {
			filesToWrite.add(fileList.get(i));
			if (i % maxThreads == 0 && i != 0) {
				new WriteListOfFhirPatientsToOmopAllAtOnce().exec(filesToWrite, connList);
				for (Connection conn : connList) {
					Database.commit(conn);
				}
				cnt++;
				filesToWrite = new ArrayList<String>();
				logMsg(i, cnt);
			}
		}
		if (filesToWrite.size() > 0) {
			new WriteListOfFhirPatientsToOmopAllAtOnce().exec(filesToWrite, connList);
		}
	}

	private static void logMsg(int i, int cnt) {
		String msg = "";
		msg += "\n\n\n\n------------------------------------------------";
		msg += "\nWriting Datafiles";
		msg += "\nTotal written: " + i;
		msg += "\nBlock count:   " + cnt;
		msg += "\n------------------------------------------------\n\n\n";
		log.info(msg);
	}

}

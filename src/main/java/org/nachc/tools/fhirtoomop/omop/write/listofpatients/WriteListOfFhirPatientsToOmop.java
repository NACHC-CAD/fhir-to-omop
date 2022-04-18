package org.nachc.tools.fhirtoomop.omop.write.listofpatients;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.omop.write.listofpatients.allatonce.WriteListOfFhirPatientsToOmopAllAtOnce;
import org.yaorma.database.Database;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class should be used to write large lists of FHIR patients to an omop
 * database.
 *
 */
@Slf4j
public class WriteListOfFhirPatientsToOmop {

	private static int maxActive = 10;
	
	private static List<WriteListOfFhirPatientsToOmopAllAtOnce> waiting = new ArrayList<WriteListOfFhirPatientsToOmopAllAtOnce>();
	
	private static List<WriteListOfFhirPatientsToOmopAllAtOnce> active = new ArrayList<WriteListOfFhirPatientsToOmopAllAtOnce>();
	
	public static void exec(List<String> fileList, List<Connection> connList, int maxThreads) {
		ArrayList<String> filesToWrite = new ArrayList<String>();
		int cnt = 0;
		// add everything to waiting
		for (int i = 0; i < fileList.size(); i++) {
			filesToWrite.add(fileList.get(i));
			if (i % maxThreads == 0 && i != 0) {
				WriteListOfFhirPatientsToOmopAllAtOnce writer = new WriteListOfFhirPatientsToOmopAllAtOnce(filesToWrite, connList);
				waiting.add(writer);
				cnt++;
				filesToWrite = new ArrayList<String>();
				logMsg(i, cnt);
			}
		}
		while(active.size() < maxActive && waiting.size() > 0) {
			WriteListOfFhirPatientsToOmopAllAtOnce writer = waiting.remove(0);
			active.add(writer);
			writer.exec();
			TimeUtil.sleep(1);
		}
	}
	
	public static void done(WriteListOfFhirPatientsToOmopAllAtOnce writer) {
		active.remove(writer);
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

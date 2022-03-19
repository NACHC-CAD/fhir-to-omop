package org.nachc.tools.fhirtoomop.tools.populate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.tools.fhirtoomop.util.db.write.patienteverything.WriteAllFilesToOmop;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateFromFileList {

	private static final int MAX_THREADS = 2000;

	public static void writeAllFiles(List<String> fileList, List<Connection> connList) {
		ArrayList<String> filesToWrite = new ArrayList<String>();
		int cnt = 0;
		for (int i = 0; i < fileList.size(); i++) {
			filesToWrite.add(fileList.get(i));
			if (i % MAX_THREADS == 0 && i != 0) {
				new WriteAllFilesToOmop().exec(filesToWrite, connList);
				for (Connection conn : connList) {
					Database.commit(conn);
				}
				cnt++;
				filesToWrite = new ArrayList<String>();
				logMsg(i, cnt);
			}
		}
		if (filesToWrite.size() > 0) {
			new WriteAllFilesToOmop().exec(filesToWrite, connList);
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

package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import org.nachc.tools.fhirtoomop.tools.download.terminology.DownloadDefaultTerminology;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VOC00_DownloadTerminology {

	public static void main(String[] args) {
		exec();
	}
	
	public static void exec() {
		DownloadDefaultTerminology.exec();
	}

}

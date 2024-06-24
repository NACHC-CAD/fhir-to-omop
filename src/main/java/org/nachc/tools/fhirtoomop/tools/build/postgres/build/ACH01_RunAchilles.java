package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.File;
import java.io.InputStream;

import org.nachc.tools.fhirtoomop.tools.build.achilles.RunAchilles;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ACH01_RunAchilles {

	public static void main(String[] args) {
		exec();
		log.info("Done.");
	}
	
	public static void exec() {
		RunAchilles.exec();
	}

}

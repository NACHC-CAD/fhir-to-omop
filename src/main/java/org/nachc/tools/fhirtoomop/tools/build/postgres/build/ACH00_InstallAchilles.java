package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import org.nachc.tools.fhirtoomop.tools.build.achilles.InstallAchilles;
import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ACH00_InstallAchilles {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		InstallAchilles.exec();
		log.info("Done with ACH00_InstallAchilles");
	}

}













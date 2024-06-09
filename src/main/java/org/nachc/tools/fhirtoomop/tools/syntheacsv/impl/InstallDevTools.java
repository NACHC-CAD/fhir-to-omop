package org.nachc.tools.fhirtoomop.tools.syntheacsv.impl;

import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstallDevTools {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		try {
			log.info("\n\n\n");
			log.info("\n-----------------------------------");
			log.info("Installing devtools...");
			// get the file to run
			String rScript = "install.packages(\"devtools\", repos = \"http://cran.us.r-project.org\")";
			RunRFileAsBat.run(rScript);
			log.info("Done installing devtools.");
			log.info("\n-----------------------------------");
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}

}

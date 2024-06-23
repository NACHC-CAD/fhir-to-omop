package org.nachc.tools.fhirtoomop.tools.build.achilles;

import java.io.File;
import java.io.InputStream;

import org.nachc.tools.fhirtoomop.tools.build.driver.types.DriverType;
import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunAchilles {

	private static final String ACHILLES_SCRIPT = "/achilles/run-achilles.r";

	private static final String INSTALL_SCRIPT = "/achilles/install-achilles.r";

	public static void main(String[] args) {
		exec();
		log.info("Done.");
	}

	public static void exec() {
		log.info("Installing and running Achilles...");
		runAchilles();
		log.info("Done with Achilles.");
	}

	private static void runAchilles() {
		// run achilles
		log.info("---------------------");
		log.info("START: Running Achilles...");
		// get the parameters
		String rString = FileUtil.getAsString(ACHILLES_SCRIPT);
		String dbms = AppParams.get("atlasDbms");
		String user = AppParams.get("uid");
		String pwd = AppParams.get("pwd");
		String server = AppParams.get("ServerName");
		String port = AppParams.get("Port");
		String cdmVersion = AppParams.get("cdm_version");
		String cdmDbName = AppParams.get("atlasCdm");
		String resultsDbName = AppParams.get("atlasResults");
		String pathToDriver = AppParams.get("DatabaseDrvierPath");
		pathToDriver = pathToDriver.replace("\\", "\\\\");
		// update the script with the parameters
		rString = rString.replace("@dbms", dbms);
		rString = rString.replace("@user", user);
		rString = rString.replace("@pwd", pwd);
		rString = rString.replace("@server", server);
		rString = rString.replace("@port", port);
		rString = rString.replace("@pathToDriver", pathToDriver);
		rString = rString.replace("@cdmVersion", cdmVersion);
		rString = rString.replace("@cdmDbName", cdmDbName);
		rString = rString.replace("@resultsDbName", resultsDbName);
		String msg = "Running Achilles script...\n\n";
		msg += "* * * \n";
		msg += "* RUNNING ACHILLES R SCRIPT\n";
		msg += "* * * \n";
		msg += "\n";
		msg += "---START SCRIPT----------------------------\n\n";
		msg += rString;
		msg += "\n";
		msg += "---END SCRIPT------------------------------\n";
		msg += "\n\n";
		log.info(msg);
		// download the driver if it doesn't exist
		downloadDriver(pathToDriver);
		log.info("Running Achilles...");
		RunRFileAsBat.run(rString);
		log.info("DONE: Running Achilles...");
	}

	private static void downloadDriver(String pathToDriver) {
		log.info("Checking for database driver...");
		DriverType driverType = getDriverType();
//		String url = "https://www.dropbox.com/scl/fi/drg1kgckeykub44uwgeay/postgresql-42.3.3.jar?rlkey=xopakpf2zokbjorl2pmtkat2a&st=e2ou9gxh&dl=1";
//		String fileName = "postgresql-42.3.3.jar";
		String url = driverType.getParams().getUrl();
		String fileName = driverType.getParams().getFileName();
		File dir = new File(pathToDriver);
		File file = new File(dir, fileName);
		if (dir.exists() == false || file.exists() == false) {
			log.info("Driver not found, downloading now...");
			FileUtil.rmdir(dir);
			FileUtil.mkdirs(dir);
			HttpRequestClient client = new HttpRequestClient(url);
			client.doGet();
			InputStream is = client.getResponseInputStream();
			FileUtil.write(is, file);
			log.info("Done writing driver to: \n" + FileUtil.getCanonicalPath(file));
		} else {
			log.info("Driver already exists, skipping download.");
		}
	}
	
	private static DriverType getDriverType() {
		String driverName = AppParams.get("DatabaseDriverName");
		DriverType rtn = DriverType.getDriverType(driverName);
		return rtn;
	}

}

package org.nachc.tools.fhirtoomop.tools.build.postgres.build;

import java.io.File;
import java.io.InputStream;

import org.nachc.tools.fhirtoomop.util.params.AppParams;
import org.nachc.tools.fhirtoomop.util.win.r.RunRFileAsBat;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.http.HttpRequestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ACH1_RunAchilles {

	public static void main(String[] args) {
		exec();
	}

	public static void exec() {
		// run achilles
		log.info("---------------------");
		log.info("START: Running Achilles...");
		String rString = FileUtil.getAsString("/postgres/build/r/achilles/run-achilles.r");
		String dbms = AppParams.get("cdmDbType");
		String user = AppParams.get("ohdsiAdminUserUid");
		String pwd = AppParams.get("ohdsiAdminUserPwd");
		String server = AppParams.get("postgresServer");
		String port = AppParams.get("postgresPort");
		String pathToDriver = AppParams.get("postgresPathToDriver");
		// download the driver if it doesn't exist
		downloadDriver(pathToDriver);
//		RunRFileAsBat.run(rString);
		log.info("DONE: Running Achilles...");
		log.info("---------------------");
	}

	private static void downloadDriver(String pathToDriver) {
		log.info("Checking for database driver...");
		String url = "https://www.dropbox.com/scl/fi/drg1kgckeykub44uwgeay/postgresql-42.3.3.jar?rlkey=xopakpf2zokbjorl2pmtkat2a&st=e2ou9gxh&dl=1";
		String fileName = "postgresql-42.3.3.jar";
		File dir = new File(pathToDriver);
		File file = new File(dir, fileName);
		if(dir.exists() == false || file.exists() == false) {
			log.info("PostgreSql driver not found, downloading now...");
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
	
}













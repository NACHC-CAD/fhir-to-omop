package org.nachc.tools.fhirtoomop.tools.dqd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCallerOptions;
import com.github.rcaller.rstuff.RCode;
import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunDqdInitScript {

//	private static final String PATH = "/dqd/run-dqd.r";
	private static final String PATH = "/dqd/init-params-for-dtq.r";

	public static void exec() {
		String script = FileUtil.getAsString(PATH);
		log.info("SCRIPT:\n\n" + script + "\n\n");
		log.info("RUNNING SCRIPT...\n");
		RCaller caller = RCaller.create();
		caller.redirectROutputToStream(System.out);

		StringBuffer sb = new StringBuffer();
		sb.append(script);
		RCode code = RCode.create(sb);
		caller.setRCode(code);
		caller.runOnly();
		log.info("Done.");
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		int[] values = {1,2,3,4,5,6};
		String fileContent = FileUtil.getAsString(PATH);
		RCode code = RCode.create();
		code.addRCode(fileContent);
		code.addIntArray("input", values);
//		code.addRCode("result <- customMean(input)");
		RCaller caller = RCaller.create(code, RCallerOptions.create());
		File file = new File("C:\\temp\\temp.txt");
		OutputStream out = new FileOutputStream(file);
		caller.redirectROutputToStream(out);
		caller.runAndReturnResult("dbms");
		String foo = caller.getParser().getAsStringArray("dbms")[0];
		System.out.println(foo);
		out.flush();
		out.close();
		System.out.println("Done.");
	}

}

package org.nachc.tools.fhirtoomop.tools.dqd;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunDqdInitScript {

	private static final String PATH = "/dqd/init-params-for-dtq.r";

	public static void exec() {
		String script = FileUtil.getAsString(PATH);
		log.info("SCRIPT:\n\n" + script + "\n\n");
		log.info("RUNNING SCRIPT...\n");
		RCaller caller = RCaller.create();
		// caller.redirectROutputToStream(null);
		StringBuffer sb = new StringBuffer();
		sb.append(script);
		RCode code = RCode.create(sb);
		caller.setRCode(code);
		caller.runOnly();
		log.info("Done.");
	}

}

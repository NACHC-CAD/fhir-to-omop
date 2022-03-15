package org.nachc.tools.fhirtoomop.tools.dqd;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.script.ScriptEngine;

import org.renjin.script.RenjinScriptEngineFactory;

import com.github.rcaller.rstuff.RCaller;
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

	public static void main(String[] args) throws Exception {
		String script = FileUtil.getAsString(PATH);

		RenjinScriptEngineFactory factory = new RenjinScriptEngineFactory();
		PrintWriter writer = new PrintWriter(System.out);
		// create a Renjin engine:
		ScriptEngine engine = factory.getScriptEngine();
		engine.getContext().setWriter(writer);
		String meanScriptContent = FileUtil.getAsString(PATH);
//		engine.put("input", values);
		engine.eval(script);
//		DoubleArrayVector result = (DoubleArrayVector) engine.eval("customMean(input)");
//		return result.asReal();
		System.out.println("Done.");
	}

}

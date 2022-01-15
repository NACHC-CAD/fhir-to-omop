package org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.race;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;
import org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.CodeParser;
import org.nachc.tools.fhirtoomop.util.fhir.parser.codesystem.CodeSystemParser;

import com.nach.core.util.csv.CsvUtilApache;

public class RaceEthnicityParser {

	private String json;

	private CodeSystemParser codeSystem;

	public RaceEthnicityParser(String json) {
		this.json = json;
		this.codeSystem = new CodeSystemParser(json);
	}

	public List<CodeParser> getRaces() {
		List<CodeParser> codes = this.codeSystem.getConcepts();
		for (CodeParser code : codes) {
			if ("1000-9".equals(code.getCode())) {
				return code.getChildren();
			}
		}
		return null;
	}

	public List<CodeParser> getEthnicities() {
		List<CodeParser> codes = this.codeSystem.getConcepts();
		for (CodeParser code : codes) {
			if ("2133-7".equals(code.getCode())) {
				return code.getChildren();
			}
		}
		return null;
	}

	public List<CodeParser> getConcepts() {
		List<CodeParser> rtn = new ArrayList<CodeParser>();
		List<CodeParser> rootCodes = this.codeSystem.getConcepts();
		for (CodeParser code : rootCodes) {
			rtn.add(code);
			rtn.addAll(code.getChildren());
		}
		return rtn;
	}

	public void writeRaceToFile(File file) {
		List<CodeParser> codes = this.getRaces();
		writeToFile(file, codes);
	}

	public void writeEthToFile(File file) {
		List<CodeParser> codes = this.getEthnicities();
		writeToFile(file, codes);
	}

	
	public void writeToFile(File file) {
		List<CodeParser> codes = this.getConcepts();
		writeToFile(file, codes);
	}

	
	public void writeToFile(File file, List<CodeParser> codes) {
		try {
			CSVPrinter printer = CsvUtilApache.getWriter(file);
			List<String> headerRow = new ArrayList<String>();
			headerRow.add("depth");
			headerRow.add("parent_code");
			headerRow.add("parent_display");
			headerRow.add("code");
			headerRow.add("display");
			headerRow.add("is_abstract");
			headerRow.add("definition");
			printer.printRecord(headerRow);
			printer.flush();
			for (CodeParser code : codes) {
				List<String> row = new ArrayList<String>();
				row.add(code.getDepth() != null ? code.getDepth() + "" : "");
				row.add(code.getParentCode() != null ? code.getParentCode() : "");
				row.add(code.getParentDisplay() != null ? code.getParentDisplay() : "");
				row.add(code.getCode() != null ? code.getCode() : "");
				row.add(code.getDisplay() != null ? code.getDisplay() : "");
				row.add(code.isAbstract() + "");
				row.add(code.getDefinition() != null ? code.getDefinition() : "");
				printer.printRecord(row);
				printer.flush();
			}
			printer.close();
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}
}

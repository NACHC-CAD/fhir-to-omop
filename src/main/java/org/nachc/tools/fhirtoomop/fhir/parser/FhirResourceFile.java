package org.nachc.tools.fhirtoomop.fhir.parser;

import java.io.File;

import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.nachc.tools.fhirtoomop.fhir.parser.valueset.ValueSetParser;

import com.nach.core.util.fhir.parser.FhirJsonParser;
import com.nach.core.util.file.FileUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FhirResourceFile<T extends IBaseResource> {

	private File file;
	
	private T resource;
	
	public FhirResourceFile(File file, Class<T> cls) {
		this.file = file;
		String json = FileUtil.getAsString(file);
		this.resource = FhirJsonParser.parse(json, cls);
	}

	public String getCanonicalPath() {
		return FileUtil.getCanonicalPath(file);
	}

	public String getFileName() {
		return file.getName();
	}
	
}

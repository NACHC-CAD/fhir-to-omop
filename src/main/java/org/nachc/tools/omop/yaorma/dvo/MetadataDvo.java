//
// Data Value Object (DVO) for metadata
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class MetadataDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "metadata";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "metadata_concept_id",
        "metadata_date",
        "metadata_datetime",
        "metadata_id",
        "metadata_type_concept_id",
        "name",
        "value_as_concept_id",
        "value_as_number",
        "value_as_string"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "metadata_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "metadataConceptId",
        "metadataDate",
        "metadataDatetime",
        "metadataId",
        "metadataTypeConceptId",
        "name",
        "valueAsConceptId",
        "valueAsNumber",
        "valueAsString"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "MetadataConceptId",
        "MetadataDate",
        "MetadataDatetime",
        "MetadataId",
        "MetadataTypeConceptId",
        "Name",
        "ValueAsConceptId",
        "ValueAsNumber",
        "ValueAsString"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer metadataConceptId;
    
    private String metadataDate;
    
    private String metadataDatetime;
    
    private Integer metadataId;
    
    private Integer metadataTypeConceptId;
    
    private String name;
    
    private Integer valueAsConceptId;
    
    private String valueAsNumber;
    
    private String valueAsString;
    
    private ConceptDvo metadataConceptDvo;
    
    private ConceptDvo metadataTypeConceptDvo;
    
    private ConceptDvo valueAsConceptDvo;
    
    //
    // trivial getters and setters
    //
    
    // metadataConceptId
    
    public void setMetadataConceptId(Integer val) {
        this.metadataConceptId = val;
    }
    
    public Integer getMetadataConceptId() {
        return this.metadataConceptId;
    }
    
    // metadataDate
    
    public void setMetadataDate(String val) {
        this.metadataDate = val;
    }
    
    public String getMetadataDate() {
        return this.metadataDate;
    }
    
    // metadataDatetime
    
    public void setMetadataDatetime(String val) {
        this.metadataDatetime = val;
    }
    
    public String getMetadataDatetime() {
        return this.metadataDatetime;
    }
    
    // metadataId
    
    public void setMetadataId(Integer val) {
        this.metadataId = val;
    }
    
    public Integer getMetadataId() {
        return this.metadataId;
    }
    
    // metadataTypeConceptId
    
    public void setMetadataTypeConceptId(Integer val) {
        this.metadataTypeConceptId = val;
    }
    
    public Integer getMetadataTypeConceptId() {
        return this.metadataTypeConceptId;
    }
    
    // name
    
    public void setName(String val) {
        this.name = val;
    }
    
    public String getName() {
        return this.name;
    }
    
    // valueAsConceptId
    
    public void setValueAsConceptId(Integer val) {
        this.valueAsConceptId = val;
    }
    
    public Integer getValueAsConceptId() {
        return this.valueAsConceptId;
    }
    
    // valueAsNumber
    
    public void setValueAsNumber(String val) {
        this.valueAsNumber = val;
    }
    
    public String getValueAsNumber() {
        return this.valueAsNumber;
    }
    
    // valueAsString
    
    public void setValueAsString(String val) {
        this.valueAsString = val;
    }
    
    public String getValueAsString() {
        return this.valueAsString;
    }
    
    // metadataConceptDvo
    
    public void setMetadataConceptDvo(ConceptDvo dvo) {
        this.metadataConceptDvo = dvo;
    }
    
    public ConceptDvo getMetadataConceptDvo() {
        return this.metadataConceptDvo;
    }
    
    // metadataTypeConceptDvo
    
    public void setMetadataTypeConceptDvo(ConceptDvo dvo) {
        this.metadataTypeConceptDvo = dvo;
    }
    
    public ConceptDvo getMetadataTypeConceptDvo() {
        return this.metadataTypeConceptDvo;
    }
    
    // valueAsConceptDvo
    
    public void setValueAsConceptDvo(ConceptDvo dvo) {
        this.valueAsConceptDvo = dvo;
    }
    
    public ConceptDvo getValueAsConceptDvo() {
        return this.valueAsConceptDvo;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String getSchemaName() {
        return SCHEMA_NAME;
    };
    
    public String[] getColumnNames() {
        return COLUMN_NAMES;
    };
    
    public String[] getPrimaryKeyColumnNames() {
        return PRIMARY_KEY_COLUMN_NAMES;
    };
    
    public String[] getJavaNames() {
        return JAVA_NAMES;
    };
    
    public String[] getJavaNamesProper() {
        return JAVA_NAMES_PROPER;
    };
    
    public void setDescriptions(HashMap<String, String> descriptions) {
        this.descriptions = descriptions;
    }
    
    public HashMap<String, String> getDescriptions() {
        return this.descriptions;
    }
    
    public void addDescription(String javaName, String value) {
        this.descriptions.put(javaName, value);
    }
    
    public String getDescription(String javaName) {
        return this.descriptions.get(javaName);
    }
    
    public String[] getPrimaryKeyValues() {
        String[] rtn = new String[] {
            getMetadataId()  == null ? null: getMetadataId() + ""
        };
        return rtn;
    }
}

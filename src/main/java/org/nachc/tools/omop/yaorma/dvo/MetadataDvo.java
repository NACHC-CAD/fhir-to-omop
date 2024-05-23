//
// Data Value Object (DVO) for METADATA
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.math.BigDecimal;

import org.yaorma.dvo.Dvo;

public class MetadataDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "METADATA";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_micro.dbo";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "metadata_id",
        "metadata_concept_id",
        "metadata_type_concept_id",
        "name",
        "value_as_string",
        "value_as_concept_id",
        "value_as_number",
        "metadata_date",
        "metadata_datetime"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "metadataId",
        "metadataConceptId",
        "metadataTypeConceptId",
        "name",
        "valueAsString",
        "valueAsConceptId",
        "valueAsNumber",
        "metadataDate",
        "metadataDatetime"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "MetadataId",
        "MetadataConceptId",
        "MetadataTypeConceptId",
        "Name",
        "ValueAsString",
        "ValueAsConceptId",
        "ValueAsNumber",
        "MetadataDate",
        "MetadataDatetime"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer metadataId;
    
    private Integer metadataConceptId;
    
    private Integer metadataTypeConceptId;
    
    private String name;
    
    private String valueAsString;
    
    private Integer valueAsConceptId;
    
    private BigDecimal valueAsNumber;
    
    private Date metadataDate;
    
    private Date metadataDatetime;
    
    //
    // trivial getters and setters
    //
    
    // metadataId
    
    public void setMetadataId(Integer val) {
        this.metadataId = val;
    }
    
    public Integer getMetadataId() {
        return this.metadataId;
    }
    
    // metadataConceptId
    
    public void setMetadataConceptId(Integer val) {
        this.metadataConceptId = val;
    }
    
    public Integer getMetadataConceptId() {
        return this.metadataConceptId;
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
    
    // valueAsString
    
    public void setValueAsString(String val) {
        this.valueAsString = val;
    }
    
    public String getValueAsString() {
        return this.valueAsString;
    }
    
    // valueAsConceptId
    
    public void setValueAsConceptId(Integer val) {
        this.valueAsConceptId = val;
    }
    
    public Integer getValueAsConceptId() {
        return this.valueAsConceptId;
    }
    
    // valueAsNumber
    
    public void setValueAsNumber(BigDecimal val) {
        this.valueAsNumber = val;
    }
    
    public BigDecimal getValueAsNumber() {
        return this.valueAsNumber;
    }
    
    // metadataDate
    
    public void setMetadataDate(Date val) {
        this.metadataDate = val;
    }
    
    public Date getMetadataDate() {
        return this.metadataDate;
    }
    
    // metadataDatetime
    
    public void setMetadataDatetime(Date val) {
        this.metadataDatetime = val;
    }
    
    public Date getMetadataDatetime() {
        return this.metadataDatetime;
    }
    
    //
    // implementation of Dvo
    //
    
    public String getTableName() {
        return TABLE_NAME;
    };
    
    public String getSchemaName() {
        return org.nachc.tools.fhirtoomop.util.params.AppParams.getFullySpecifiedSchemaName();
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
        };
        return rtn;
    }
}

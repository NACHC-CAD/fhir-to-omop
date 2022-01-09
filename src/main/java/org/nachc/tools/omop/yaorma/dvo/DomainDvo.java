//
// Data Value Object (DVO) for domain
//

package org.nachc.tools.omop.yaorma.dvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import org.yaorma.dvo.Dvo;

public class DomainDvo implements Dvo {

    //
    // tableName
    //
    
    public static final String TABLE_NAME = "domain";
    
    //
    // schemaName
    //
    
    public static final String SCHEMA_NAME = "synthea_omop";
    
    //
    // columnNames
    //
    
    public static final String[] COLUMN_NAMES = {
        "domain_concept_id",
        "domain_id",
        "domain_name"
    };
    
    //
    // primaryKeyColumnNames
    //
    
    public static final String[] PRIMARY_KEY_COLUMN_NAMES = {
        "domain_id"
    };
    
    //
    // javaNames
    //
    
    public static final String[] JAVA_NAMES = {
        "domainConceptId",
        "domainId",
        "domainName"
    };
    
    //
    // javaNamesProper
    //
    
    public static final String[] JAVA_NAMES_PROPER = {
        "DomainConceptId",
        "DomainId",
        "DomainName"
    };
    
    
    //
    // member variables
    //
    
    private HashMap<String, String> descriptions = new HashMap<String, String>();
    
    private Integer domainConceptId;
    
    private String domainId;
    
    private String domainName;
    
    private ConceptDvo domainConceptDvo;
    
    private ArrayList<CostDvo> costCostDomainList = new ArrayList<CostDvo>();
    
    private ArrayList<ConceptDvo> conceptDomainList = new ArrayList<ConceptDvo>();
    
    //
    // trivial getters and setters
    //
    
    // domainConceptId
    
    public void setDomainConceptId(Integer val) {
        this.domainConceptId = val;
    }
    
    public Integer getDomainConceptId() {
        return this.domainConceptId;
    }
    
    // domainId
    
    public void setDomainId(String val) {
        this.domainId = val;
    }
    
    public String getDomainId() {
        return this.domainId;
    }
    
    // domainName
    
    public void setDomainName(String val) {
        this.domainName = val;
    }
    
    public String getDomainName() {
        return this.domainName;
    }
    
    // domainConceptDvo
    
    public void setDomainConceptDvo(ConceptDvo dvo) {
        this.domainConceptDvo = dvo;
    }
    
    public ConceptDvo getDomainConceptDvo() {
        return this.domainConceptDvo;
    }
    
    public ArrayList<CostDvo> getCostCostDomainList() {
        return costCostDomainList;
    }
    
    public void setCostCostDomainList(ArrayList<CostDvo> list) {
        this.costCostDomainList = list;
    }
    
    public ArrayList<ConceptDvo> getConceptDomainList() {
        return conceptDomainList;
    }
    
    public void setConceptDomainList(ArrayList<ConceptDvo> list) {
        this.conceptDomainList = list;
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
            getDomainId()  == null ? null: getDomainId() + ""
        };
        return rtn;
    }
}

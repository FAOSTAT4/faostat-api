package org.fao.faostat.constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 * */
public class QUERIES {

    private Map<String, String> queries;

    public QUERIES() {
        this.setQueries(new HashMap<String, String>());
        this.getQueries().put("groups", "SELECT D.GroupCode AS code, D.GroupName{{lang}} AS label FROM Domain D GROUP BY D.GroupCode, D.GroupName{{lang}}");
        this.getQueries().put("domains", "SELECT D.DomainCode AS code, D.DomainName{{lang}} AS label, D.Ord AS ord FROM Domain D WHERE D.GroupCode = '{{group_code}}' ORDER BY D.Ord");
        this.getQueries().put("groupsanddomains", "SELECT D.GroupCode AS code, D.GroupName{{lang}} AS label, D.DomainCode, D.DomainName{{lang}}, D.Ord AS ord FROM Domain D ORDER BY D.Ord");
        this.getQueries().put("dimensions", "EXEC Warehouse.dbo.usp_GetDomainListBoxes @DomainCode = N'{{domain_code}}', @Lang = N'{{lang}}'");
        this.getQueries().put("methodologies", "SELECT M.MethodologyCode AS code, M.MethodologyTitle{{lang}} AS label FROM Metadata_Methodology AS M GROUP BY M.MethodologyCode, M.MethodologyTitle{{lang}} ORDER BY M.MethodologyTitle{{lang}} ASC");
        this.getQueries().put("methodology", "SELECT M.MethodologyNote{{lang}} AS note, M.MethodologyCoverage{{lang}} AS coverage, M.MethodologyReferences{{lang}} AS reference, M.MethodologyCollection{{lang}} AS collection, M.MethodologyEstimation{{lang}} AS estimation FROM Metadata_Methodology AS M WHERE M.MethodologyCode='{{methodology_code}}'");
        this.getQueries().put("classifications", "SELECT M.ItemCode AS code, M.ItemName{{lang}} AS label, M.ItemDescription{{lang}} AS description FROM Metadata_Item AS M WHERE M.domaincode = '{{domain_code}}' ORDER BY M.ItemName{{lang}} ASC");
        this.getQueries().put("units", "SELECT E.UnitAbbreviation{{lang}} AS code, E.UnitTitle{{lang}} AS label FROM Metadata_Unit AS E ORDER BY E.UnitAbbreviation{{lang}} ASC");
        this.getQueries().put("glossary", "SELECT M.GlossaryName{{lang}} AS code, M.GlossaryDefinition{{lang}} AS label, M.GlossarySource{{lang}} AS source FROM Metadata_Glossary AS M ORDER BY M.GlossaryName{{lang}} ASC");
        this.getQueries().put("abbreviations", "SELECT M.AbbreviationTitle{{lang}} AS code, AbbreviationDefinition{{lang}} AS label FROM Metadata_Abbreviation AS M ORDER BY AbbreviationTitle{{lang}} ASC");
        this.getQueries().put("codes", "EXEC Warehouse.dbo.usp_GetListBox @DomainCode = N'{{domain_code}}', @Lang = N'{{lang}}', @ListBoxNO = {{dimension}}, @TabOrder = {{subdimension}}");
        this.getQueries().put("bulkdownloads", "SELECT B.Domain AS code, B.Source AS label, B.Filename AS filename, B.FileContent AS content, B.CreatedDate AS date FROM BulkDownloads B WHERE B.LanguageID = '{{lang}}' AND B.Domain = '{{domain_code}}'");
    }

    public String getQuery(String id, Map<String, String> procedureParameters) throws IOException {
        String query = this.getQueries().get(id.toLowerCase());
        for (String key : procedureParameters.keySet()) {
            String tmp = "\\{\\{" + key + "\\}\\}";
            query = query.replaceAll(tmp, procedureParameters.get(key));
        }
        System.out.println(query);
        return query;
    }

    public Map<String, String> getQueries() {
        return queries;
    }

    public void setQueries(Map<String, String> queries) {
        this.queries = queries;
    }

}

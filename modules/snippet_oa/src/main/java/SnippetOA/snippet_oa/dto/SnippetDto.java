package SnippetOA.snippet_oa.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnippetDto {
	
	@JsonProperty("snippet_id")
    private long snippetId;
	
	@JsonProperty("type_language")
    private String typeLanguage;
	
	@JsonProperty("syntax_code")
    private String syntaxCode;
    
	@JsonProperty("create_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    
	@JsonProperty("update_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
	
}

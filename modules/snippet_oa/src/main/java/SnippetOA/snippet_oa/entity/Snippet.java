package SnippetOA.snippet_oa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "snippet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long snippetId;
	
	@Column(name = "type_language")
    private String typeLanguage;
	
    @Column(name = "syntax_code")
    private String syntaxCode;
    
    @Column(name = "create_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name = "update_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

//    @Column(name = "create_user_id")
//    private int userId;
}

package SnippetOA.snippet_oa.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiWrapper {
	private Boolean success;
	private Integer errorCode;
	private String message;
	private Object data;
}

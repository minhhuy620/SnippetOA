package SnippetOA.snippet_oa.service;

import SnippetOA.snippet_oa.dto.SnippetDto;
import SnippetOA.snippet_oa.wrapper.ApiWrapper;

public interface SnippetService {
	ApiWrapper getAllSnippets();
	ApiWrapper saveSnippet(SnippetDto theSnippet);
}

package SnippetOA.snippet_oa.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SnippetOA.snippet_oa.dto.SnippetDto;
import SnippetOA.snippet_oa.entity.Snippet;
import SnippetOA.snippet_oa.repository.SnippetRepository;
import SnippetOA.snippet_oa.wrapper.ApiWrapper;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SnippetOA.snippet_oa.service.SnippetService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SnippetServiceImpl implements SnippetService {
	
	private static final Logger LOGGER = LogManager.getLogger(SnippetServiceImpl.class);
	@Autowired
	private SnippetRepository snippetRepository;

	@Override
	public ApiWrapper getAllSnippets()  {
		List<Snippet> data = snippetRepository.findAll();
		List<SnippetDto> dataDto = new ArrayList<>();
		data.forEach(dataCheck ->{
			SnippetDto dto = new SnippetDto();
			try {
				byte[] bytesData = Hex.decodeHex(dataCheck.getSyntaxCode());
				String getSyntaxCode = new String(bytesData, StandardCharsets.UTF_8);
				dto.setSnippetId(dataCheck.getSnippetId());
				dto.setTypeLanguage(dataCheck.getTypeLanguage());
				dto.setSyntaxCode(getSyntaxCode);
				dto.setCreatedDate(dataCheck.getCreatedDate());
				dto.setUpdatedDate(dataCheck.getUpdatedDate());
				dataDto.add(dto);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		return new ApiWrapper(true,1,"Success",dataDto);
	}

	@Transactional
	@Override
	public ApiWrapper saveSnippet(SnippetDto theSnippet) {

		Snippet snippet = new Snippet();
		try {
			String syntaxCodeData = theSnippet.getSyntaxCode();
			if (syntaxCodeData == "" || syntaxCodeData.isEmpty() || syntaxCodeData == null)
			{
				throw new RuntimeException("Syntax is NULL or empty");
			}
			else{
				String syntaxCode = theSnippet.getSyntaxCode();
				char[] charsCode = Hex.encodeHex(syntaxCode.getBytes(StandardCharsets.UTF_8));
				snippet.setTypeLanguage(theSnippet.getTypeLanguage());
				snippet.setSyntaxCode(String.valueOf(charsCode));
				snippet.setCreatedDate(new Date());
				snippetRepository.save(snippet);
			}
		}catch (Exception e){
			return new ApiWrapper(false, 0, e.getMessage(), null);
		}
		return new ApiWrapper(true, 1, "Add success", snippet);
	}


}

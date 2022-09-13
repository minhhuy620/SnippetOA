package SnippetOA.snippet_oa.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import SnippetOA.snippet_oa.dto.SnippetDto;
import SnippetOA.snippet_oa.entity.Snippet;
import SnippetOA.snippet_oa.repository.SnippetRepository;
import SnippetOA.snippet_oa.wrapper.ApiWrapper;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SnippetOA.snippet_oa.service.SnippetService;

@Service
public class SnippetServiceImpl implements SnippetService {
	
	private static final Logger LOGGER = LogManager.getLogger(SnippetServiceImpl.class);
	private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
	@Autowired
	private SnippetRepository snippetRepository;

	public static String bytesToHex(byte[] bytes) {
		byte[] hexChars = new byte[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars, StandardCharsets.UTF_8);
	}
	@Override
	public ApiWrapper getSnippet()  {
		List<Snippet> data = snippetRepository.findAll();
		List<SnippetDto> dataDto = new ArrayList<>();
		data.forEach(dataCheck ->{
			SnippetDto dto = new SnippetDto();
			String syntaxCode = dataCheck.getSyntaxCode();
			String bytesToStringHex = bytesToHex(syntaxCode.getBytes(StandardCharsets.UTF_8));
			String OutputString = new String();
			char[] Temp_Char = bytesToStringHex.toCharArray();
			for(int x = 0; x < Temp_Char.length; x=x+2) {
				String Temp_String = ""+Temp_Char[x]+""+Temp_Char[x+1];
				char character = (char)Integer.parseInt(Temp_String, 16);
				OutputString = OutputString + character;
			}
			try {
				dto.setSnippetId(dataCheck.getSnippetId());
				dto.setTypeLanguage(dataCheck.getTypeLanguage());
				dto.setSyntaxCode(OutputString);
				dto.setCreatedDate(dataCheck.getCreatedDate());
				dto.setUpdatedDate(dataCheck.getUpdatedDate());
				dataDto.add(dto);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		return new ApiWrapper(true,1,"Success",dataDto);
	}

}

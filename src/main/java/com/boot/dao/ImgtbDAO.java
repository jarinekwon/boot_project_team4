package com.boot.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.ImgtbDTO;

@Mapper
public interface ImgtbDAO {
	public void imgupload(ImgtbDTO imgtbdto);
	public ImgtbDTO getFileselect(HashMap<String, String> param);
	public ImgtbDTO getFile_coinfo_select(HashMap<String, String> param);
	
	public void imgupload_resume(ImgtbDTO imgtbdto);
	public ImgtbDTO imgdata(ImgtbDTO imgtbdto);
	public void deleteImgdata(ImgtbDTO imgtbdto);
	public void deleteImgdata_coinfo(ImgtbDTO imgtbdto);
	public int imgcnt(ImgtbDTO imgtbdto); //이미지 여부 확인
}

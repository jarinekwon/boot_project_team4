package com.boot.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boot.dao.ImgtbDAO;
import com.boot.dto.ImgtbDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@Service("ImgtbService")
public class ImgtbServiceImpl implements ImgtbService {
	
	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private ServletContext servletContext; // ServletContext 주입
	
	@Override
	public void imgupload(ImgtbDTO imgtbdto) {
		log.info("@# ImgtbServiceImpl imgupload");
		ImgtbDAO dao = sqlsession.getMapper(ImgtbDAO.class);
		log.info("@# imgtbdto=> "+imgtbdto);
		dao.imgupload(imgtbdto);
	}

	@Override
	public ImgtbDTO getFileselect(HashMap<String, String> param) {
		log.info("@# getFileselect");

		ImgtbDAO dao = sqlsession.getMapper(ImgtbDAO.class);
		return dao.getFileselect(param);
	}

	@Override
	public void imgupload_resume(ImgtbDTO imgtbdto, MultipartFile uploadFile, String basepath) {
		log.info("@# ImgtbServiceImpl imgupload_resume");
		ImgtbDAO dao = sqlsession.getMapper(ImgtbDAO.class);
		
		String uploadFolder = basepath;
		String uploadFolderPath = imgtbdto.getUploadpath();
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		//폴더 없으면 폴더 생성
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		log.info("@# 파일 이름 => "+uploadFile.getOriginalFilename()); //업로드되는 파일 이름
		log.info("@# 파일 크기 => "+uploadFile.getSize()); //업로드되는 파일 크기
		
		String uploadFileName = imgtbdto.getUuid()+"_"+imgtbdto.getFilename();
		log.info("@# uuid_uploadFileName => "+uploadFileName);
		//saveFile : 경로하고 파일이름
		File saveFile = new File(uploadPath, uploadFileName);
		FileInputStream fis = null;
		
		try {
			//transferTo : savaFile 내용을 저장
			uploadFile.transferTo(saveFile);

			//썸네일 추가 로직
			fis = new FileInputStream(saveFile);
			//썸네일 파일은 s_를 앞에 추가
			FileOutputStream thumnail = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
			//썸내일 파일 형식을 100*100 크기로 생성
			Thumbnailator.createThumbnail(fis, thumnail, 100, 100);
			thumnail.close();
			
			log.info("@# img file add()");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dao.imgupload_resume(imgtbdto); //받은 정보 DB에 저장
	}

	@Override
	public void imgdelete_resume(ImgtbDTO imgtbdto) {
		log.info("@# ImgtbServiceImpl imgdelete_resume");
		
		ImgtbDAO dao = sqlsession.getMapper(ImgtbDAO.class);
		
		ImgtbDTO dto = dao.imgdata(imgtbdto); //imgno를 제외한 키를 이용해서 해당 이미지파일 DB 조회
		
		String filename = dto.getUuid() + "_" + dto.getFilename();
		
		File file = null;
		try {
			log.info("@# servlet_realPath => "+servletContext.getRealPath("/"));
			log.info("@# getUploadpath => "+dto.getUploadpath());
			log.info("@# filename  => "+URLDecoder.decode(filename, "UTF-8"));
			file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/"+URLDecoder.decode(filename, "UTF-8"));
			log.info("@# file =>" + file);
			boolean filedelete = file.delete();
			
			if(filedelete) {
				log.info("파일 삭제 성공");
			} else {
				log.info("파일 삭제 실패");
			}

			//String largeFileName = file.getAbsolutePath().replace("s_", "");
			//log.info("@# largeFileName=>"+largeFileName);
			
			file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/s_"+URLDecoder.decode(filename, "UTF-8")); //new File(largeFileName);
			filedelete = file.delete();
			
			if(filedelete) {
				log.info("s_파일 삭제 성공");
			} else {
				log.info("s_파일 삭제 실패");
			}
			dao.deleteImgdata(imgtbdto);
		} catch (UnsupportedEncodingException e) {
		    log.error("파일명 디코딩 오류: " + e.getMessage());
		} catch (SecurityException e) {
		    log.error("파일 접근 권한 오류: " + e.getMessage());
		} catch (Exception e) {
		    log.error("파일 삭제 중 오류 발생: " + e.getMessage());
		} finally {
		    // file 객체의 리소스 해제 등을 위해 필요한 코드 추가
		}
	}

	@Override
	public ImgtbDTO getFile_coinfo_select(HashMap<String, String> param) {
		log.info("@# getFile_coinfo_select");

		ImgtbDAO dao = sqlsession.getMapper(ImgtbDAO.class);
		return dao.getFile_coinfo_select(param);
	}

	@Override
	public void imgdelete_coifno(ImgtbDTO imgtbdto) {
		log.info("@# ImgtbServiceImpl imgdelete_coifno");
		
		ImgtbDAO dao = sqlsession.getMapper(ImgtbDAO.class);
		
		ImgtbDTO dto = dao.imgdata(imgtbdto); //imgno를 제외한 키를 이용해서 해당 이미지파일 DB 조회
		if(dto != null) {
			String filename = dto.getUuid() + "_" + dto.getFilename();
			
			File file = null;
			try {
				log.info("@# servlet_realPath => "+servletContext.getRealPath("/"));
				log.info("@# getUploadpath => "+dto.getUploadpath());
				log.info("@# filename  => "+URLDecoder.decode(filename, "UTF-8"));
				file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/"+URLDecoder.decode(filename, "UTF-8"));
				log.info("@# file =>" + file);
				boolean filedelete = file.delete();
				
				if(filedelete) {
					log.info("파일 삭제 성공");
				} else {
					log.info("파일 삭제 실패");
				}
	
				//String largeFileName = file.getAbsolutePath().replace("s_", "");
				//log.info("@# largeFileName=>"+largeFileName);
				
				file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/s_"+URLDecoder.decode(filename, "UTF-8")); //new File(largeFileName);
				filedelete = file.delete();
				
				if(filedelete) {
					log.info("s_파일 삭제 성공");
				} else {
					log.info("s_파일 삭제 실패");
				}
				dao.deleteImgdata_coinfo(imgtbdto);
			} catch (UnsupportedEncodingException e) {
			    log.error("파일명 디코딩 오류: " + e.getMessage());
			} catch (SecurityException e) {
			    log.error("파일 접근 권한 오류: " + e.getMessage());
			} catch (Exception e) {
			    log.error("파일 삭제 중 오류 발생: " + e.getMessage());
			} finally {
			    // file 객체의 리소스 해제 등을 위해 필요한 코드 추가
			}
		}
	}

	@Override
	public int imgcnt(ImgtbDTO imgtbdto) {
		ImgtbDAO dao = sqlsession.getMapper(ImgtbDAO.class);
		
		return dao.imgcnt(imgtbdto);
	}
}
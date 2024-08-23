package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.RcareerDTO;

@Mapper
public interface RcareerDAO {
    public void createRcareer(HashMap<String, String> param);
    public ArrayList<RcareerDTO> selectRcareer(HashMap<String, String> param); 
    public void updateRcareer(RcareerDTO rcareer);
    public void delete_career(HashMap<String, String> param); //해당 이력서의 경력 삭제
    public void delete_career_All(HashMap<String, String> param); //회원 탈퇴 시 경력 전체 삭제
}
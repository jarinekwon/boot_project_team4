package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.SectortbDTO;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public interface SectortbService {
	public ArrayList<SectortbDTO> Sectortblist();
}
package com.ncm.crud.service;

import java.util.List;

import com.ncm.crud.entity.Assets;

public interface AssetsService {
    public Assets saveAsset(Assets asset) ;

	public List<String> getAllCompnay();

	public List<String> getAllSerialNumbers();

	public static List<String> getSerialNumbersBySubcategory(Long subcategoryId) {
		// TODO Auto-generated method stub
		return null;
	}



}
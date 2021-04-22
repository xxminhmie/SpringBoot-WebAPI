package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.entity.NewsEntity;

public interface INewsService {
	NewsDTO save(NewsDTO newsDTO);
	void delete(long[] ids);
	public List<NewsEntity> findAll();

}

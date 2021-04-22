package com.laptrinhjavaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.NewsConverter;
import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewsEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewsRepository;
import com.laptrinhjavaweb.service.INewsService;

@Service
public class NewsService implements INewsService {
	@Autowired
	private NewsRepository newsRepository;
	// dependency injection

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NewsConverter newsConverter;

	@Override
	public NewsDTO save(NewsDTO newsDTO) {
		NewsEntity newsEntity = new NewsEntity();
		if (newsDTO.getId() != 0) {// update
			NewsEntity oldNewsEntity = newsRepository.findById(newsDTO.getId()).orElse(new NewsEntity());
			newsEntity = newsConverter.toEntity(newsDTO, oldNewsEntity);
		} else {
			// create
			newsEntity = newsConverter.toEntity(newsDTO);
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newsDTO.getCategoryCode());
		newsEntity.setCategory(categoryEntity);
		newsEntity = newsRepository.save(newsEntity);
		return newsConverter.toDTO(newsEntity);
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			newsRepository.deleteById(item);
		}
	}

	@Override
	public List<NewsEntity> findAll() {
		List<NewsEntity> entity = (List<NewsEntity>) newsRepository.findAll();

		return entity;
	}

//	@Override
//	public NewsDTO update(NewsDTO newsDTO) {
//		//NewsEntity oldNews = newsRepository.findOne(newsDTO.getId());
//		NewsEntity oldNewsEntity = newsRepository.findById(newsDTO.getId()).orElse(new NewsEntity());
//		NewsEntity newsEntity = newsConverter.toEntity(newsDTO, oldNewsEntity);
//		CategoryEntity categoryEntity =  categoryRepository.findOneByCode(newsDTO.getCategoryCode());
//		newsEntity.setCategory(categoryEntity);
//		newsEntity = newsRepository.save(newsEntity);
//		return newsConverter.toDTO(newsEntity);
// 	}

}

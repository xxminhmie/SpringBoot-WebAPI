 package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewsDTO;
import com.laptrinhjavaweb.entity.NewsEntity;

@Component
public class NewsConverter {
	public NewsEntity toEntity(NewsDTO newsDTO) {
		NewsEntity entity = new NewsEntity();
		entity.setTitle(newsDTO.getTitle());
		entity.setContent(newsDTO.getContent());
		entity.setShortDescription(newsDTO.getShortDescription());
		entity.setThumbnail(newsDTO.getThumbnail() );
		return entity;
	}
	
	public NewsDTO toDTO (NewsEntity newsEntity) {
		NewsDTO dto = new NewsDTO();
		if(newsEntity.getId() != 0) {
			dto.setId(newsEntity.getId());
		}
		dto.setTitle(newsEntity.getTitle());
		dto.setContent(newsEntity.getContent());
		dto.setShortDescription(newsEntity.getShortDescription());
		dto.setThumbnail(newsEntity.getThumbnail());
		dto.setCreatedDate(newsEntity.getCreatedDate());
		dto.setCreatedBy(newsEntity.getCreatedBy());
		dto.setModifiedBy(newsEntity.getModifiedBy());
		dto.setModifiedDate(newsEntity.getCreatedDate());
		return dto;
	}
	public NewsEntity toEntity(NewsDTO newsDTO, NewsEntity newsEntity) {
		newsEntity.setTitle(newsDTO.getTitle());
		newsEntity.setContent(newsDTO.getContent());
		newsEntity.setShortDescription(newsDTO.getShortDescription());
		newsEntity.setThumbnail(newsDTO.getThumbnail() );
		return newsEntity;
	}

}

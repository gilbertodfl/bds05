package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.devsuperior.movieflix.entities.Movie;

public class MovieDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Campo requerido")
	private String title;

	@NotBlank(message = "Campo requerido")
	private String subTitle;
	
	@NotNull(message = "Campo requerido")
	private Integer year;

	private String imgUrl;

	@NotNull(message = "Campo requerido")
	private String synopsis;
	
	@NotNull(message = "Campo requerido")
	private Long genreId;

	public MovieDTO() {
	}

	
	public MovieDTO(Long id, String title, String subTitle,  Integer year,	String imgUrl,  String synopsis, Long genreId) {
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.year = year;
		this.imgUrl = imgUrl;
		this.synopsis = synopsis;
		this.genreId = genreId;
	}


	public MovieDTO(Movie entity) {
		id = entity.getId();
		title = entity.getTitle();
		subTitle = entity.getSubTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();
		synopsis = entity.getSynopsis();
		genreId = entity.getGenre().getId();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSubTitle() {
		return subTitle;
	}


	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public String getSynopsis() {
		return synopsis;
	}


	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Long getGenreId() {
		return genreId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

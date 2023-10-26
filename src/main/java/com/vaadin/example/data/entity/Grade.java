package com.vaadin.example.data.entity;

public class Grade {

	private Long notenId;
	private int wert;

	public Grade() {
	}

	public Grade(Long notenId, int wert) {
		this.notenId = notenId;
		this.wert = wert;
	}

	public int wert() {
		return wert;
	}

	public Long getId() {
		return notenId;
	}

	public void setId(Long id) {
		this.notenId = notenId;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

/*
	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return String.format("Movie[title= %s, producer = %d]", title, releaseYear);
	}

 */
}

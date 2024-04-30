package com.sirine.series.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idG;
	private String nomG;
	
	@JsonIgnore
	@OneToMany(mappedBy = "genre")
	private List<Serie> series;

	public long getIdG() {
		return idG;
	}
	public void setIdG(long idG) {
		this.idG = idG;
	}
	public String getNomG() {
		return nomG;
	}
	public void setNomG(String nomG) {
		this.nomG = nomG;
	}
	public List<Serie> getSeries() {
		return series;
	}
	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	@Override
	public String toString() {
		return "Genre [idG=" + idG + ", nomG=" + nomG + ", series=" + series + "]";
	}
	
}

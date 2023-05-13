package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "noticias")
public class Noticias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_noticias", columnDefinition = "SERIAL")
	private Integer id_noticias;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String desarrollo;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
}

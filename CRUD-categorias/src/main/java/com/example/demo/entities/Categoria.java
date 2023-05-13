package com.example.demo.entities;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria", columnDefinition = "SERIAL")
	private Integer id_categoria;

	@Column(nullable = false)
	private String descripcion;

	@OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
	private List<Noticias> noticias;

}

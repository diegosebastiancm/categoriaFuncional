package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.Noticias;
import com.example.demo.repository.CategoriaRepository;

@Controller
public class CategoriaController {

	@Autowired
	CategoriaRepository repositorio;
	
	
	@GetMapping("/")
	public String ver(Model modelo) {
		return "inicio";
	}
	
	@GetMapping("/categorias")
	public String verCategorias(Model modelo) {
		List<Categoria> categorias = repositorio.findAll();
		modelo.addAttribute("categorias", categorias);
		return "categoria";
	}
	
	@GetMapping("/categorias/nuevo")
	public String mostrarFormulario(Model modelo) {
		Categoria categoria = new Categoria();
		modelo.addAttribute("categoria", categoria);
		return "crear-categoria";
	}

	@PostMapping("/categorias")
	public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
		repositorio.save(categoria);
		return "redirect:/categorias";
	}

	@GetMapping("/categorias/{id}/editar")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
		modelo.addAttribute("categoria", repositorio.findById(id).get());
		return "editar-categoria";
	}

	@PostMapping("/categorias/{id}")
	public String actualizarCategoria(@PathVariable Integer id, @ModelAttribute("categoria") Categoria categoria, Model modelo) {
		Categoria categoriaExistente = repositorio.findById(id).get();
		categoriaExistente.setId_categoria(id);
		categoriaExistente.setDescripcion(categoria.getDescripcion());
		categoriaExistente.setNoticias(categoria.getNoticias());
		repositorio.save(categoriaExistente);
		return "redirect:/categorias";
	}

	@GetMapping("/categorias/{id}/eliminar")
	public String eliminarCategoria(@PathVariable Integer id) {
		repositorio.deleteById(id);
		return "redirect:/categorias";
	}
	
	@GetMapping("/categorias/{id}/noticias")
    public String listarNoticiasPorCategoria(@PathVariable Integer id, Model modelo) {
        Categoria categoria = repositorio.findById(id).get();
        List<Noticias> noticias = categoria.getNoticias();
        modelo.addAttribute("noticias", noticias);
        return "noticias_categoria";
    }
	
}

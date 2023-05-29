package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Noticias;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.NoticiaRepository;


@Controller
public class NoticiaController {

	@Autowired
	NoticiaRepository repositorio;
	
	@Autowired
	CategoriaRepository repositorioCategoria;
	
	@GetMapping("/noticias")
	public String verNoticias(Model modelo) {
		List<Noticias> noticias = repositorio.findAll();
		modelo.addAttribute("noticias", noticias);
		return "noticias";
	}
	
	@GetMapping("/noticias/nuevo")
	public String mostrarFormulario(Model modelo){
		List<Categoria> listaCategorias = repositorioCategoria.findAll();
		Noticias noticia = new Noticias();
		modelo.addAttribute("noticia", noticia);
		modelo.addAttribute("categorias", listaCategorias);
		return "crear-noticia";
	}
	
	@PostMapping("/noticias")
	public String guardarNoticia(@ModelAttribute("noticia") Noticias noticia) {
		repositorio.save(noticia);
		return "redirect:/noticias";
	}
	
	@GetMapping("/noticias/{id}/editar")
	public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model modelo) {
		List<Categoria> listaCategorias = repositorioCategoria.findAll();
		modelo.addAttribute("noticia", repositorio.findById(id).get());
		modelo.addAttribute("categorias", listaCategorias);
		return "editar-noticia";
	}
	
	@PostMapping("/noticias/{id}")
	public String actualizarNoticia(@PathVariable("id") Integer id, @ModelAttribute("noticia") Noticias noticia) {
		Noticias noticiaExistente = repositorio.findById(id).get();
		noticiaExistente.setId_noticias(id);
		noticiaExistente.setTitulo(noticia.getTitulo());
		noticiaExistente.setDesarrollo(noticia.getDesarrollo());
		noticiaExistente.setCategoria(noticia.getCategoria());
		repositorio.save(noticiaExistente);
		return "redirect:/noticias";
	}
	
	@GetMapping("/noticias/{id}/eliminar")
	public String eliminarNoticia(@PathVariable("id") Integer id) {
		repositorio.deleteById(id);
		return "redirect:/noticias";
	}
}

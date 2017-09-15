package org.restWebService.RPlayer.controller;

import java.io.IOException;
import java.util.List;

import org.restWebService.RPlayer.dto.FilmDto;
import org.restWebService.RPlayer.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("film")
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@RequestMapping(value = "/findAllOrderByNameAsc", method = RequestMethod.GET)
	public List<FilmDto> findAllOrderByNameAsc(){
		return filmService.findAllOrderByNameAsc();
	}
	
	@RequestMapping(value = "/findOne/{idFilm}", method = RequestMethod.GET)
	public FilmDto findOne(@PathVariable("idFilm") Long idFilm){
		return filmService.findOne(idFilm);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public FilmDto save(@RequestBody FilmDto filmDto){
		return filmService.save(filmDto);
	}
	
	@RequestMapping(value = "/uploadImage/{idFilm}", method = RequestMethod.POST)
	public FilmDto uploadImage(@PathVariable("idFilm") Long idFilm, @RequestParam MultipartFile file) {
		FilmDto res = new FilmDto();
		try { 
			byte [] image = file.getBytes();
			res = filmService.uploadFilmImage(idFilm, image);
		} catch (IOException e) {
			System.err.println("Se ha producido un error");
			e.printStackTrace();
		}
		return res;
	}

}

package com.sirine.series.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sirine.series.entities.Serie;
import com.sirine.series.service.SerieService;

@Controller
public class SerieController {

	@Autowired
	SerieService serieService;

	@RequestMapping("/ListeSeries")
	public String listeSeries(ModelMap modelMap, @RequestParam (name="page",defaultValue = "0") int page,@RequestParam (name="size", defaultValue = "3") int size) {
		Page<Serie> series = serieService.getAllSeriesParPage(page, size);
		modelMap.addAttribute("series", series);
		 modelMap.addAttribute("pages", new int[series.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeSeries";
	}

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createSerie";
	}

	@RequestMapping("/saveSerie")
	public String saveSerie(@ModelAttribute Serie serie, @RequestParam String date,ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateSortie = dateformat.parse(String.valueOf(date));
		serie.setDateSortie(dateSortie);

		Serie saveSerie = serieService.saveSerie(serie);
		String msg = "serie enregistré avec Id " + saveSerie.getIdSerie();
		modelMap.addAttribute("msg", msg);
		return "createSerie";
	}

	@RequestMapping("/supprimerSerie")
	public String supprimerSerie(@RequestParam Long id, ModelMap modelMap, @RequestParam (name="page",defaultValue = "0") int page,@RequestParam (name="size", defaultValue = "3") int size) {
		serieService.deleteSerieById(id);
		Page<Serie> series = serieService.getAllSeriesParPage(page, size);
		modelMap.addAttribute("series", series);
		modelMap.addAttribute("pages", new int[series.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeSeries";
	}

	@RequestMapping("/modifierSerie")
	public String editerSerie(@RequestParam Long id, ModelMap modelMap) {
		Serie p = serieService.getSerie(id);
		modelMap.addAttribute("serie", p);
		return "editerSerie";
	}

	@RequestMapping("/updateSerie")
	public String updateProduit(@ModelAttribute("produit") Serie serie, @RequestParam String date, ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateSortie = dateformat.parse(String.valueOf(date));
		serie.setDateSortie(dateSortie);

		serieService.updateSerie(serie);
		List<Serie> series = serieService.getAllSeries();
		modelMap.addAttribute("series", series);
		return "listeSeries";
	}
}

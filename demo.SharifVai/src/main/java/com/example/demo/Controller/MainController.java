package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.LogText;
import com.example.demo.Model.Product;
import com.example.demo.Service.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
//@Controller
/*
 * @Slf4j
 * 
 * @RequiredArgsConstructor
 */
public class MainController {

	@Autowired
	private MainService mService;

	@GetMapping("/allProduct")
	public ResponseEntity<List<Product>> allProduct() {
		return ResponseEntity.ok(mService.listAll());
	}

	/*
	 * @RequestMapping("/") public String viewHomePage(Model model) { List<Product>
	 * listProduct = mService.listAll(); model.addAttribute("listProduct",
	 * listProduct); return "index"; }
	 */

	@RequestMapping("/")
	public ModelAndView sendIndex() {
		ModelAndView mv = new ModelAndView();
		LogText.log.info("Index.html Called");
		mv.setViewName("index.html");
		return mv;
	}

}

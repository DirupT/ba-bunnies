package com.lambdaschool.bunnies.controller;

import com.lambdaschool.bunnies.BunniesApplication;
import com.lambdaschool.bunnies.exception.ResourceNotFoundException;
import com.lambdaschool.bunnies.model.Bunny;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BunnyController
{
	// localhost:2019/bunny/allbunnies
	@GetMapping(value = "/allbunnies", produces = "application/json")
	public ResponseEntity<?> getAllBunnies()
	{
		return new ResponseEntity<>(BunniesApplication.ourBunnyList.bunnyList, HttpStatus.OK);
	}

	// localhost:2019/bunny/get/{id}
	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getBunnyDetail(@PathVariable long id) throws ResourceNotFoundException
	{
		Bunny rtnBunny = BunniesApplication.ourBunnyList.findBunny(b -> b.getId() == id);

		if(rtnBunny == null)
		{
			throw new ResourceNotFoundException("Bunny with id " + id + " not found");
		}

		return new ResponseEntity<>(rtnBunny, HttpStatus.OK);
	}

	// localhost:2019/bunny/bunnytable
	@GetMapping(value = "/bunnytable")
	public ModelAndView displayBunnyTable()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bunnies");
		mav.addObject("bunnyList", BunniesApplication.ourBunnyList.bunnyList);

		return mav;
	}
}

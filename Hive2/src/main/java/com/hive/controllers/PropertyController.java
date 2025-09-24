package com.hive.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hive.models.Property;
import com.hive.models.Room;
import com.hive.models.Amenity;
import com.hive.models.Owner;
import com.hive.services.PropertyService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;
	
	@GetMapping("/addRooms")
	public String addRooms(int id,HttpSession session) {
		if(session.getAttribute("owner")!=null) {
			session.setAttribute("id", id);
			return "addRoom";
		}
		return "OwnerLogin";
	}
	@GetMapping("/addRoomss")
	public String addRoomss(HttpSession session) {
		if(session.getAttribute("owner")!=null) {
			return "addRoom";
		}
		return "OwnerLogin";
	}
	@GetMapping("/addImages")
	public String addImages(int id,HttpSession session) {
		if(session.getAttribute("owner")!=null) {
			session.setAttribute("id", id);
			return "images";
		}
		return "OwnerLogin";
	}
	@GetMapping("/owner")
	public String owner(HttpSession session) {
		if(session.getAttribute("owner")!=null) {
			return "Owner";
		}
		return "OwnerLogin";
	}
	
	@GetMapping("/AllProperty")
	public String AllProperty(HttpSession session, ModelMap m) {
		try {
			Owner owner = (Owner) session.getAttribute("owner");
			if (owner != null) {
				List<Property> properties = propertyService.getPropertiesByOwner(owner.getEmail());
				if (properties != null) {
					m.addAttribute("properties", properties);
					return "AllProperty";
				}
			}
		} catch (Exception e) {
			// Log error in production
		}
		return "OwnerLogin";
	}
	
	@GetMapping("/viewRooms") 
	public String viewRooms(int id, HttpSession session, ModelMap m) {
		try {
			if (session.getAttribute("owner") != null) {
				List<Room> rooms = propertyService.getRoomsByProperty(id);
				if (rooms != null) {
					m.addAttribute("rooms", rooms);
					return "viewRooms";
				}
			}
		} catch (Exception e) {
			// Log error in production
		}
		return "OwnerLogin";
	}
	
	@PostMapping("/addProperty")
	public String addProperty(@Valid @ModelAttribute Property property, HttpSession session, ModelMap m) {
		try {
			Owner owner = (Owner) session.getAttribute("owner");
			if (owner != null) {
				Owner updatedOwner = propertyService.addProperty(property, owner);
				if (updatedOwner != null) {
					session.setAttribute("owner", updatedOwner);
					List<Property> properties = propertyService.getPropertiesByOwner(updatedOwner.getEmail());
					if (properties != null) {
						m.addAttribute("properties", properties);
						return "AllProperty";
					}
				}
			}
		} catch (Exception e) {
			// Log error in production
		}
		return "redirect:/property/owner";
	}
	
	@PostMapping("/addRoom")
	public String addRoom(int pid, @Valid @ModelAttribute Room room, @Valid @ModelAttribute Amenity amenity, 
	                     HttpSession session, ModelMap m) {
		try {
			Owner owner = propertyService.addRoom(pid, room, amenity);
			if (owner != null) {
				List<Property> properties = propertyService.getPropertiesByOwner(owner.getEmail());
				if (properties != null) {
					m.addAttribute("properties", properties);
				}
			}
		} catch (Exception e) {
			// Log error in production
		}
		return "redirect:/property/AllProperty";
	}
	
	@PostMapping("/uploadImages")
	public String uploadImages(MultipartFile[] images, int id, HttpSession session, ModelMap m) {
		try {
			if (propertyService.uploadPropertyImages(id, images)) {
				return AllProperty(session, m);
			}
		} catch (Exception e) {
			// Log error in production
		}
		return "redirect:/property/AllProperty";
	}
	
	@GetMapping("/getImage")
	public void getImage(@RequestParam int id, @RequestParam int n, HttpServletResponse response) throws IOException {
		try {
			List<byte[]> images = propertyService.getPropertyImages(id);
			byte[] image = null;
			
			if (images != null && images.size() >= n) {
				switch(n) {
					case 1: image = images.get(0); break;
					case 2: image = images.get(1); break;
					case 3: image = images.get(2); break;
					case 4: image = images.get(3); break;
				}
			}
			
			if (image == null || image.length == 0) {
				InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/images/h1.jpg");
				image = is.readAllBytes();
			}
			response.getOutputStream().write(image);
		} catch (Exception e) {
			// Return default image on error
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/images/h1.jpg");
			byte[] image = is.readAllBytes();
			response.getOutputStream().write(image);
		}
	}
	
	@GetMapping("/searchProperty")
	public String searchProperty(@RequestParam String name, ModelMap m) {
		try {
			List<Property> properties = propertyService.searchProperties(name);
			if (properties != null) {
				m.addAttribute("properties", properties);
			}
		} catch (Exception e) {
			// Log error in production
		}
		return "searchProperty";
	}
	
	@GetMapping("/viewdetails")
	public String viewdetails(@RequestParam int id, ModelMap m) {
		try {
			Property property = propertyService.getProperty(id);
			if (property != null) {
				m.addAttribute("property", property);
			}
		} catch (Exception e) {
			// Log error in production
		}
		return "viewDetail";
	}
	
	
}

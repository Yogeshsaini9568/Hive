package com.hive.services;

import com.hive.models.Owner;
import com.hive.models.Property;
import com.hive.models.Room;
import com.hive.models.Amenity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PropertyService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_BASE_URL = "http://localhost:2003/property";

    public Owner addProperty(Property property, Owner owner) {
        try {
            // Ensure owner is set properly
            if (property.getOwnerEmail() == null) {
                property.setOwnerEmail(owner.getEmail());
            }
            
            String apiUrl = API_BASE_URL + "/addProperty";
            HttpEntity<Property> requestEntity = new HttpEntity<>(property);
            ResponseEntity<Owner> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, Owner.class);
            return response.getBody();
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public Property getProperty(int id) {
        try {
            String apiUrl = API_BASE_URL + "/getProperty/" + id;
            return restTemplate.getForObject(apiUrl, Property.class);
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }
    
    public Owner getOwner(String ownerEmail) {
        try {
            String apiUrl = "http://localhost:2003/owner/getOwner/" + ownerEmail;
            return restTemplate.getForObject(apiUrl, Owner.class);
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public List<Property> getPropertiesByOwner(String ownerEmail) {
        try {
            String apiUrl = API_BASE_URL + "/getProperties/" + ownerEmail;
            ResponseEntity<List<Property>> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Property>>() {});
            return response.getBody();
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public Owner addRoom(int propertyId, Room room, Amenity amenity) {
        try {
            // Get the property first
            Property property = getProperty(propertyId);
            if (property == null) {
                return null;
            }
            
            room.setProperty(property);
            room.setAmenity(amenity);
            
            String apiUrl = API_BASE_URL + "/addRoom";
            HttpEntity<Room> requestEntity = new HttpEntity<>(room);
            ResponseEntity<Owner> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, Owner.class);
            return response.getBody();
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public List<Room> getRoomsByProperty(int propertyId) {
        try {
            String apiUrl = API_BASE_URL + "/viewRooms/" + propertyId;
            ResponseEntity<List<Room>> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {});
            return response.getBody();
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public boolean uploadPropertyImages(int propertyId, MultipartFile[] images) {
        try {
            Property property = getProperty(propertyId);
            if (property == null) {
                return false;
            }
            // Set images
            if (images.length > 0) property.setImg1(images[0].getBytes());
            if (images.length > 1) property.setImg2(images[1].getBytes());
            if (images.length > 2) property.setImg3(images[2].getBytes());
            if (images.length > 3) property.setImg4(images[3].getBytes());
            
            String apiUrl = API_BASE_URL + "/uploadImages";
            HttpEntity<Property> requestEntity = new HttpEntity<>(property);
            ResponseEntity<Boolean> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, Boolean.class);
            
            return response.getBody() != null && response.getBody();
        } catch (IOException e) {
            // Log the error in production
            return false;
        }
    }

    public List<byte[]> getPropertyImages(int propertyId) {
        try {
            String apiUrl = API_BASE_URL + "/getImage/" + propertyId;
            ResponseEntity<List<byte[]>> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<byte[]>>() {});
            return response.getBody();
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }

    public List<Property> searchProperties(String name) {
        try {
            String apiUrl = API_BASE_URL + "/searchProperty/" + name;
            ResponseEntity<List<Property>> response = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Property>>() {});
            return response.getBody();
        } catch (Exception e) {
            // Log the error in production
            return null;
        }
    }
} 
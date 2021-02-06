package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.enteties.Cour;
import com.ae.gestion_etudiants.enteties.ElementModule;
import com.ae.gestion_etudiants.services.CourService;
import com.ae.gestion_etudiants.services.ElementModuleService;
import com.ae.gestion_etudiants.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/cour")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourController {
    private CourService courService;
    private ElementModuleService elementModuleService;
    private FileStorageService fileStorageService;

    @Autowired
    public CourController(CourService courService, ElementModuleService elementModuleService, FileStorageService fileStorageService) {
        this.courService = courService;
        this.elementModuleService = elementModuleService;
        this.fileStorageService = fileStorageService;
    }


    @PostMapping(value = "/")
    public ResponseEntity<Cour> ajouCour(@RequestParam("titreCour") String titreCour, @RequestParam("nbrHeure") Integer nbrHeure,
                                         @RequestParam("elementId") Long elementId, @RequestParam("contenue") MultipartFile contenue) {
        System.out.println("-- body -- " + titreCour + " " + nbrHeure + " " + elementId);
        try {
            String fileName = fileStorageService.storeFile(contenue);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/cour/downloadFile/")
                    .path(fileName)
                    .toUriString();

            ElementModule elementModule = this.elementModuleService.gElementModule(elementId);
            Cour cour = new Cour();
            cour.setTitreCour(titreCour);
            cour.setNbrHeure(nbrHeure);
            cour.setContenue(fileDownloadUri);
            cour.setElementModule(elementModule);
            System.out.println("cour --> " + cour);
            Cour newCour = this.courService.ajouterCour(cour);
            return ResponseEntity.ok()
                    .body(newCour);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @PutMapping(path = "{id}")
    public Cour mofiCour(@PathVariable("id") Long id, @Valid @RequestBody Cour c) throws Exception {
        Cour cour = this.mofiCour(id, c);
        return cour;
    }

    @GetMapping(path = "/byTitre")
    public List<Cour> getCoursParTitre(@RequestBody String titre) {
        List<Cour> list = this.courService.getCourParTitre(titre);
        return list;
    }

    @GetMapping(value = "/")
    public List<Cour> getCoursByQuery(@RequestParam("titre") String titre) {
        List<Cour> cours = this.courService.recherCourByQuery(titre);
        return cours;
    }

    @DeleteMapping(value = "{id}")
    public Cour suprimerCour(@PathVariable("id") Long param) throws Exception {
        Cour cour = this.courService.deleteCour(param);
        return cour;
    }

}

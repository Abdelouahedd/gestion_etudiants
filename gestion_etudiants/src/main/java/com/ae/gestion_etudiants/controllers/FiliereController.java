package com.ae.gestion_etudiants.controllers;

import com.ae.gestion_etudiants.enteties.Filiere;
import com.ae.gestion_etudiants.services.FileStorageService;
import com.ae.gestion_etudiants.services.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
@RequestMapping(path = "/api/filiere")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class FiliereController {
    private FiliereService filiereService;
    private FileStorageService fileStorageService;

    @Autowired
    public FiliereController(FiliereService filiereService, FileStorageService fileStorageService) {
        this.filiereService = filiereService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Filiere> creerFiliere(
            @RequestParam("nomFormation") String nomFormation,
            @RequestParam("description") MultipartFile description
    ) throws Exception {
        try {
            String fileName = fileStorageService.storeFile(description);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/filiere/downloadFile/")
                    .path(fileName)
                    .toUriString();
            Filiere filiere = new Filiere();
            filiere.setNomFormation(nomFormation);
            filiere.setDescription(fileDownloadUri);
            Filiere flr = this.filiereService.ajouFiliere(filiere);
            return ResponseEntity.ok()
                    .body(flr);
        } catch (Exception e) {
            throw new Exception("Filiere not added try again");
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

    @GetMapping(params = "{id}")
    public Filiere getFiliere(@PathVariable("id") Long id) throws Exception {
        Filiere flr = this.filiereService.getFiliere(id);
        return flr;
    }

    @GetMapping(path = "/list")
    public List<Filiere> getFilieres() throws Exception {
        List<Filiere> listFiliere = this.filiereService.getFilieres();
        return listFiliere;
    }

    @PutMapping(path = "{id}")
    public Filiere modiFiliere(@PathVariable("id") Long id, @Valid @RequestBody Filiere entity) throws Exception {
        Filiere flr = this.filiereService.updateFiliere(id, entity);
        return flr;
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> supprimerFiliere(@PathVariable Long id) throws Exception {
        try {
            this.filiereService.supprimerFiliere(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }
    }

}

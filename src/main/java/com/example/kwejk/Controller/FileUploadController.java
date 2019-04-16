package com.example.kwejk.Controller;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.kwejk.model.Gif;
import com.example.kwejk.services.GifService;
import com.example.kwejk.upload.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.kwejk.upload.StorageFileNotFoundException;

@Slf4j
@Controller
public class FileUploadController {

    private final StorageService storageService;
    private final GifService gifService;

    @Autowired
    public FileUploadController(StorageService storageService, GifService gifService) {
        this.storageService = storageService;
        this.gifService = gifService;
    }

    @GetMapping("/uploadForm")
    public String listUploadedFiles(Model model) throws IOException {

        String ss = storageService.loadAll()
                .map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName())
                        .toString()).toString();

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName()
                                .toString())
                        .build()
                        .toString())
                .collect(Collectors.toList()));

        log.info("file was uploaded.");

//        gifService.save(storageService.load(file.getOriginalFilename()).toString(), 1);

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadForm")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) {

        log.error("file was uploaded4345534345345.");
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

       gifService.getGifLinksDatabase();
//        storageService.loadAll();
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }


}

package ru.recipe.recipeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.FilesService;

import java.io.*;

@RestController
@RequestMapping("/IngredientFiles")
@Tag(name = "Ингридиенты(работа с файлами)", description = "Input/Output и другие эндпоинты для работы с файлами Ингридиентов")
public class FilesIngredientController {



    private final FilesService filesService;

    public FilesIngredientController(@Qualifier("filesServiceIngredientImpl") FilesService filesService) {
        this.filesService = filesService;
    }



    @GetMapping(value = "/export")
    @Operation(summary = "Скачать файл ингридиентов",description = "Возможность скачать файл со всеми ингридиентами")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл с ингридиентами скачан",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            )
    })
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = filesService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_JSON).
                    contentLength(file.length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"IngredientFile.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл ингридиентов",description = "Возможность загрузить файл с ингридиентами")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл с ингридиентами загружен",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            )
    })
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        filesService.cleanDataFile();
        File dataFile = filesService.getDataFile();

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

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
import ru.recipe.recipeapp.service.RecipeService;

import java.io.*;

@RestController
@RequestMapping("/recipeFiles")
@Tag(name = "Рецепты(работа с файлами)", description = "Input/Output и другие эндпоинты для работы с файлами Рецептов")
public class FilesRecipeController {


    private final FilesService<Recipe> filesService;
    private final RecipeService recipeService;


    public FilesRecipeController(@Qualifier("filesServiceRecipeImpl") FilesService<Recipe> filesService, RecipeService recipeService) {
        this.filesService = filesService;
        this.recipeService = recipeService;
    }


    @GetMapping(value = "/export")
    @Operation(summary = "Скачать файл со всеми рецептами в формате json", description = "Возможность скачать файл со всеми рецептами(файл json)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл с рецептами скачан",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            )
    })
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {                        //Эндпоинт "Выгрузка файла в формате json/Возможность скачать файл рецептов в web"
        File file = filesService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_JSON).
                    contentLength(file.length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"RecipeFile.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл рецептов", description = "Возможность загрузить файл с рецептами")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл с рецептами загружен",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            )
    })
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {                                      //Эндпоинт "Загрузка файла/Возможность прочитать файл и сохранить рецепт"
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



    @GetMapping(value = "exportTxt")                                          //Эндпоинт "Выгрузка файла в формате txt/Возможность скачать файл рецептов в web"
    @Operation(summary = "Скачать файл со всеми рецептами в формате txt", description = "Возможность скачать файл со всеми рецептами(файл txt)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл с рецептами скачан",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            )
    })
    public ResponseEntity<byte[]> exportTxt() {
        byte[] bytes = filesService.exportTxt(recipeService.getAll());

        if (bytes == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"RecipeFileInfo.txt\"")
                .body(bytes);


    }


}

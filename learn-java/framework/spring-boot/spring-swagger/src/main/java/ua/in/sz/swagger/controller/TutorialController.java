package ua.in.sz.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "Tutorial", description = "Tutorial management APIs")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Operation(summary = "Retrieve all Tutorials", tags = {"Tutorial"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tutorial.class)
                    )
            })
    })
    @GetMapping
    public ResponseEntity<List<Tutorial>> getTutorials(
            @Parameter(example = "Title-1",
                    schema = @Schema(implementation = Title.class))
            @RequestParam
            String title,
            @Parameter(name = "parameters", required = true,
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "object"))},
                    example = """
                            {
                              "prop1": "PROP-1",
                              "prop2": true,
                              "prop3": 123
                            }
                            """
            )
            @RequestParam(name = "parameters")
            Map<String, Object> parameters
    ) {

        log.info("Title: {}, parameters: {}", title, parameters);

        List<Tutorial> result = new ArrayList<>();

        result.add(Tutorial.builder().title("T-1").description("D-1").build());
        result.add(Tutorial.builder().title("T-2").description("D-2").build());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

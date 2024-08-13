package ua.in.sz.swagger.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Tutorial {
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private long id;
  private String title;
  private String description;
}

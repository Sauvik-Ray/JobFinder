package com.JobFinder.JobFinder.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private Long id;
    private String title;
    private String link;
    private String descriptionSnippet;
    private String description;
    private String guid;
    private String pubDate;
    private String category;
    private Integer salaryMin;
    private Integer salaryMax;
}

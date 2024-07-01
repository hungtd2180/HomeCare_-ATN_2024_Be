package com.example.homecare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentObject {
    private String content;
    private String userId;
    private String collaboratorId;
}

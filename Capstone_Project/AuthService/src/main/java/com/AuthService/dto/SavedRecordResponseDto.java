package com.AuthService.dto;

import com.AuthService.entity.UserDE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedRecordResponseDto {
    private UserDE savedRecord;

    private ResponseDto response;
}

package org.zerock.finedu.common.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OpenAIRequest {
    private String prompt;
    @Builder
    OpenAIRequest(String prompt) {
        this.prompt = prompt;
    }
}

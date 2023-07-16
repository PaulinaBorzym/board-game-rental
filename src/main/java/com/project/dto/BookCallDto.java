package com.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class BookCallDto {
    private Long bookCallId;
    private LocalDate bookDate;
    private String phoneNumber;
    private String title;

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder {
        private BookCallDto bookCallDto = new BookCallDto();

        public Builder bookCallId(Long bookCallId){
            bookCallDto.bookCallId = bookCallId;
            return this;
        }

        public Builder bookDate(LocalDate bookDate){
            bookCallDto.bookDate = bookDate;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            bookCallDto.phoneNumber = phoneNumber;
            return this;
        }

        public Builder title(String title){
            bookCallDto.title = title;
            return this;
        }
        public BookCallDto build(){
            return bookCallDto;
        }
    }
}

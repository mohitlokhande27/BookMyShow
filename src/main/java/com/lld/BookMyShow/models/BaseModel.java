package com.lld.BookMyShow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @Data = @Getter + @Setter + @AllArgsConstructor +  @NoArgsConstructor
// @Entity Table for BaseModel is not required, just its attributes needs to be percolated in child
// class db table, instead use @MappedSuperclass
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // id will auto increment
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
}

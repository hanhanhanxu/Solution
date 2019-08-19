package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "testemoji")
public class Testemoji {
    @Id
    @KeySql(useGeneratedKeys = true)//自动增长
    private Integer id;

    private String name;

    private String emoji;
}
package org.novastart.admin.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
//在开发中，所有的pojo类都会序列化
public class User3 implements Serializable {
    private String name;
    private int age;
}
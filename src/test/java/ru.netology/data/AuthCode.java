package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthCode {
    private String id;            //    id      CHAR(36) PRIMARY KEY,
    private String user_id;       //    user_id CHAR(36)   NOT NULL,
    private String code;          //    code    VARCHAR(6) NOT NULL,
    private String created;       //    created TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
}

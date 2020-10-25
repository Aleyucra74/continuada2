package com.mycompany.continuada2;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoBanco {
    
    public JdbcTemplate conexao() {
        
     BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./meubanco");
        
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
//        System.out.println(
//                jdbcTemplate.queryForList("select * from usuario")
//        );

        return jdbcTemplate;
        
    }    
        
}

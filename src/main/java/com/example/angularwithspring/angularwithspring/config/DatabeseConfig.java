package com.example.angularwithspring.angularwithspring.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.example.angularwithspring.repository")
@EnableTransactionManagement
public class DatabeseConfig {
}

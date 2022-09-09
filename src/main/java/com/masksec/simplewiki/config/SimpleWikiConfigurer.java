package com.masksec.simplewiki.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration("simplewiki")
public class SimpleWikiConfigurer {

    @Value("${simplewiki.title}")
    public String title;

}

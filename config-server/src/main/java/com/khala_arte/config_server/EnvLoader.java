package com.khala_arte.config_server;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    public static void load() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("GIT_REPO_URL", dotenv.get("GIT_REPO_URL"));
        System.setProperty("GIT_USERNAME", dotenv.get("GIT_USERNAME"));
        System.setProperty("GIT_PASSWORD", dotenv.get("GIT_PASSWORD"));
    }
}

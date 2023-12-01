package com.noslimes.orebuff.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ModConfigManager {

    private static final String CONFIG_FOLDER_NAME = "OreBuff";
    private static final String CONFIG_FILE_NAME = "OreBuff_Config.json";
    private static ModConfig modConfig = new ModConfig();

    public static ModConfig getConfig(){
        return modConfig;
    }

    public static void loadConfig() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Path configPath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FOLDER_NAME);
            File configFile = configPath.resolve(CONFIG_FILE_NAME).toFile();

            if (configFile.exists()) {
                modConfig = mapper.readValue(configFile, ModConfig.class);
            } else {
                saveConfig(); // Create and save default config if it doesn't exist
            }
        } catch (IOException e) {
            handleConfigException(e, "Error loading configuration");
        }
    }

    public static void saveConfig() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Path configPath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_FOLDER_NAME);
            File configFolder = configPath.toFile();

            if (!configFolder.exists()) {
                configFolder.mkdirs();
            }

            File configFile = configPath.resolve(CONFIG_FILE_NAME).toFile();
            mapper.writerWithDefaultPrettyPrinter().writeValue(configFile, modConfig);
        } catch (IOException e) {
            handleConfigException(e, "Error saving configuration");
        }
    }

    private static void handleConfigException(IOException e, String errorMessage) {
        // Handle the exception according to your application's requirements
        e.printStackTrace();
        // Example: Log the error or notify the user
    }
}

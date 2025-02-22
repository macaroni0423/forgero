package com.sigmundgranaas.forgero.core.io;

import com.sigmundgranaas.forgero.core.identifier.texture.TextureIdentifier;
import com.sigmundgranaas.forgero.core.identifier.texture.toolpart.PaletteIdentifier;
import com.sigmundgranaas.forgero.core.identifier.texture.toolpart.TemplateTextureIdentifier;
import com.sigmundgranaas.forgero.resources.FabricModFileLoader;

import java.io.InputStream;

public class FileService {

    public static final String ASSETS_FOLDER_PATH = "assets/forgero/";
    public static final String TEXTURE_FOLDER_PATH = ASSETS_FOLDER_PATH + "textures/";
    public static String ITEM_TEXTURES_FOLDER_PATH = TEXTURE_FOLDER_PATH + "item/";
    public static String CONFIG_FOLDER_PATH = "config/";


    private String getTexturePath(TextureIdentifier id) {
        if (id instanceof TemplateTextureIdentifier texture) {
            return "assets/forgero/templates/textures/" + texture.skin() + "/" + id.getFileNameWithExtension();
        } else if (id instanceof PaletteIdentifier) {
            return "assets/forgero/templates/materials/" + id.getFileNameWithExtension();
        } else {
            return "";
        }
    }

    public InputStream getStream(TextureIdentifier id) {
        //ClassLoader classLoader = FileService.class.getClassLoader();

        var inputStream = new FabricModFileLoader().loadFileFromMods(getTexturePath(id));

        //InputStream resource = classLoader.getResourceAsStream(getTexturePath(id));
        if (inputStream.isEmpty()) {
            throw new IllegalArgumentException("file not found! " + getTexturePath(id));
        } else {
            return inputStream.get();
        }
    }
}

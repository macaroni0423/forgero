package com.sigmundgranaas.forgero.client;

import com.sigmundgranaas.forgero.ForgeroInitializer;
import com.sigmundgranaas.forgero.client.forgerotool.model.ForgeroModelVariantProvider;
import com.sigmundgranaas.forgero.client.forgerotool.model.UnbakedModelCollection;
import com.sigmundgranaas.forgero.client.texture.FabricTextureIdentifierFactory;
import com.sigmundgranaas.forgero.core.texture.ForgeroToolPartTextureRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class ForgeroClient implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(ForgeroInitializer.MOD_NAMESPACE);
    public static Set<ModelIdentifier> modelSet = new HashSet<>();

    @Override
    public void onInitializeClient() {
        initializeItemModels();
    }

    private void initializeItemModels() {
        registerToolPartTextures();
        ModelLoadingRegistry.INSTANCE.registerVariantProvider(variant -> new ForgeroModelVariantProvider(UnbakedModelCollection.INSTANCE));
    }

    private void registerToolPartTextures() {

        ForgeroToolPartTextureRegistry registry = ForgeroToolPartTextureRegistry.getInstance(new FabricTextureIdentifierFactory());
        registry.getTextures().forEach(texture -> {
            ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, atlasRegistry) -> atlasRegistry.register(new Identifier(ForgeroInitializer.MOD_NAMESPACE, "item/" + texture.getIdentifier())));

        });
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, atlasRegistry) -> atlasRegistry.register(new Identifier(ForgeroInitializer.MOD_NAMESPACE, "item/" + "transparent_base")));

    }
}

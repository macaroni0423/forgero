package com.sigmundgranaas.forgero.client.forgerotool.model.dynamicmodel.head;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sigmundgranaas.forgero.client.forgerotool.model.dynamicmodel.AbstractDynamicModel;
import com.sigmundgranaas.forgero.item.toolpart.ForgeroToolPartItemImpl;

public class ShovelHeadModel extends AbstractDynamicModel {
    private static final String ELEMENTS = "[{\"name\":\"shovel_head\",\"from\":[11,10,7.4],\"to\":[15,14,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[11,2,15,6],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[15,2,11,2.5],\"rotation\":90,\"texture\":\"#0\"},\"south\":{\"uv\":[11,6,15,2],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[11,5.5,15,6],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[14.5,2,15,6],\"rotation\":90,\"texture\":\"#0\"},\"down\":{\"uv\":[11,2,11.5,6],\"rotation\":90,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[11,9,7.4],\"to\":[14,10,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[10,3,11,7],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[11,3,10,3.5],\"rotation\":90,\"texture\":\"#0\"},\"south\":{\"uv\":[10,6,11,3],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[10,6.5,11,7],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[10.5,3,11,7],\"rotation\":90,\"texture\":\"#0\"},\"down\":{\"uv\":[10,3,10.5,7],\"rotation\":90,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[12,8,7.4],\"to\":[13,9,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[9,4,10,6],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[10,4,9,4.5],\"rotation\":90,\"texture\":\"#0\"},\"south\":{\"uv\":[9,5,10,4],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[9,5.5,10,6],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[9.5,4,10,6],\"rotation\":90,\"texture\":\"#0\"},\"down\":{\"uv\":[9,4,9.5,6],\"rotation\":90,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[11,7,7.4],\"to\":[12,8,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[8,5,9,6],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[9,5,8,5.5],\"rotation\":90,\"texture\":\"#0\"},\"south\":{\"uv\":[8,6,9,5],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[8,5.5,9,6],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[8.5,5,9,6],\"rotation\":90,\"texture\":\"#0\"},\"down\":{\"uv\":[8,5,8.5,6],\"rotation\":90,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[10,10,7.4],\"to\":[11,12,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[11,6,13,8],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[13,6,11,6.5],\"rotation\":90,\"texture\":\"#0\"},\"south\":{\"uv\":[11,7,13,6],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[11,7.5,13,8],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[12.5,6,13,8],\"rotation\":90,\"texture\":\"#0\"},\"down\":{\"uv\":[11,6,11.5,8],\"rotation\":90,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[9,11,7.4],\"to\":[10,12,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"south\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"down\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[10,12,7.4],\"to\":[11,13,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[13,6,14,7],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[14,6,13,6.5],\"rotation\":90,\"texture\":\"#0\"},\"south\":{\"uv\":[13,7,14,6],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[13,6.5,14,7],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[13.5,6,14,7],\"rotation\":90,\"texture\":\"#0\"},\"down\":{\"uv\":[13,6,13.5,7],\"rotation\":90,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[8,10,7.4],\"to\":[9,11,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[11,8,12,9],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[12,8,11,8.5],\"rotation\":90,\"texture\":\"#0\"},\"south\":{\"uv\":[11,9,12,8],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[11,8.5,12,9],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[11.5,8,12,9],\"rotation\":90,\"texture\":\"#0\"},\"down\":{\"uv\":[11,8,11.5,9],\"rotation\":90,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[11,8,7.4],\"to\":[12,9,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"south\":{\"uv\":[11,7,12,8],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"down\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[10,9,7.4],\"to\":[11,10,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"south\":{\"uv\":[10,6,11,7],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"down\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"}}},{\"name\":\"shovel_head\",\"from\":[9,10,7.4],\"to\":[10,11,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"south\":{\"uv\":[11,7,12,8],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"},\"down\":{\"uv\":[12,7,13,8],\"rotation\":270,\"texture\":\"#0\"}}},{\"name\":\"shovel_head_binding\",\"from\":[11,10,7.4],\"to\":[15,14,8.6],\"rotation\":{\"angle\":0,\"axis\":\"y\",\"origin\":[11.21429,10.5,5.92857]},\"faces\":{\"north\":{\"uv\":[11,2,15,6],\"rotation\":270,\"texture\":\"#0\"},\"east\":{\"uv\":[15,2,11,2.5],\"rotation\":90,\"texture\":\"#0\"},\"south\":{\"uv\":[11,6,15,2],\"rotation\":270,\"texture\":\"#0\"},\"west\":{\"uv\":[11,5.5,15,6],\"rotation\":270,\"texture\":\"#0\"},\"up\":{\"uv\":[14.5,2,15,6],\"rotation\":90,\"texture\":\"#0\"},\"down\":{\"uv\":[11,2,11.5,6],\"rotation\":90,\"texture\":\"#0\"}}}]";

    public ShovelHeadModel(ForgeroToolPartItemImpl toolpart) {
        super(toolpart);
    }

    @Override
    protected JsonArray getElements() {
        return (JsonArray) new JsonParser().parse(ELEMENTS);
    }

    @Override
    protected JsonObject getTextures() {
        JsonObject textures = new JsonObject();
        String texture = super.getTexture();
        textures.addProperty("0", texture);
        textures.addProperty("particle", texture);
        return textures;
    }
}

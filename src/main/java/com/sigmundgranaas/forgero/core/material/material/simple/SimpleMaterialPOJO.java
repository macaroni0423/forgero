package com.sigmundgranaas.forgero.core.material.material.simple;

import com.sigmundgranaas.forgero.core.material.material.MaterialType;
import com.sigmundgranaas.forgero.core.property.PropertyPOJO;

import java.util.List;

public class SimpleMaterialPOJO {
    public String name;
    public int rarity;
    public MaterialType type;
    public PropertyPOJO properties;
    public Ingredient ingredient;
    public Palette palette;
    public Primary primary;
    public Secondary secondary;

    public static SimpleMaterialPOJO createDefaultMaterialPOJO() {
        SimpleMaterialPOJO pojo = new SimpleMaterialPOJO();
        pojo.name = "Default";
        pojo.rarity = 1;
        pojo.type = MaterialType.METAL;
        pojo.properties = new PropertyPOJO();
        Palette palette = new Palette();
        palette.include = List.of();
        palette.exclude = List.of();
        pojo.palette = palette;
        pojo.primary = new Primary();
        pojo.secondary = new Secondary();

        Ingredient ingredient = new Ingredient();
        ingredient.item = "ingredient";
        pojo.ingredient = ingredient;
        return pojo;
    }

    public static class Palette {
        public String name;
        public List<String> include;
        public List<String> exclude;
    }

    public static class Ingredient {
        public String item;
    }

    public static class Primary {
        public PropertyPOJO properties;
    }

    public static class Secondary {
        public PropertyPOJO properties;
    }
}

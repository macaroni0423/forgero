package com.sigmundgranaas.forgero.core.tool.factory;

import com.sigmundgranaas.forgero.core.tool.ForgeroTool;
import com.sigmundgranaas.forgero.core.toolpart.toolpart.HandleTest;
import com.sigmundgranaas.forgero.core.toolpart.toolpart.PickaxeHeadTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ForgeroToolBuilderImplTest {


    @Test
    void addGem() {
        //TODO()
    }

    @Test
    void addSkin() {
        //TODO()
    }

    @Test
    void createBaseTool() {
        ForgeroTool tool = ForgeroToolBuilder.createBuilder(PickaxeHeadTest.createDefaultPickaxeHead(), HandleTest.createDefaultToolPartHandle()).createTool();
        Assertions.assertEquals("iron_pickaxe", tool.getToolIdentifierString());
    }
}
package com.sigmundgranaas.forgero.core.property;

import com.sigmundgranaas.forgero.core.tool.ForgeroTool;
import com.sigmundgranaas.forgero.core.tool.factory.ForgeroToolFactory;
import com.sigmundgranaas.forgero.core.toolpart.factory.ToolPartBuilder;
import com.sigmundgranaas.forgero.core.toolpart.factory.ToolPartHandleBuilder;
import com.sigmundgranaas.forgero.core.toolpart.factory.ToolPartHeadBuilder;
import com.sigmundgranaas.forgero.core.toolpart.handle.ToolPartHandle;
import com.sigmundgranaas.forgero.core.toolpart.head.ToolPartHead;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.sigmundgranaas.forgero.core.property.ToolPropertyTest.OAK_PRIMARY;
import static com.sigmundgranaas.forgero.core.property.ToolPropertyTest.PICKAXEHEAD_SCHEMATIC;

public class WoodenToolPropertyTest {

    @Test
    void testToolDurability() {
        ToolPartBuilder headBuilder = new ToolPartHeadBuilder(OAK_PRIMARY.get(), PICKAXEHEAD_SCHEMATIC.get());
        //headBuilder.setSecondary(DIAMOND_SECONDARY.get());

        ToolPartBuilder handleBuilder = new ToolPartHandleBuilder(OAK_PRIMARY.get(), PICKAXEHEAD_SCHEMATIC.get());
        //handleBuilder.setSecondary(DIAMOND_SECONDARY.get());

        ForgeroTool exampleTool = ForgeroToolFactory.INSTANCE.createForgeroTool((ToolPartHead) headBuilder.createToolPart(), (ToolPartHandle) handleBuilder.createToolPart());

        Assertions.assertEquals(30, exampleTool.getPropertyStream().applyAttribute(Target.createEmptyTarget(), AttributeType.DURABILITY), 20);
    }

    @Test
    void testToolMiningLevel() {
        ToolPartBuilder headBuilder = new ToolPartHeadBuilder(OAK_PRIMARY.get(), PICKAXEHEAD_SCHEMATIC.get());
        //headBuilder.setSecondary(DIAMOND_SECONDARY.get());

        ToolPartBuilder handleBuilder = new ToolPartHandleBuilder(OAK_PRIMARY.get(), PICKAXEHEAD_SCHEMATIC.get());
        //handleBuilder.setSecondary(DIAMOND_SECONDARY.get());

        ForgeroTool exampleTool = ForgeroToolFactory.INSTANCE.createForgeroTool((ToolPartHead) headBuilder.createToolPart(), (ToolPartHandle) handleBuilder.createToolPart());

        Assertions.assertEquals(0, exampleTool.getPropertyStream().applyAttribute(Target.createEmptyTarget(), AttributeType.MINING_LEVEL));
    }

    @Test
    void testToolMiningSpeed() {
        ToolPartBuilder headBuilder = new ToolPartHeadBuilder(OAK_PRIMARY.get(), PICKAXEHEAD_SCHEMATIC.get());
        //headBuilder.setSecondary(DIAMOND_SECONDARY.get());

        ToolPartBuilder handleBuilder = new ToolPartHandleBuilder(OAK_PRIMARY.get(), PICKAXEHEAD_SCHEMATIC.get());
        //handleBuilder.setSecondary(DIAMOND_SECONDARY.get());

        ForgeroTool exampleTool = ForgeroToolFactory.INSTANCE.createForgeroTool((ToolPartHead) headBuilder.createToolPart(), (ToolPartHandle) handleBuilder.createToolPart());

        Assertions.assertEquals(2, exampleTool.getPropertyStream().applyAttribute(Target.createEmptyTarget(), AttributeType.MINING_SPEED), 1);
    }
}

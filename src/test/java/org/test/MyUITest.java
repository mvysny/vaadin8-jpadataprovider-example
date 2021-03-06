package org.test;

import com.github.mvysny.kaributesting.v8.MockVaadin;
import com.vaadin.ui.Grid;
import org.junit.jupiter.api.*;

import static com.github.mvysny.kaributesting.v8.GridKt.expectRow;
import static com.github.mvysny.kaributesting.v8.GridKt.expectRows;
import static com.github.mvysny.kaributesting.v8.LocatorJ._get;

/**
 * Look Ma, no Spring/JavaEE/Servlet container necessary! That's what you get when you keep things simple.
 * @author mavi
 */
public class MyUITest {
    @BeforeAll
    public static void mockContainer() {
        new Bootstrap().contextInitialized(null);
    }

    @AfterAll
    public static void stopMockedContainer() {
        new Bootstrap().contextDestroyed(null);
    }

    @BeforeEach
    public void mockVaadin() {
        MockVaadin.setup(MyUI::new);
    }

    @AfterEach
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void testGridHas100Rows() {
        final Grid grid = _get(Grid.class);
        expectRows(grid, 100);
        expectRow(grid, 0, "Person 0", "1", "10");
    }
}

import org.example.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractGraphTest<T extends Graph> {

    protected T instance;

    protected abstract T createInstance(int size);

    @BeforeEach
    public void setUp() {
        instance = createInstance(3);
    }

    @Test
    public void testEqualsAndHashCode() {
        T g1 = createInstance(3);
        T g2 = createInstance(3);

        g1.addEdge(0, 1);
        g2.addEdge(0, 1);

        assertTrue(g1.equals(g2));
        assertEquals(g1.hashCode(), g2.hashCode());

        g2.addEdge(1, 2);
        assertFalse(g1.equals(g2));
        assertNotEquals(g1.hashCode(), g2.hashCode());
    }
}

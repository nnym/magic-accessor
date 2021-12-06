import net.auoeke.magic.Definer;
import net.auoeke.magic.MagicAccessor;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@Testable
public class Tests {
    private static final boolean canUpdateFinal = Runtime.version().major() <= 16;
    private static final Integer staticFinal = 0;
    private static Integer staticNonfinal = 0;

    @Test
    void test() {
        var accessor = MagicAccessor.access(Tests.class, "staticNonfinal");
        accessor.set(1);
        assert staticNonfinal == 1 && accessor.getInt() == 1;

        if (canUpdateFinal) {
            accessor = MagicAccessor.access(Tests.class, "staticFinal");
            accessor.set(2);
            assert staticFinal == 2 && accessor.getInt() == 2;
        }
    }

    static {
        Definer.define();
    }
}

package net.auoeke.magic;

import java.lang.invoke.MethodHandles;
import java.security.ProtectionDomain;
import net.gudenau.lib.unsafe.Unsafe;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class Definer {
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static boolean defined;

    public static void define() {
        try {
            if (!defined) {
                var pkg = Definer.class.getPackageName().replace('.', '/');
                var node = new ClassNode();
                node.visit(Opcodes.V1_8, 0, pkg + "/internal MagicAccessor", null, "jdk/internal/reflect/MagicAccessorImpl", null);
                var writer = new ClassWriter(0);
                node.accept(writer);
                define(node.name, writer.toByteArray(), Class.forName("jdk.internal.reflect.MagicAccessorImpl").getClassLoader(), Definer.class.getProtectionDomain());

                new ClassReader(Definer.class.getClassLoader().getResourceAsStream(pkg + "/MagicAccessor.class").readAllBytes()).accept(node, 0);
                node.superName = pkg + "/internal MagicAccessor";
                node.accept(writer);
                define(writer.toByteArray(), false);

                defined = true;
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    static Class define(byte[] bytecode, boolean hidden) {
        try {
            return hidden ? lookup.defineHiddenClass(bytecode, false).lookupClass() : lookup.defineClass(bytecode);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?> define(String name, byte[] b, ClassLoader cl, ProtectionDomain pd) {
        return Unsafe.defineClass(name.replace('/', '.'), b, 0, b.length, cl, pd);
    }
}

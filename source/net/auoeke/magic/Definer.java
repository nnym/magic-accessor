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
        if (!defined) {
            var pkg = Definer.class.getPackageName().replace('.', '/');
            var node = new ClassNode();
            node.visit(Opcodes.V1_8, 0, pkg + "/internal MagicAccessor", null, "jdk/internal/reflect/MagicAccessorImpl", null);
            var writer = new ClassWriter(0);
            node.accept(writer);
            define(writer.toByteArray(), Class.forName("jdk.internal.reflect.MagicAccessorImpl").getClassLoader(), Definer.class.getProtectionDomain());

            try (var magicAccessor = Definer.class.getClassLoader().getResourceAsStream(pkg + "/MagicAccessor.class")) {
                new ClassReader(magicAccessor.readAllBytes()).accept(node, 0);
            }

            node.superName = pkg + "/internal MagicAccessor";
            node.accept(writer);
            define(writer.toByteArray(), false);

            defined = true;
        }
    }

    @SuppressWarnings("rawtypes")
    static Class define(byte[] bytecode, boolean hidden) {
        return hidden ? lookup.defineHiddenClass(bytecode, false).lookupClass() : lookup.defineClass(bytecode);
    }

    private static Class<?> define(byte[] classFile, ClassLoader loader, ProtectionDomain domain) {
        return Unsafe.defineClass(null, classFile, 0, classFile.length, loader, domain);
    }
}

package net.auoeke.magic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import net.gudenau.lib.unsafe.Unsafe;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

@SuppressWarnings({"unused", "rawtypes"})
public abstract class MagicAccessor {
    private static final Map<Class, Map<String, MagicAccessor>> fields = new HashMap<>();

    MagicAccessor() {}

    public static MagicAccessor access(Field field) {
        return fields(field.getDeclaringClass()).computeIfAbsent(field.getName(), name -> compute(field.getDeclaringClass(), name, field));
    }

    public static MagicAccessor access(Class type, String field) {
        return fields(type).computeIfAbsent(field, field1 -> {
            try {
                return compute(type, field1, type.getDeclaredField(field1));
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public abstract boolean getBoolean(Object object);

    public abstract byte getByte(Object object);

    public abstract char getChar(Object object);

    public abstract short getShort(Object object);

    public abstract int getInt(Object object);

    public abstract long getLong(Object object);

    public abstract float getFloat(Object object);

    public abstract double getDouble(Object object);

    public abstract <T> T get(Object object);

    public abstract void setBoolean(Object object, boolean value);

    public abstract void setByte(Object object, byte value);

    public abstract void setChar(Object object, char value);

    public abstract void setShort(Object object, short value);

    public abstract void setInt(Object object, int value);

    public abstract void setLong(Object object, long value);

    public abstract void setFloat(Object object, float value);

    public abstract void setDouble(Object object, double value);

    public abstract void set(Object object, Object value);

    public final boolean getBoolean() {
        return this.getBoolean(null);
    }

    public final byte getByte() {
        return this.getByte(null);
    }

    public final char getChar() {
        return this.getChar(null);
    }

    public final short getShort() {
        return this.getShort(null);
    }

    public final int getInt() {
        return this.getInt(null);
    }

    public final long getLong() {
        return this.getLong(null);
    }

    public final float getFloat() {
        return this.getFloat(null);
    }

    public final double getDouble() {
        return this.getDouble(null);
    }

    public final <T> T get() {
        return this.get(null);
    }

    public final void setBoolean(boolean b) {
        this.setBoolean(null, b);
    }

    public final void setByte(byte b) {
        this.setByte(null, b);
    }

    public final void setChar(char c) {
        this.setChar(null, c);
    }

    public final void setShort(short s) {
        this.setShort(null, s);
    }

    public final void setInt(int i) {
        this.setInt(null, i);
    }

    public final void setLong(long l) {
        this.setLong(null, l);
    }

    public final void setFloat(float f) {
        this.setFloat(null, f);
    }

    public final void setDouble(double d) {
        this.setDouble(null, d);
    }

    public final void set(Object p) {
        this.set(null, p);
    }

    private static Map<String, MagicAccessor> fields(Class c) {
        return fields.computeIfAbsent(c, c1 -> new HashMap<>());
    }

    private static MagicAccessor compute(Class c, String f, Field f1) {
        var t = f1.getType();
        Class sc;

        if (t.isPrimitive()) {
            if (t == boolean.class) sc = BooleanAccessor.class;
            else if (t == byte.class) sc = ByteAccessor.class;
            else if (t == char.class) sc = CharAccessor.class;
            else if (t == short.class) sc = ShortAccessor.class;
            else if (t == int.class) sc = IntAccessor.class;
            else if (t == long.class) sc = LongAccessor.class;
            else if (t == float.class) sc = FloatAccessor.class;
            else if (t == double.class) sc = DoubleAccessor.class;
            else throw new AssertionError();
        } else {
            sc = ReferenceAccessor.class;
        }

        var scn = sc.getName().replace('.', '/');
        var n = new ClassNode();
        n.visit(Opcodes.V1_8, 0, String.join("$", scn, c.getName().replace('.', '_'), f), null, scn, null);

        String mn;

        if (t.isPrimitive()) {
            mn = t.getName();
            mn = Character.toUpperCase(mn.charAt(0)) + mn.substring(1);
        } else {
            mn = "";
        }

        var t1 = Type.getType(t);
        var td = t1.getDescriptor();
        var g = n.visitMethod(0, "get" + mn, "(Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        var s = (MethodNode) n.visitMethod(0, "set" + mn, "(Ljava/lang/Object;%s)V".formatted(t.isPrimitive() ? td : "Ljava/lang/Object;"), null, null);
        var cn = c.getName().replace('.', '/');
        int go;
        int po;

        if (Modifier.isStatic(f1.getModifiers())) {
            po = Opcodes.PUTSTATIC;
            go = Opcodes.GETSTATIC;
        } else {
            po = Opcodes.PUTFIELD;
            go = Opcodes.GETFIELD;

            g.visitVarInsn(Opcodes.ALOAD, 1);
            s.visitVarInsn(Opcodes.ALOAD, 1);
        }

        g.visitFieldInsn(go, cn, f, td);
        g.visitInsn(t1.getOpcode(Opcodes.IRETURN));
        s.visitVarInsn(t1.getOpcode(Opcodes.ILOAD), 2);
        s.visitFieldInsn(po, cn, f, td);
        s.visitInsn(Opcodes.RETURN);

        var w = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        n.accept(w);

        return (MagicAccessor) Unsafe.allocateInstance(Definer.define(w.toByteArray(), true));
    }

    static {
        try {
            if (MagicAccessor.class.getSuperclass().getSuperclass() != Class.forName("jdk.internal.reflect.MagicAccessorImpl")) {
                throw new IllegalStateException("%s must be defined through %s.".formatted(MagicAccessor.class.getName(), Definer.class.getName()));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

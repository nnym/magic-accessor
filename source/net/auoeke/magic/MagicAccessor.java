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
        return fields(type).computeIfAbsent(field, f -> compute(type, f, type.getDeclaredField(f)));
    }

    public abstract boolean getBoolean(Object object);

    public abstract byte getByte(Object object);

    public abstract char getChar(Object object);

    public abstract short getShort(Object object);

    public abstract int getInt(Object object);

    public abstract long getLong(Object object);

    public abstract float getFloat(Object object);

    public abstract double getDouble(Object object);

    public abstract Object get(Object object);

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

    public final Object get() {
        return this.get(null);
    }

    public final void setBoolean(boolean value) {
        this.setBoolean(null, value);
    }

    public final void setByte(byte value) {
        this.setByte(null, value);
    }

    public final void setChar(char value) {
        this.setChar(null, value);
    }

    public final void setShort(short value) {
        this.setShort(null, value);
    }

    public final void setInt(int value) {
        this.setInt(null, value);
    }

    public final void setLong(long value) {
        this.setLong(null, value);
    }

    public final void setFloat(float value) {
        this.setFloat(null, value);
    }

    public final void setDouble(double value) {
        this.setDouble(null, value);
    }

    public final void set(Object value) {
        this.set(null, value);
    }

    private static Map<String, MagicAccessor> fields(Class type) {
        return fields.computeIfAbsent(type, t -> new HashMap<>());
    }

    private static MagicAccessor compute(Class owner, String name, Field field) {
        var type = field.getType();
        var accessorType = type == boolean.class ? BooleanAccessor.class
            : type == byte.class ? ByteAccessor.class
            : type == char.class ? CharAccessor.class
            : type == short.class ? ShortAccessor.class
            : type == int.class ? IntAccessor.class
            : type == long.class ? LongAccessor.class
            : type == float.class ? FloatAccessor.class
            : type == double.class ? DoubleAccessor.class
            : ReferenceAccessor.class;

        var accessorName = accessorType.getName().replace('.', '/');
        var accessorNode = new ClassNode();
        accessorNode.visit(Opcodes.V1_8, 0, String.join("$", accessorName, owner.getName().replace('.', '_'), name), null, accessorName, null);

        var typeName = type.isPrimitive() ? type.getName().transform(m -> Character.toUpperCase(m.charAt(0)) + m.substring(1)) : "";
        var asmType = Type.getType(type);
        var descriptor = asmType.getDescriptor();
        var getter = accessorNode.visitMethod(0, "get" + typeName, "(Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        var setter = (MethodNode) accessorNode.visitMethod(0, "set" + typeName, "(Ljava/lang/Object;%s)V".formatted(type.isPrimitive() ? descriptor : "Ljava/lang/Object;"), null, null);
        var ownerName = owner.getName().replace('.', '/');
        int getOpcode;
        int putOpcode;

        if (Modifier.isStatic(field.getModifiers())) {
            putOpcode = Opcodes.PUTSTATIC;
            getOpcode = Opcodes.GETSTATIC;
        } else {
            putOpcode = Opcodes.PUTFIELD;
            getOpcode = Opcodes.GETFIELD;

            getter.visitVarInsn(Opcodes.ALOAD, 1);
            setter.visitVarInsn(Opcodes.ALOAD, 1);
        }

        getter.visitFieldInsn(getOpcode, ownerName, name, descriptor);
        getter.visitInsn(asmType.getOpcode(Opcodes.IRETURN));
        setter.visitVarInsn(asmType.getOpcode(Opcodes.ILOAD), 2);
        setter.visitFieldInsn(putOpcode, ownerName, name, descriptor);
        setter.visitInsn(Opcodes.RETURN);

        var writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        accessorNode.accept(writer);

        return (MagicAccessor) Unsafe.allocateInstance(Definer.define(writer.toByteArray(), true));
    }

    static {
        if (MagicAccessor.class.getSuperclass().getSuperclass() != Class.forName("jdk.internal.reflect.MagicAccessorImpl")) {
            throw new IllegalStateException("%s must be defined through %s".formatted(MagicAccessor.class.getName(), Definer.class.getName()));
        }
    }
}

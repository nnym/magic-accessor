package net.auoeke.magic;

abstract class FloatAccessor extends MagicAccessor {
    @Override
    public boolean getBoolean(Object object) {
        return this.getFloat(object) != 0;
    }

    @Override
    public byte getByte(Object object) {
        return (byte) this.getFloat(object);
    }

    @Override
    public char getChar(Object object) {
        return (char) this.getFloat(object);
    }

    @Override
    public short getShort(Object object) {
        return (short) this.getFloat(object);
    }

    @Override
    public int getInt(Object object) {
        return (int) this.getFloat(object);
    }

    @Override
    public long getLong(Object object) {
        return (long) this.getFloat(object);
    }

    @Override
    public double getDouble(Object object) {
        return this.getFloat(object);
    }

    @Override
    public Float get(Object object) {
        return this.getFloat(object);
    }

    @Override
    public void setBoolean(Object object, boolean value) {
        this.setFloat(object, value ? 1 : 0);
    }

    @Override
    public void setByte(Object object, byte value) {
        this.setFloat(object, value);
    }

    @Override
    public void setChar(Object object, char value) {
        this.setFloat(object, value);
    }

    @Override
    public void setShort(Object object, short value) {
        this.setFloat(object, value);
    }

    @Override
    public void setInt(Object object, int value) {
        this.setFloat(object, value);
    }

    @Override
    public void setLong(Object object, long value) {
        this.setFloat(object, value);
    }

    @Override
    public void setDouble(Object object, double value) {
        this.setFloat(object, (long) value);
    }

    @Override
    public void set(Object object, Object value) {
        this.setFloat(object, (long) value);
    }
}

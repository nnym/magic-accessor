package net.auoeke.magic;

abstract class DoubleAccessor extends MagicAccessor {
    @Override
    public boolean getBoolean(Object object) {
        return this.getDouble(object) != 0;
    }

    @Override
    public byte getByte(Object object) {
        return (byte) this.getDouble(object);
    }

    @Override
    public char getChar(Object object) {
        return (char) this.getDouble(object);
    }

    @Override
    public short getShort(Object object) {
        return (short) this.getDouble(object);
    }

    @Override
    public int getInt(Object object) {
        return (int) this.getDouble(object);
    }

    @Override
    public float getFloat(Object object) {
        return (float) this.getDouble(object);
    }

    @Override
    public long getLong(Object object) {
        return (long) this.getDouble(object);
    }

    @Override
    public <T> T get(Object object) {
        return (T) (Double) this.getDouble(object);
    }

    @Override
    public void setBoolean(Object object, boolean value) {
        this.setDouble(object, value ? 1 : 0);
    }

    @Override
    public void setByte(Object object, byte value) {
        this.setDouble(object, value);
    }

    @Override
    public void setChar(Object object, char value) {
        this.setDouble(object, value);
    }

    @Override
    public void setShort(Object object, short value) {
        this.setDouble(object, value);
    }

    @Override
    public void setInt(Object object, int value) {
        this.setDouble(object, value);
    }

    @Override
    public void setLong(Object object, long value) {
        this.setDouble(object, value);
    }

    @Override
    public void setFloat(Object object, float value) {
        this.setDouble(object, (long) value);
    }

    @Override
    public void set(Object object, Object value) {
        this.setDouble(object, (long) value);
    }
}

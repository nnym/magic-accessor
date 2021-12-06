package net.auoeke.magic;

abstract class IntAccessor extends MagicAccessor {
    @Override
    public boolean getBoolean(Object object) {
        return this.getInt(object) != 0;
    }

    @Override
    public byte getByte(Object object) {
        return (byte) this.getInt(object);
    }

    @Override
    public char getChar(Object object) {
        return (char) this.getInt(object);
    }

    @Override
    public short getShort(Object object) {
        return (short) this.getInt(object);
    }

    @Override
    public long getLong(Object object) {
        return this.getInt(object);
    }

    @Override
    public float getFloat(Object object) {
        return this.getInt(object);
    }

    @Override
    public double getDouble(Object object) {
        return this.getInt(object);
    }

    @Override
    public <T> T get(Object object) {
        return (T) (Integer) this.getInt(object);
    }

    @Override
    public void setBoolean(Object object, boolean value) {
        this.setInt(object, value ? 1 : 0);
    }

    @Override
    public void setByte(Object object, byte value) {
        this.setInt(object, value);
    }

    @Override
    public void setChar(Object object, char value) {
        this.setInt(object, value);
    }

    @Override
    public void setShort(Object object, short value) {
        this.setInt(object, value);
    }

    @Override
    public void setLong(Object object, long value) {
        this.setInt(object, (int) value);
    }

    @Override
    public void setFloat(Object object, float value) {
        this.setInt(object, (int) value);
    }

    @Override
    public void setDouble(Object object, double value) {
        this.setInt(object, (int) value);
    }

    @Override
    public void set(Object object, Object value) {
        this.setInt(object, (int) value);
    }
}

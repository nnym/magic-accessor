package net.auoeke.magic;

abstract class ReferenceAccessor extends MagicAccessor {
    @Override
    public boolean getBoolean(Object object) {
        return (Long) this.get(object) != 0;
    }

    @Override
    public byte getByte(Object object) {
        return this.get(object);
    }

    @Override
    public char getChar(Object object) {
        return this.get(object);
    }

    @Override
    public short getShort(Object object) {
        return this.get(object);
    }

    @Override
    public int getInt(Object object) {
        return this.get(object);
    }

    @Override
    public long getLong(Object object) {
        return this.get(object);
    }

    @Override
    public float getFloat(Object object) {
        return this.get(object);
    }

    @Override
    public double getDouble(Object object) {
        return this.get(object);
    }

    @Override
    public void setBoolean(Object object, boolean value) {
        this.set(object, value ? 1 : 0);
    }

    @Override
    public void setByte(Object object, byte value) {
        this.set(object, value);
    }

    @Override
    public void setChar(Object object, char value) {
        this.set(object, value);
    }

    @Override
    public void setShort(Object object, short value) {
        this.set(object, value);
    }

    @Override
    public void setInt(Object object, int value) {
        this.set(object, value);
    }

    @Override
    public void setLong(Object object, long value) {
        this.set(object, value);
    }

    @Override
    public void setFloat(Object object, float value) {
        this.set(object, (long) value);
    }

    @Override
    public void setDouble(Object object, double value) {
        this.set(object, (long) value);
    }
}

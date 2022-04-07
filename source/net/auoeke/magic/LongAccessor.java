package net.auoeke.magic;

abstract class LongAccessor extends MagicAccessor {
    @Override
    public boolean getBoolean(Object object) {
        return this.getLong(object) != 0;
    }

    @Override
    public byte getByte(Object object) {
        return (byte) this.getLong(object);
    }

    @Override
    public char getChar(Object object) {
        return (char) this.getLong(object);
    }

    @Override
    public short getShort(Object object) {
        return (short) this.getLong(object);
    }

    @Override
    public int getInt(Object object) {
        return (int) this.getLong(object);
    }

    @Override
    public float getFloat(Object object) {
        return this.getLong(object);
    }

    @Override
    public double getDouble(Object object) {
        return this.getLong(object);
    }

    @Override
    public Long get(Object object) {
        return this.getLong(object);
    }

    @Override
    public void setBoolean(Object object, boolean value) {
        this.setLong(object, value ? 1 : 0);
    }

    @Override
    public void setByte(Object object, byte value) {
        this.setLong(object, value);
    }

    @Override
    public void setChar(Object object, char value) {
        this.setLong(object, value);
    }

    @Override
    public void setShort(Object object, short value) {
        this.setLong(object, value);
    }

    @Override
    public void setInt(Object object, int value) {
        this.setLong(object, value);
    }

    @Override
    public void setFloat(Object object, float value) {
        this.setLong(object, (long) value);
    }

    @Override
    public void setDouble(Object object, double value) {
        this.setLong(object, (long) value);
    }

    @Override
    public void set(Object object, Object value) {
        this.setLong(object, (long) value);
    }
}

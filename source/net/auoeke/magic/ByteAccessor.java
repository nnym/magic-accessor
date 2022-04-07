package net.auoeke.magic;

abstract class ByteAccessor extends MagicAccessor {
    @Override
    public boolean getBoolean(Object object) {
        return this.getByte(object) != 0;
    }

    @Override
    public char getChar(Object object) {
        return (char) this.getByte(object);
    }

    @Override
    public short getShort(Object object) {
        return this.getByte(object);
    }

    @Override
    public int getInt(Object object) {
        return this.getByte(object);
    }

    @Override
    public long getLong(Object object) {
        return this.getByte(object);
    }

    @Override
    public float getFloat(Object object) {
        return this.getByte(object);
    }

    @Override
    public double getDouble(Object object) {
        return this.getByte(object);
    }

    @Override
    public Byte get(Object object) {
        return this.getByte(object);
    }

    @Override
    public void setBoolean(Object object, boolean value) {
        this.setByte(object, (byte) (value ? 1 : 0));
    }

    @Override
    public void setChar(Object object, char value) {
        this.setByte(object, (byte) value);
    }

    @Override
    public void setShort(Object object, short value) {
        this.setByte(object, (byte) value);
    }

    @Override
    public void setInt(Object object, int value) {
        this.setByte(object, (byte) value);
    }

    @Override
    public void setLong(Object object, long value) {
        this.setByte(object, (byte) value);
    }

    @Override
    public void setFloat(Object object, float value) {
        this.setByte(object, (byte) value);
    }

    @Override
    public void setDouble(Object object, double value) {
        this.setByte(object, (byte) value);
    }

    @Override
    public void set(Object object, Object value) {
        this.setByte(object, (byte) value);
    }
}
